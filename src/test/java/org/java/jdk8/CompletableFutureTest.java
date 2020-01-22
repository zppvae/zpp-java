package org.java.jdk8;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author zpp
 * @date 2019/4/25 16:04
 */
public class CompletableFutureTest {
    /**
     * 创建一个完成的CompletableFuture
     */
    @Test
    public void testCompleteFuture(){
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        assertEquals("message", cf.getNow(null));
    }

    /**
     * thenApplyAsync：异步函数
     */
    @Test
    public void testThenApplyAsync() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    /**
     * 使用一个固定大小的线程池来应用大写函数
     */
    @Test
    public void testThenApplyAsyncWithExecutor(){
        ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
            int count = 1;

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "custom-executor-" + count++);
            }
        });

        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        }, executor);

        assertNull(cf.getNow(null));
        assertEquals("MESSAGE", cf.join());
    }

    /**
     * 消费前一阶段的结果
     *
     * 如果下一阶段接收了当前阶段的结果，但是在计算的时候不需要返回值(它的返回类型是void)，
     * 那么它可以不应用一个函数，而是一个消费者， 调用方法也变成了thenAccept:
     */
    @Test
    public void testThenAccept(){
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(s -> result.append(s));
        System.out.println(result.toString());
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 异步地消费迁移阶段的结果
     */
    @Test
    public void testThenAcceptAsync(){
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     *  使用BiConsumer处理两个阶段的结果
     */
    @Test
    public void testThenAcceptBoth(){
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        assertEquals("MESSAGEmessage", result.toString());
    }

    private String delayedUpperCase(String s){
        return s.toUpperCase();
    }

    private String delayedLowerCase(String s){
        return s.toLowerCase();
    }

    /**
     * 使用BiFunction处理两个阶段的结果（同步）
     *
     * thenCombine: 复合两个阶段的结果再返回一个结果
     */
    @Test
    public void testThenCombine(){
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2);
        //getNow：得到最终的结果
        assertEquals("MESSAGEmessage", cf.getNow(null));
    }

    /**
     * 异步使用BiFunction处理两个阶段的结果
     *
     * 依赖的前两个阶段异步地执行，所以thenCombine()也异步地执行，即时它没有Async后缀。
     */
    @Test
    public void testThenCombineAsync(){
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(
                        CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + s2
                );
        //join：等待获取结果
        assertEquals("MESSAGEmessage", cf.join());
    }

    /**
     * 组合 CompletableFuture
     *
     * thenCompose函数需要一个大写字符串做参数，然后返回一个CompletableFuture,
     * 这个CompletableFuture会转换字符串变成小写，然后连接在大写字符串的后面。
     */
    @Test
    public void testThenCompose(){
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
                        .thenApply(s -> upper + s));
        assertEquals("MESSAGEmessage", cf.join());
    }

    private boolean isUpperCase(Object s){
        return StringUtils.isAllUpperCase(s+"");
    }

    /**
     * 当几个阶段中的一个完成，创建一个完成的阶段
     */
    @Test
    public void testAnyOf(){
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if(th == null) {
                assertTrue(isUpperCase((String) res));
                result.append(res);
            }
        });
        assertTrue("Result was empty", result.length() > 0);
    }

    /**
     * 当所有的阶段都完成后创建一个阶段（同步）
     */
    @Test
    public void testAllOf(){
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
            futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
            result.append("done");
        });
    }

    /**
     * 当所有的阶段都完成后异步地创建一个阶段
     */
    @Test
    public void testAllOfAsync(){
        StringBuilder result = new StringBuilder();
        List<String> messages = Arrays.asList("a", "b", "c");
        List<CompletableFuture> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
                .collect(Collectors.toList());

        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .whenComplete((v, th) -> {
                    futures.forEach(cf -> assertTrue(isUpperCase(cf.getNow(null))));
                    result.append("done");
                });
        allOf.join();
        assertTrue("Result was empty", result.length() > 0);
    }


    /**
     * 完成计算异常
     *
     *
     * @version JDK9
     */
    @Test
    public void testCompleteExceptionally(){
//        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
//                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
//        CompletableFuture exceptionHandler = cf.handle((s, th) -> { return (th != null) ? "message upon cancel" : ""; });
//        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
//        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
//        try {
//            cf.join();
//            fail("Should have thrown an exception");
//        } catch(CompletionException ex) { // just for testing
//            assertEquals("completed exceptionally", ex.getCause().getMessage());
//        }
//
//        assertEquals("message upon cancel", exceptionHandler.join());
    }
}
