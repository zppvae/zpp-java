//package org.java.jdk11;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.concurrent.CompletableFuture;
//
///**
// * JDK11的新的HttpClient支持HTTP/2和WebSocket，并且可以使用异步接口
// */
//public class HttpApi {
//
//    public static void main(String[] args) {
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com/")).GET().build();
//        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
//        HttpClient client = HttpClient.newHttpClient();
//        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, bodyHandler);
//        future.thenApply(HttpResponse::body).thenAccept(System.out::println).join();
//    }
//}