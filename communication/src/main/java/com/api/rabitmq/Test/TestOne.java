package com.api.rabitmq.Test;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class TestOne {

    public Connection connection = null;
    public Channel channel =null;
    private String mqQueue = "onequeue";
    private String mqExchange = "publish2";

    private Map<String,List<String>> map = new HashMap<>();

    public TestOne(String ip , String username, String password) throws IOException, TimeoutException {
        connection = getConnection(ip,username,password);
        channel = connection.createChannel();
    }

    protected static Connection getConnection(String ip ,String username,String password) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setPort(AMQP.PROTOCOL.PORT);// MQ端口
        factory.setUsername(username);// MQ用户名
        factory.setPassword(password);// MQ密码
        Connection connection = factory.newConnection();
        return connection;
    }

    private void sentRabbitMq(String content) throws Exception {
        channel.exchangeDeclare(mqExchange, "fanout", true, false, null);
        channel.basicPublish(mqExchange, "", null, content.getBytes());
    }

    private void getRabbitMq() throws IOException {
        channel.queueDeclare(this.mqQueue, false, false, false, null);

        // 定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                String key = message.split("@@@@")[1];

                if (map.containsKey(key)){
                    map.get(key).add(message);
                }else {
                    List<String> list = new ArrayList<>();
                    list.add(message);
                    map.put(key,list);
                }
            }
        };
        channel.basicConsume(this.mqQueue, true, consumer);
    }

    private List<String> getList(String key) throws InterruptedException {
        long t = System.currentTimeMillis();
        while (true){

            if (map.get(key)!=null&&map.get(key).size() ==2){
                return map.get(key);
            }else {
                Thread.sleep(10L);
                if (System.currentTimeMillis()-t>=1000*10){
                    return null;
                }
            }
        }
    }
    private void closeMq() throws Exception {
        channel.close();
        connection.close();
    }


    public static void main(String[] args) throws Exception {
        String param = "{ \"openID\": \"444522\", \"appID\": 1, \"eyeMode\": 1,\"index\": 0, \"feature\":\"6ubVlYeHMXh4aAE1lMfPF29oUBESP//fXP6/l3/96+suIlDNx0AgNKzu+1kXJm3d8qJszJcVeTv+zAEB8v7sRZaU6mIRXd+3tzFIz91Qq6+nFFDI+qODJiydvc+mqs6/EbJiSMyFVTF7/k0BArL+7EUVt7cQAEBVlZ2mIuru/z2Vh6b/2UQmv5dV/OqLkRRmbL2pyNPW5rrZWVNNjsKx00HBwMrLPh+isxCpJCHF9/v5TCQVUYAip5fHaDAUpysIXVXV15c3L29s7Pp5Xc3ielHVyMT++3FB/P/+7FjV16NqWV1VVbemqqoAEZe3t/9/ff3//7elBQBIyIKKqzeVXaSsvPjZy29NzMJraYXXW8NrT0wETv/7S0GXpq44fHVlilrzLW1YE6eu1Vxse4KAPX93wICffmEBEIbO92t5SG0IwSrA4OyE/TW01MTc3MzspaWlrT2l7Xl6zs5tbzdTWgW3/M/PMWpoEZWGrv3dBCK/B3X46oMVFi78uahK17amxtVdePqubTv70siE/X81ETKC29Pa+Dg6tpcVETERemp9fRKS9v/dFRGEpqf/elhI6u63l5e3FVhoSEARlIaHt2hIETDUz4c3amgREZY//91c/r9X//7v25NHRzU9qMneAAAAAKr/VWL1+pRodbEVJAiiUhIzGKHxcQAASkf/QFI=\"}";
        TestOne testOne = new TestOne("10.2.1.227", "mq", "mq");
        long t1 = System.currentTimeMillis();
        System.out.println("-----------"+System.currentTimeMillis());
        for (int i = 0; i < 1; i++) {
            testOne.sentRabbitMq(i+"----"+param);
            testOne.getRabbitMq();
            List<String> lists = testOne.getList(i+"");
            System.out.println(lists.toString());
        }
        System.out.println(System.currentTimeMillis()-t1);
//        System.out.println(System.currentTimeMillis());
        testOne.closeMq();

    }
}
