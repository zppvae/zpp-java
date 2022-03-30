package org.java.jdk8.lambda;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaDemo {

    public static void main(String[] args) {
//        //1、线程lambda
//        Runnable aa = () -> System.out.println("ok");
//        new Thread(aa).start();

        //2、排序
//		List<String> names = Arrays.asList("111","ccc","aaa","58");
//		Collections.sort(names, (a, b) -> a.compareTo(b));
//		System.out.println(names);

//        //3、收集器
//        List<String> list = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "Canada");
//        //将字符串换成大写并用点号链接起来
//        String str = list.stream().map(x -> x.toUpperCase()).collect(Collectors.joining("、"));
//        //USA、JAPAN、FRANCE、GERMANY、ITALY、CANADA
//        System.out.println("转换大写：" + str);
//
//        List<String> collect = list.stream().map(String::toUpperCase)
//                .peek(e -> System.out.println("Filtered value: " + e))
//                .collect(Collectors.toList());
//        System.out.println(collect);


        //集合元素分组
//        List<Person> list = new ArrayList<>();
//        list.add(new Person(10, "Elon"));
//        list.add(new Person(12, "Dennisit"));
//        list.add(new Person(10, "Alone"));

//        Map<Integer, List<Person>> groupByAge = list.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.toList()));
//        // {10=[{"age":10,"name":"Elon"}, {"age":10,"name":"Alone"}], 12=[{"age":12,"name":"Dennisit"}]}
//        System.out.println("按年龄分组："+groupByAge);

//        List<String> names = list.stream().map(e->e.getName()).collect(Collectors.toList());
//        // [Elon, Dennisit, Alone]
//        System.out.println("获取姓名："+names);

//        List<Integer> ages = list.stream().filter(e->e.getAge()==10).map(e->e.getAge()).distinct().collect(Collectors.toList());
//        // [10, 12]
//        System.out.println(ages);

//        List<Integer> users = Arrays.asList(14,11,33);
//        Iterator<Person> its = list.iterator();
//        while (its.hasNext()) {
//            if (users.contains(its.next().getAge())) {
//                its.remove();
//            }
//        }
//        System.out.println(list);



        //集合元素递归
//        List<Person2> list = new ArrayList<>();
//        List<Person2> child1 = new ArrayList<>();
//        child1.add(new Person2(15, "zpp"));
//
//        List<Person2> child2 = new ArrayList<>();
//        child2.add(new Person2(13, "zpp"));
//
//        List<Person2> child3 = new ArrayList<>();
//        child3.add(new Person2(14, "zpp"));
//        list.add(new Person2(10, "Elon",child1));
//        list.add(new Person2(12, "Dennisit",child2));
//        list.add(new Person2(10, "Alone",child3));
//
//        List<String> collect = list.stream().filter(e -> e.getAge() == 10).map(e -> e.getName().toUpperCase())
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(collect);


        // 获取最大值
        Person p1 = new Person(1, "zz");
        Person p2 = new Person(2, "22");
        Person p3 = new Person(3, "221");

        List<Person> list = Arrays.asList(p1, p2, p3);
        Integer maxAge = list.stream().max(Comparator.comparingInt(Person::getAge)).get().getAge();
        System.out.println(maxAge);
    }

    static class Person{
        private Integer age;
        private String name;

        public Person(int age,String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString(){
            return JSON.toJSONString(this);
        }
    }

    static class Person2{
        private Integer age;
        private String name;

        private List<Person2> child;

        public Person2(int age,String name) {
            this.age = age;
            this.name = name;
        }

        public Person2(int age,String name,List<Person2> child) {
            this.age = age;
            this.name = name;
            this.child = child;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString(){
            return JSON.toJSONString(this);
        }

        public List<Person2> getChild() {
            return child;
        }

        public void setChild(List<Person2> child) {
            this.child = child;
        }
    }

}


