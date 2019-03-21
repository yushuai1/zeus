package com.jiandan;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        map.put("yu","yu");
        while (true){
            map.get("yu");
        }
    }
}
