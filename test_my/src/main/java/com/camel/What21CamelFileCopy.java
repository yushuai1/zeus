package com.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class What21CamelFileCopy {

    static class FileCopyRouteBuilder extends RouteBuilder{

        private int second = 30 * 1000;

        // 源，参数delay，每隔30秒轮询一次
        String source = "file:D:/Camel/inbox?delay=" + second;
        // 目标
        String target = "file:D:/Camel/outbox";

        @Override
        public void configure() throws Exception {
            from(source).to(target);
        }

    }

    public static void main(String args[]) {
        // 配置路由的上下文
        CamelContext context = new DefaultCamelContext();
        // 路由规则构建
        RouteBuilder routeBuilder = new FileCopyRouteBuilder();
        try {
            context.addRoutes(routeBuilder);
            // 启动
            context.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadSleep(25000);
        try {
            // 停止
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void threadSleep(long time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}