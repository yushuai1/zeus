//package com.分布式.zookeeper.master的选主;
//
//import org.I0Itec.zkclient.ZkClient;
//import org.I0Itec.zkclient.serialize.SerializableSerializer;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooKeeper;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by nevermore on 16/6/23.
// */
//public class LeaderSelectorZkClient implements Watcher {
//
//    //启动的服务个数
//    private static final int        CLIENT_QTY = 10;
//    //zookeeper服务器的地址
//    private static final String     ZOOKEEPER_SERVER = "10.3.1.166:2181";
//
//
//    public static void main(String[] args) throws Exception{
//        //保存所有zkClient的列表
//        List<ZooKeeper> clients = new ArrayList<ZooKeeper>();
//        //保存所有服务的列表
//        List<WorkServer>  workServers = new ArrayList<WorkServer>();
//
//        try{
//            for ( int i = 0; i < CLIENT_QTY; ++i ){
//                //创建zkClient
//                ZooKeeper client = new ZooKeeper(ZOOKEEPER_SERVER, 30000, new Watcher() {
//                    @Override
//                    public void process(WatchedEvent watchedEvent) {
//
//                    }
//                });
//                clients.add(client);
//                //创建serverData
//                RunningData runningData = new RunningData();
//                runningData.setCid(Long.valueOf(i));
//                runningData.setName("Client #" + i);
//                //创建服务
//                WorkServer  workServer = new WorkServer(runningData);
//                workServer.setZkClient(client);
//
//                workServers.add(workServer);
//                workServer.start();
//            }
//
//            System.out.println("敲回车键退出！\n");
//            new BufferedReader(new InputStreamReader(System.in)).readLine();
//        }finally{
//            System.out.println("Shutting down...");
//
//            for ( WorkServer workServer : workServers ){
//                try {
//                    workServer.stop();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            for ( ZkClient client : clients ){
//                try {
//                    client.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void process(WatchedEvent watchedEvent) {
//
//    }
//}