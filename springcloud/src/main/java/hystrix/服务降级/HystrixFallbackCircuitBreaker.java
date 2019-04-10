package hystrix.服务降级;

import com.netflix.hystrix.*;

import java.io.IOException;
import java.util.Map;

/**
 * 熔断机制相当于电路的跳闸功能，例如：我们可以配置熔断策略为当请求错误比例在10s内>50%时，该服务将进入熔断状态，后续请求都会进入fallback
 * CircuitBreakerRequestVolumeThreshold设置为3，意味着10s内请求超过3次就触发熔断器(10s这个时间暂时不可配置)
 * run()中无限循环使命令超时进入fallback，10s内请求超过3次，将被熔断，进入降级，即不进入run()而直接进入fallback
 * 如果未熔断，但是threadpool被打满，仍然会降级，即不进入run()而直接进入fallback
 */
public class HystrixFallbackCircuitBreaker extends HystrixCommand<String> {
    private final String name;

    public HystrixFallbackCircuitBreaker(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerTestGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakerTestKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakerTest"))
                        .andThreadPoolPropertiesDefaults(   // 配置线程池
                                HystrixThreadPoolProperties.Setter()
                                        .withCoreSize(200)  // 配置线程池里的线程数，设置足够多线程，以防未熔断却打满threadpool
                        )
                        .andCommandPropertiesDefaults(  // 配置熔断器
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerEnabled(true)
                                        .withCircuitBreakerRequestVolumeThreshold(3)
                                        .withCircuitBreakerErrorThresholdPercentage(80)
//                          .withCircuitBreakerForceOpen(true)  // 置为true时，所有请求都将被拒绝，直接到fallback
//                          .withCircuitBreakerForceClosed(true)    // 置为true时，将忽略错误
//                          .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)   // 信号量隔离
//                          .withExecutionTimeoutInMilliseconds(5000)
                        )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("running run():" + name);
        int num = Integer.valueOf(name);
        if (num < 10) {   // 直接返回
            return name;
        } else {    // 无限循环模拟超时
            int j = 0;
            while (true) {
                j++;
            }
        }
//        return name;
    }

    @Override
    protected String getFallback() {
        return "CircuitBreaker fallback: " + name;
    }


    public static void main(String[] a) throws IOException {
        for (int i = 0; i < 50; i++) {
            try {
                System.out.println("===========" +
                        new HystrixFallbackCircuitBreaker(String.valueOf(i)).execute());
            } catch (Exception e) {
                System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
            }
        }

        System.out.println("------开始打印现有线程---------");
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Thread thread : map.keySet()) {
            System.out.println(thread.getName());
        }
        System.out.println("thread num: " + map.size());

        System.in.read();
    }
}