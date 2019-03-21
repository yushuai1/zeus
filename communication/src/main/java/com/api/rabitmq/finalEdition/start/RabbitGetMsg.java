package com.api.rabitmq.finalEdition.start;

import com.api.rabitmq.finalEdition.Interface.RabbitGetListener;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.api.rabitmq.finalEdition.util.RabbitConnect.getConnection;


public class RabbitGetMsg {

    /**
     * 连接
     */
    private Connection connection = null;
    /**
     * 管道
     */
    public Channel channel =null;
    /**
     * 对列名
     */
    private String mqQueue = "";
    /**
     * 交互器
     */
    private String mqExchange = "";
    /**
     * 是否自动确认
     */
    private boolean flag = false;

    /**
     * 处理获取内容处理类
     */
    private RabbitGetListener tailListener = null;

    public void setTailListener(RabbitGetListener tailListener) {
        this.tailListener = tailListener;
    }

    public RabbitGetMsg(String ip , String username, String password, RabbitGetListener tailListener) throws IOException, TimeoutException {
        connection = getConnection(ip,username,password);
        channel = connection.createChannel();
        this.tailListener = tailListener;
    }
    public RabbitGetMsg(String ip , String username, String password) throws IOException, TimeoutException {
        connection = getConnection(ip,username,password);
        channel = connection.createChannel();
    }

    public void setMqQueue(String mqQueue) {
        this.mqQueue = mqQueue;
    }

    public void setMqExchange(String mqExchange) {
        this.mqExchange = mqExchange;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void getOne() throws IOException {
        // 声明队列(如果你已经明确的知道有这个队列,那么下面这句代码可以注释掉,
        // 如果不注释掉的话,也可以理解为消费者必须监听一个队列,如果没有就创建一个)
        channel.queueDeclare(this.mqQueue, false, false, false, null);

        // 定义队列的消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                tailListener.mqLine(message,channel,envelope.getDeliveryTag());
            }
        };
        channel.basicConsume(this.mqQueue, flag, consumer);
    }

    public void getOnePull() throws Exception {
        // 声明队列(如果你已经明确的知道有这个队列,那么下面这句代码可以注释掉,
        // 如果不注释掉的话,也可以理解为消费者必须监听一个队列,如果没有就创建一个)
        channel.queueDeclare(this.mqQueue, false, false, false, null);

        while (true) {
            //自动拉取，第二个参数自动确认
            GetResponse resp = channel.basicGet(this.mqQueue,false);
            if (resp == null) {
                TimeUnit.MILLISECONDS.sleep(1000);
            } else {
                String message = new String(resp.getBody(), "UTF-8");
                tailListener.mqLine(message,channel,resp.getEnvelope().getDeliveryTag());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }

    }

    /**
     * 采用默认的routingKey和对列明，适用于发布订阅
     * @throws Exception
     */
    public void getMany() throws Exception{

        String routingKey = "test01";
        // 创建一个非持久的、唯一的且自动删除的队列且队列名称由服务器随机产生一般情况这个名称与amq.gen-JzTY20BRgKO-HjmUJj0wLg 类似。
        String queue = channel.queueDeclare().getQueue();
        getMany(routingKey,queue);
    }

    /**
     * 推模式，指定routingKey和队列名，适用于指定队列同一队列只有一个消费者可以消费
     * @param routingKey
     * @param queue
     * @throws Exception
     */
    public void getMany(String routingKey,String queue) throws Exception {

        channel.exchangeDeclare(this.mqExchange, "fanout",true, false, null);
        //声明队列
        channel.queueDeclare(queue, true, false, false, null);
        // 为转发器指定队列，设置binding
        channel.queueBind(queue, this.mqExchange, routingKey,null);

        while (true) {
            //自动拉取，第二个参数自动确认
            GetResponse resp = channel.basicGet(this.mqQueue,false);
            if (resp == null) {
                TimeUnit.MILLISECONDS.sleep(1000);
            } else {
                String message = new String(resp.getBody(), "UTF-8");
                tailListener.mqLine(message,channel,resp.getEnvelope().getDeliveryTag());
            }
        }
    }


    /**
     * 拉模式
     * @param routingKey
     * @param queue
     * @throws IOException
     */
    public void getManyPull(String routingKey,String queue) throws IOException {

        channel.exchangeDeclare(this.mqExchange, "fanout",true, false, null);
        //声明队列
        channel.queueDeclare(queue, true, false, false, null);
        // 为转发器指定队列，设置binding
        channel.queueBind(queue, this.mqExchange, routingKey,null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleConsumeOk(String s) {
                System.out.println(s);
            }

            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String message = new String(bytes, "UTF-8");
                tailListener.mqLine(message,channel,envelope.getDeliveryTag());
            }
        };
        // 指定接收者，第二个参数为自动应答，无需手动应答
        channel.basicConsume(queue, flag, consumer);
    }
}
