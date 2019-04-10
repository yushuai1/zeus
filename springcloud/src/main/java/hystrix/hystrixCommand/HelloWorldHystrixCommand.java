package hystrix.hystrixCommand;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class HelloWorldHystrixCommand extends HystrixCommand<String> {
    private final String name;
    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //Thread.sleep(100);
        return "hello"+name;
    }
    public static void testQueue() throws Exception {
        // queue()是异步非堵塞性执行：直接返回，同时创建一个线程运行HelloWorldHystrixCommand.run()
        // 一个对象只能queue()一次
        // queue()事实上是toObservable().toBlocking().toFuture()
        Future<String> future = new HelloWorldHystrixCommand("Hlx").queue();

        // 使用future时会堵塞，必须等待HelloWorldHystrixCommand.run()执行完返回
        String queueResult = future.get(10000, TimeUnit.MILLISECONDS);
        // String queueResult = future.get();
        System.out.println("queue异步结果：" + queueResult);

    }
    public static void main(String[] args) throws Exception {
        String result = new HelloWorldHystrixCommand("test").execute();
        System.out.println(result);
        testQueue();
    }
}