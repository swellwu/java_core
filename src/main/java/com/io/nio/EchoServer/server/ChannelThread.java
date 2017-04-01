package com.io.nio.EchoServer.server;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2017/4/1.
 */
public class ChannelThread implements Runnable{

    private static final int BUFFSIZE = 1024;
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(BUFFSIZE);
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BUFFSIZE);

    private SocketChannel socketChannel;

    public ChannelThread(SocketChannel sc){
        this.socketChannel=sc;
    }

    @Override
    public void run() {
        try {
            while (true){
                receiveBuffer.clear();
                int count = socketChannel.read(receiveBuffer);
                if(count>0){
                    String receiveText = new String(receiveBuffer.array(),0,count);
                    System.out.println("服务器端接受客户端数据--:" + receiveText);
                    sendBuffer.clear();
                    sendBuffer.put(receiveText.getBytes());
                    sendBuffer.flip();
                    socketChannel.write(sendBuffer);
                }
            }
        }catch (Exception e){

        }
    }
}
