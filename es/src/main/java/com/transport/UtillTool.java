package com.transport;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class UtillTool {

    private volatile static TransportClient client = null;

    public static TransportClient getEsClient(String host, String cluserName) {
        if (client == null) {
            synchronized (TransportClient.class) {
                if (client == null) {
                    try {
                        // 设置集群名称
                        Settings settings = Settings.builder().put("cluster.name", cluserName).
                                put("client.transport.sniff", true).build();
                        // 创建client
                        client = new PreBuiltTransportClient(settings)
                                .addTransportAddress(new TransportAddress(InetAddress.getByName(host), 9300));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return client;
    }

    public static void close(){
        client.close();
    }
}
