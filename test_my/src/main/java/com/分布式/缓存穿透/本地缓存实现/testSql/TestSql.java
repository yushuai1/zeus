package com.分布式.缓存穿透.本地缓存实现.testSql;

import com.分布式.缓存穿透.本地缓存实现.SimDataSql;

public class TestSql implements SimDataSql<Integer,TestUser> {

    @Override
    public TestUser getData(Integer s){
        TestUser k = new TestUser("yushaui",15);
        return k;
    }
}
