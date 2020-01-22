package org.java.concurrency.thread.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 线程池大小与处理器的利用率之比可以使用下面的公式进行估算：
 * T = N * U * (1 + W/C)
 *
 * 其中:
 *
 * N是处理器的核的数量，可以通过Runtime.getRuntime().availableProcessors()得到
 *
 * U是期望的CPU利用率(该值应该介于0-1之间)
 *
 * W/C是等待时间与计算时间的比率
 *
 * 拿我们的例子举例，我的机器有8个核，则N是8，我们的getPrice方法单独执行时耗时1010毫秒，其中1000毫秒是等待时间，那么等待时间和计算时间的比是 1000/10 = 100, 那么，如果我希望CPU利用率 70% 的话，线程数应该是 8 * 0.7 * 100 = 560
 *
 * 而在实际操作中，如果你创建的线程数比商店的数目更多，反而是一种浪费。因为多出的线程根本没机会用到，所以，最佳的策略应该是创建的线程数与商店数目相等，最大不超过560.
 */
public class ShopPriceService {

    private static List<Shop> shops = new ArrayList<>();

    static {
        for (int i = 1; i < 100; i++) {
            shops.add(new Shop("show"+i));
        }
    }

    /**
     * 顺序流计算
     * @param product
     * @return
     */
    public List<String> findPrices1(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 并行流计算
     *
     * 并行流背后依赖ForkJoinPool，它的默认最大线程数是cpu核数
     * @param product
     * @return
     */
    public List<String> findPrices2(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 用 CompletableFuture 来将对不同商店的同步调用替换为异步调用。
     * @param product
     * @return
     */
    public List<String> findPrices3(String product) {

        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());

        //等待所有异步操作结束
        //对所有future对象执行join操作，一个接一个地等待他们运行结束
        //注意join方法和Future接口中的get有相同的含义，唯一的不同是join不会抛出任何受检查异常。
        // 使用它你不再需要使用try/catch语句来捕获get抛出的异常。
        return priceFuture.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    /**
     * 自定义执行器Executor
     *
     * 处理需要大量使用异步操作的情况时，这几乎是最有效的策略
     * @param product
     * @return
     */
    public List<String> findPrices4(String product) {
        //声明一个线程数等于商店数量的执行器，最大线程数100
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));

        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 连接多个异步任务 —— 顺序流
     * @param product
     * @return
     */
    public List<String> findPrices5(String product) {

        return shops.stream()
                .map(shop -> shop.getPriceStr(product))
                .map(Quote::parse)
                .map(DiscountService::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 连接多个异步任务——CompletableFuture
     *
     * thenApply和thenCompose都接收一个Function实现。在当前CompletableFuture执行完以后，会将结果传给这个Function进行处理。
     * thenApply的Function可以返回任何值，一般用于同步处理。
     * thenCompose的Function必须返回一个CompletableFuture，一般用于连接两个异步处理。
     * 这两个方法，还有CompletableFuture中的一些方法，都有一个以Async后缀结尾的版本。
     * 通常而言，名称不带Async的方法和他的前一个任务在同一个线程中运行，
     * 而名称以Async结尾的方法会将后续的任务提交到一个线程池，所以后续任务是由不同的线程处理的。
     *
     * supplyAsync(Supplier<U> supplier)：
     *         使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值
     * @param product
     * @return
     */
    public List<String> findPrices6(String product) {
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));
        List<CompletableFuture<String>> priceFuture = shops.stream()
                //异步方式获取价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceStr(product), executor))
                //前一个Future返回后，对其返回的值进行转换
                .map(future -> future.thenApply(Quote::parse))
                //使用另一个异步任务构造期望的Future，申请折扣
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> DiscountService.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        //等待流中所有的Future执行完毕，并提取各自的返回值
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 并行多个异步任务
     *
     * thenCombine()
     *      将两个完全不相干的 CompletableFuture 对象的结果整合起来
     * 在第一个map里，同时开了两个线程分别查询价格和汇率，
     * 当两个任务都完成后，用这两个异步任务的返回调用BiFunction，然后用BiFunction的结果异步调用第三个任务算折扣。
     * 这样，虽然9个商店都调用了3个耗时一秒的远程服务，但由于9个任务都是并行的，而且3个耗时1秒的远程服务中的2个又是并行的，
     * 所以，最终的耗时仅有2秒。
     * @param product
     * @return
     */
    public List<String> findPrices7(String product) {
        //不能再使用shop数量做线程数了，因为有独立的异步任务要同时执行，需要更多的线程
        Executor executor = Executors.newFixedThreadPool(100);

        List<CompletableFuture<String>> priceFuture = shops.stream()
                //第一个任务，查询商品价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceQuote(product), executor).thenCombine(
                        //第二个任务，查询汇率
                        CompletableFuture.supplyAsync(() -> ExchangeService.getRate("USD", "CNY"), executor),
                        //将商品价格乘以汇率得到人民币价格
                        (quote, rate) -> new Quote(quote.getShop(), quote.getPrice() * rate, quote.getDiscount())))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> DiscountService.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 只要有商店返回商品价格就在第一时间显示返回值，不再等待哪些还未返回的商店(有些甚至会发生超时)
     * @param product
     */
    public static void findPrices8(String product) {
        long start = System.currentTimeMillis();
        Executor executor = Executors.newFixedThreadPool(100);

        CompletableFuture<?>[] futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceQuote(product), executor).thenCombine(
                        CompletableFuture.supplyAsync(() -> ExchangeService.getRate("USD", "CNY"), executor),
                        (quote, rate) -> new Quote(quote.getShop(), quote.getPrice() * rate, quote.getDiscount())))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> DiscountService.applyDiscount(quote), executor)))
                .map(future -> future.thenAccept(content ->
                        System.out.println(content + " (done in " + (System.currentTimeMillis() - start )+" msecs)")))
                .toArray(size -> new CompletableFuture[size]);

    }

    /**
     * 所有商店处理完成后执行相关业务
     *
     * allOf方法接收一个由 CompletableFuture 组成的数组，数组中的所有 CompletableFuture 对象执行完成之后，
     * 它返回一个 CompletableFuture<Void> 对象。
     * 你只需要对这个对象调用thenAccept方法就可以在所有 CompletableFuture 处理完毕后执行一些操作。
     * @param product
     */
    public static void findPrices9(String product) {
        long start = System.currentTimeMillis();
        Executor executor = Executors.newFixedThreadPool(100);

        CompletableFuture<?>[] futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceQuote(product), executor).thenCombine(
                        CompletableFuture.supplyAsync(() -> ExchangeService.getRate("USD", "CNY"), executor),
                        (quote, rate) -> new Quote(quote.getShop(), quote.getPrice() * rate, quote.getDiscount())))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> DiscountService.applyDiscount(quote), executor)))
                .map(future -> future.thenAccept(content ->
                        System.out.println(content + " (done in " + (System.currentTimeMillis() - start )+" msecs)")))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).thenAccept((obj) -> System.out.println("all shop return results or time out"));

    }
    public static void main(String[] args) {
        ShopPriceService priceService = new ShopPriceService();
        long start = System.currentTimeMillis();
//        System.out.println(priceService.findPrices7("iPhone7"));
        findPrices9("iphone");
        System.out.println("服务耗时:" + ( System.currentTimeMillis() - start ));
    }
}
