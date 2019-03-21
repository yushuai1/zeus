package com.api.rabitmq.finalEdition.test.run;


import com.api.rabitmq.finalEdition.Interface.RabbitGetListener;
import com.api.rabitmq.finalEdition.start.RabbitGetMsg;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestGet {

    public static void main(String[] as) throws Exception {
        RabbitGetListener rabbitGetListener = new ConfirmGetListener();
        RabbitGetMsg rabbitGetMsg = new RabbitGetMsg("10.2.1.227","mq","mq");
        rabbitGetMsg.setTailListener(rabbitGetListener);
        rabbitGetMsg.setMqQueue("hello15");
//        rabbitGetMsg.getOne();
        rabbitGetMsg.getOnePull();

//        rabbitGetMsg.setMqExchange("nihao19");
//        rabbitGetMsg.getMany();
//        rabbitGetMsg.getMany("yu4","shuai3");
        System.out.println("接受程序启动完毕！");
    }
}
