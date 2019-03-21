package com.工作测试.网络下载图片;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetTest {

    public static  void main(String [] args) {

        String urlpath = "http://a.hiphotos.baidu.com/image/pic/item/4034970a304e251fb59344f5a586c9177f3e5352.jpg";
        try {
            byte[] imgByte = getImage(urlpath);
            System.out.println(imgByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[] getImage(String urlpath) throws Exception{  //首先得到请求路径,并抛出异常

        byte[] result=null;
        URL url = new URL(urlpath);
        HttpURLConnection conn =(HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5*1000);
        if(conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            result= readInstream(inputStream);
        }

        return result;
    }

    private static byte[] readInstream(InputStream inputStream) throws Exception {

        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;

        while((length=inputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,length);
            //把缓存区中输出到内存中
        };
        byteArrayOutputStream.close();  //关闭输出流
        inputStream.close();          //关闭输入流

        return byteArrayOutputStream.toByteArray();
        //返回这个输出流的字节数组
    }
}