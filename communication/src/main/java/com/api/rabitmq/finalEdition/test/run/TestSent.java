package com.api.rabitmq.finalEdition.test.run;

import com.api.rabitmq.finalEdition.start.RabbitSentMsg;
import com.rabbitmq.client.ConfirmListener;

public class TestSent {

    public static void main(String[] args) throws Exception {
        RabbitSentMsg rabbitSentMsg = new RabbitSentMsg("10.2.1.227","mq","mq",true);
        ConfirmListener confirmSentListener = new ConfirmSentListener(rabbitSentMsg.confirmSet);
        rabbitSentMsg.setConfirmListener(confirmSentListener);

//        rabbitSentMsg.setMqExchange("nihao19");
//        for (int i=0;i<1;i++){
//            rabbitSentMsg.sentMany("买保险"+"-"+i);
//            Thread.sleep(100L);
//        }

        rabbitSentMsg.setMqQueue("hello15");
        for (int i=0;i<10;i++){
            rabbitSentMsg.sentOne("买保险"+"-"+i);
            Thread.sleep(100L);
        }


        rabbitSentMsg.mqClose();
    }
}
