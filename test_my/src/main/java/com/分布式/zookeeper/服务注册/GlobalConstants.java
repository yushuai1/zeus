package com.分布式.zookeeper.服务注册;

public class GlobalConstants {
    // zk服务器列表
    public static final String zkhosts = "10.3.1.166:2181";
    // 连接的超时时间
    public static final int sessionTimeout = 30000;
    // 服务在zk下的路径
    public static final String parentZnodePath = "/servers";
}
