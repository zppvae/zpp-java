//package org.java.jdk11;
//
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// *
// * @author zpp
// * @date 2018/11/14 14:07
// */
//public class FileApi {
//
//    public static void main(String[] args) throws Exception{
//        //简化读取配置文件
//        //将string内容写入到文件
//        Files.writeString(
//                Path.of("D://", "tmp.txt"), // 路径
//                "hello, jdk11 files api", // 内容
//                StandardCharsets.UTF_8); // 编码
//
//        String s = Files.readString(
//                Paths.get("D://tmp.txt"), // 路径
//                StandardCharsets.UTF_8); // 编码
//
//        System.out.println("读取到的文件内容："+s);
//    }
//}
