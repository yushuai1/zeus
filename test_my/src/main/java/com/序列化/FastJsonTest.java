package com.序列化;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class FastJsonTest {

    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("fastjson1");
        list.add("fastjson2");
        list.add("fastjson3");
        String jsonStringThird = JSON.toJSONString(list);//是json数组
        System.out.println("将list转换为json字符串:"+jsonStringThird);

        List<String> list2 = JSON.parseObject(jsonStringThird,new TypeReference<List<String>>(){});
        System.out.println("将json转换为List："+list2.get(2));

    }
}
