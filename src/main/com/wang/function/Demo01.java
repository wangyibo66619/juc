package main.com.wang.function;

import java.util.function.Function;

/*
*   function型接口，有一个输入参数，有一个输出
*
*/
public class Demo01 {
    public static void main(String[] args) {
        // 工具类，输出输入的值
//        Function function = new Function<String,String>(){
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function<String,String> function = (str)->{return str;};

        System.out.println(function.apply("haha"));
    }
}
