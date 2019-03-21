package com.controller;

import com.aop.ValParam;
import com.redis.RedisApi;
import entry.test.People;
import entry.test.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestCtl {

    private static final Logger logger = LoggerFactory.getLogger(TestCtl.class);
    /**
     * post 示例
     * @param user
     * @return
     */
    @RequestMapping(value = "/testPost", method= RequestMethod.POST)
    public People pstPeople(@RequestBody User user){

        System.out.println(user.toString());

        People people = new People(30,"yushaui",new Date());

        return people;
    }
    /**
     * post 示例
     * @return
     */
    @ValParam
    @RequestMapping(value = "/post", method= RequestMethod.POST)
    public Object pstPeople(@PathParam(value = "id") String id,
                            @PathParam(value = "ids") int ids){

        People people = new People(30,"yushaui",new Date());

        return people;
    }
    /**
     * get 示例
     * @return
     */
    @RequestMapping(value = "/testGet/{id}/{ids}", method= RequestMethod.GET)
    public Map<String,Object> getPeople(@PathVariable(value = "id") String id,@PathVariable(value = "ids") int ids){

        System.out.println(id);
        People people = new People(30,"yushaui",new Date());
        Map<String,Object> reMap = new HashMap<>();
        reMap.put("reslut",people);
        return  reMap;
    }

    @GetMapping(value = "test/{name}")
    public String testDocker(@PathVariable(value = "name") String name){
        logger.info(name);
//        System.out.println(name);
        return name+" nihao";
    }


    @GetMapping(value = "/test1")
    public String test1(){
//        logger.info("---");
//        System.out.println(name);
        return " nihao";
    }

    @GetMapping(value = "testRedis/{name}")
    public String testDockerredis(@PathVariable(value = "name") String name){
        System.out.println(name);
        RedisApi redisApi = new RedisApi();
        redisApi.set(name,name+"zhi");
//        System.out.println(name);
        return name+" nihao";
    }
}
