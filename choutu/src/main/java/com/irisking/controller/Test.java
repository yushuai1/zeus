package com.irisking.controller;

import com.irisking.entry.ChouTuBean;
import entry.test.TestEntry;
import entry.test.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {

    @PostMapping(value = "/testPost")
    public Map<String,Integer> postTest(@RequestParam(value = "") String name){

        System.out.println(name);

        Map<String,Integer> reMap = new HashMap<>();
        reMap.put("code",1);
        reMap.put("result",2);
        return reMap;
    }

    @DeleteMapping(value = "/testDel/{cardID}")
    public Map<String,Object> httpDelete(@PathVariable(name = "cardID") String cardID){

        System.out.println(cardID);

        Map<String,Object> reMap = new HashMap<>();
        reMap.put("code","ok");
        reMap.put("result","very good");
        return reMap;
    }

    @GetMapping(value = "/testGet/{name}")
    public Map<String,Object> postGet(@PathVariable(name = "name") String name){

        System.out.println(name);

        Map<String,Object> reMap = new HashMap<>();
        reMap.put("code","1");
        reMap.put("result",2);
        return reMap;
    }

    @PostMapping(value = "/testEntry")
    public TestEntry postEntry(@RequestParam(value = "") String name){

        System.out.println(name);
        TestEntry testEntry = new TestEntry(name,15);
        return testEntry;
    }

    @PutMapping (value = "/testPut")
    public TestEntry postPut(@RequestParam(value = "name") String name){

        System.out.println(name);
        TestEntry testEntry = new TestEntry(name,15);
        return testEntry;
    }


    @PostMapping(value = "/testniu")
    public TestEntry postniu(@RequestBody User user){
        System.out.println(user.getName());
        TestEntry testEntry = new TestEntry(user.getName(),15);
        return testEntry;
    }
}
