package main.com.wang.function;

import java.util.function.Predicate;
/*
*   断定型接口：有一个输入参数，返回值只能是 布尔值
*/
public class Demo02 {
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> stringPredicate = new Predicate<String>() {
//            @Override
//            public boolean test(String o) {
//                return o.isEmpty();
//            }
//        };

        Predicate<String> predicate = (str)->{return str.isEmpty();};
        System.out.println("sda");


    }
}
