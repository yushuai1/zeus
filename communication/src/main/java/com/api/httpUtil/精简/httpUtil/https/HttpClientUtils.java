package com.api.httpUtil.精简.httpUtil.https;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;


public class HttpClientUtils {


    /**
     *  获取安全的加密（Https）的HttpClient
     * @return
     */
    public static HttpClient getTLSHttpClient(){
        SSLContext sslcontext = null;
        try {

            sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(new File("E:\\rabbittruststore.jks"),
                            "yushuai".toCharArray(), new TrustSelfSignedStrategy())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[] { "TLSv1" }, null,
                NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpclient;
    }

    public static void main(String[] as) throws IOException {

        HttpClient httpClient= getTLSHttpClient();
        HttpGet httpget = new HttpGet("https://127.0.0.1:1997/test/sada45");
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity httpEntity= response.getEntity();
        String strResult= EntityUtils.toString(httpEntity);
        System.out.println(strResult);

    }
}
