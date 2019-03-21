package pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class Tcp {

    private static final String ip = "127.0.0.1";
    private static final int tcpport = 8084;

    private BytesUtil bytesUtil = new BytesUtil();

    /**
     * Socket客户端
     */
    public  byte[] sentTcp(byte[] arg) {
        byte[] data=null;
        try {
            //创建Socket对象
            Socket socket=new Socket(ip,tcpport);

            //根据输入输出流和服务端连接
            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息

//            byte[] src = arg.getBytes(Charset.forName("UTF-8"));
            //把前四个字节当成长度
            outputStream.write(bytesUtil.getBytesFromLen(arg.length));
            //内容
            outputStream.write(arg);
            outputStream.flush();
            socket.shutdownOutput();//关闭输出流

            //获取返回结果
            InputStream is=socket.getInputStream();//获取一个输入流，接收服务端的信息
            byte[] datalenbyte = new byte[BytesUtil.headLen];
            is.read(datalenbyte);
            int length = bytesUtil.getLenFromBytes(datalenbyte);//将字节数组转为字符串，再转为int类型
            data = new byte[length];
            is.read(data);
//            info = new String(data);//将获得数据转为字符串类型

            is.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
