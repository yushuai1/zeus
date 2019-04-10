package hystrix.服务降级;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HystrixFallbackNomal extends HystrixCommand<String> {

    private final String name;

    public HystrixFallbackNomal(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    public String run() throws Exception {
        /*---------------会触发fallback的case-------------------*/
        // 无限循环，实际上属于超时
    /*  int j = 0;
        while (true) {
            j++;
        }*/
        Thread.sleep(1000);

        // 除零异常
        // int i = 1/0;

        // 主动抛出异常
//      throw new HystrixTimeoutException();
//      throw new RuntimeException("this command will trigger fallback");
//      throw new Exception("this command will trigger fallback");
//      throw new HystrixRuntimeException(FailureType.BAD_REQUEST_EXCEPTION, commandClass, message, cause, fallbackException);

        /*---------------不会触发fallback的case-------------------*/
        // 被捕获的异常不会触发fallback
//      try {
//          throw new RuntimeException("this command never trigger fallback");
//      } catch(Exception e) {
//          e.printStackTrace();
//      }

        // HystrixBadRequestException异常由非法参数或非系统错误引起，不会触发fallback，也不会被计入熔断器
        //throw new HystrixBadRequestException("HystrixBadRequestException is never trigger fallback");

        return name;
    }

    @Override
    protected String getFallback() {
        return "fallback: " + name;
    }
}