package com.多线程.Future;

import java.util.ArrayList;
import java.util.List;

public class Future {
    public static void main(String[] args) {

        List<String> asdf = new ArrayList<String>();
        asdf.add("1");
        asdf.add("2");
        asdf.add("3");
        asdf.add("4");
        asdf.add("5");
        List<String> aaa = asdf.subList(1,2);
        aaa.set(0,"100");

        for (String as: asdf) {
            System.out.println(as);
        }



        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕 "+System.currentTimeMillis());
        //...这里做一些其它任务
        System.out.println("数据："+data.getResult());
        System.out.println("获取完毕 "+System.currentTimeMillis());
    }
}