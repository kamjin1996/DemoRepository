package com.zxx.demorepository.LambdaTest;

import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.coyote.http11.Constants.a;

/**
 * @Auther: KAM1996
 * @Date: 14:09 2018-11-10
 * @Description: 常用Lambda函数测试
 * @Version: 1.0
 */
public class CommonFunTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //一。Predicate接口
        predicateTest();
        //二。Function接口
        functionInterfaceTest();
        //三。Supplier接口
        supplierTest();
        //四。Stream，数据操作接口
        //filter
        streamFilterTest();
        //sorted
        streamSortedTest();

        //完结方法
        //Match，用来判断某个predicate是否和流对象相匹配，最终返回Boolean类型结果
        streamMatchTest();
        //collect，在对经过变换之后，我们将变换的Stream的元素收集，比如将这些元素存至集合中，此时便可以使用Stream提供的collect方法，
        streamCollectTest();

        //count，类似sql的count，用来统计流中元素的总数，
        streamCountTest();

        //Reduce，规约，reduce方法允许我们用自己的方式去计算元素或者将一个Stream中的元素以某种规律关联
        streamReduceTest();
        //执行结果如下：
        /*
        a1|a2
        a1|a2|b1
        a1|a2|b1|b2
        a1|a2|b1|b2|b3
        a1|a2|b1|b2|b3|o1
        */
    }

    public static void predicateTest() throws IllegalAccessException, InstantiationException {
        //规则是字符串大于0
        //Predicate<String> pre = str -> str.length()>0;
        Predicate<String> pre = (String str) -> str.length() > 0;
        System.out.println("字符串长度是否大于0:"+pre.test("0"));
        //规则是对象不为null
        Predicate<Object> pre2 = Objects::nonNull;
        System.out.println("不是Null:"+pre2.test(null));
        //规则是集合size大于0
        Predicate<List<User>> pre3 = list -> list.size() > 0;
        List<User> list = Arrays.asList(
                new User("张三"),
                new User("李四"),
                new User("王五"),
                User.Build.class.newInstance().name("赵六").build()

        );
        System.out.println("size是否大于0:" + pre3.test(list));
        //遍历此list
        list.forEach(System.out::println);
        list.forEach(User::run);
    }

    public static void functionInterfaceTest(){
        Function<String, Integer> toInteger = Integer::valueOf;
        //toInteger的执行结果，将作为backToString的输入
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println("是否属于Integer类型："+(toInteger.apply("123") instanceof Integer));
        System.out.println("是否属于String类型："+(backToString.apply("123") instanceof String));

        Function<String, Integer> mutiply = (i) -> {
            System.out.println("frist input:" + i);
            return Integer.valueOf(i) * 2;
        };

        Function<String, Integer> zero = mutiply.andThen((i) -> {
            System.out.println("second input:" + i);
            return i - i;
        });

        Integer res = zero.apply("9");
        System.out.println(res);
    }


    public static void supplierTest(){
        Supplier<String> supplier = () -> "special type value";
        String s = supplier.get();
        System.out.println(s);

    }
    public static void streamFilterTest(){
        List<String> strList = Arrays.asList("a9z","xcq","zxcaas","a3sasxx","fxcqxa","a6ascaxc");
        //filter方法，配合Predicate过滤
        strList.stream().filter(str -> str.startsWith("a")).filter(str -> str.length()>3).forEach(System.out::println);

        //等价于以上
        Predicate<String> startsWitha = str -> str.startsWith("a");
        Predicate<String> strlength = str -> str.length()>3;
        strList.stream().filter(startsWitha).filter(strlength).forEach(System.out::println);
    }

    public static void streamSortedTest() {
        List<String> strList = Arrays.asList("a9z","xcq","zxcaas","a3sasxx","fxcqxa","a6ascaxc");
        //sorted方法,配合Comparator排序
        System.out.println("默认Comparator");
        strList.stream().sorted().filter((str)->str.startsWith("a")).forEach(System.out::println);

        System.out.println("自定义Comparator");
        strList.stream().sorted(Comparator.reverseOrder()).filter((str)->str.startsWith("a")).forEach(System.out::println);
        /*
        //ps:
        Comparator.naturalOrder();//自然顺序
        Comparator.reverseOrder();//反转排序
        */

        ////等价于以上，阅读成本挺大
        //List<String> strList2 = new ArrayList<>();
        //for (String str : strList) {
        //    strList2.add(str);
        //}
        //strList2.sort(String::compareTo);
        //for (String str : strList2) {
        //    if (str.startsWith("a")) {
        //        System.out.println(str);
        //    }
        //}
    }

    public static void streamCollectTest() {
        List<String> lists = Arrays.asList("a9z","xcq","zxcaas","a3sasxx","fxcqxa","a6ascaxc");
        List<String> list = lists.stream().filter((p) -> p.startsWith("a")).sorted().collect(Collectors.toList());
        System.out.println(list);

    }

    public static void streamMatchTest() {
        List<String> lists = Arrays.asList("a9z","xcq","zxcaas","a3sasxx","fxcqxa","a6ascaxc");
        //流对象中只要有一个元素匹配就返回true，可配合Predicate接口
        boolean anyStartWithA = lists.stream().anyMatch((s -> s.startsWith("a")));
        System.out.println(anyStartWithA);
        //流对象中每个元素都匹配就返回true
        boolean allStartWithA
                = lists.stream().allMatch((s -> s.startsWith("a")));
        System.out.println(allStartWithA);
    }

    public static void streamCountTest() {
        List<String> lists = Arrays.asList("a9z","xcq","zxcaas","a3sasxx","fxcqxa","a6ascaxc");

        long count = lists.stream().filter((s -> s.startsWith("a"))).count();
        System.out.println(count);
    }

    public static void streamReduceTest() {
        List<String> lists = Arrays.asList("a9z","xcq","zxcaas","a3sasxx","fxcqxa","a6ascaxc");
        Optional<String> optional = lists.stream().sorted().reduce((s1, s2) -> {
            System.out.println(s1 + "|" + s2);
            return s1 + "|" + s2;
        });
    }

    @Data
    private static class User {
        private String name;
        public User(String name) {
            this.name = name;
        }
        public User(Build build) {
            setName(build.name);
        }

        static class Build{
            private String name;

            public Build name(String name){
                this.name = name;
                return this;
            }

            public User build(){
                return new User(this);
            }
        }
        public void run (){
            System.out.println("running...............");
        }
        @Override
        public String toString(){
            return "name:" + name;
        }
    }
}
