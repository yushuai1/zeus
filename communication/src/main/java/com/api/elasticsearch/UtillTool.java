package com.api.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class UtillTool {

    private volatile static RestClient restClient;
    private volatile static TransportClient client;

    public void getRestClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "changeme"));

        restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();

    }


    public static RestClient getRestEs(String ip) {
        if (restClient == null) {
            synchronized (RestClient.class) {
                if (restClient == null) {
                    restClient = RestClient.builder(
                            new HttpHost("192.168.159.128", 9200, "http")).build();
                }
            }
        }
        return restClient;
    }


    public static TransportClient openCluster() {
        Settings settings = Settings.builder().put("cluster.name", "yushuai")
                .put("client.transport.sniff", true).build();

        try {
            client = new PreBuiltTransportClient(settings);
          //  client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.159.128"), 9300));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return client;
    }

    public static TransportClient openNoCluster() {

        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.159.128"), 9300));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return client;
    }
}
