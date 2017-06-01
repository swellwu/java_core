package com.io.nio.EchoServer.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

import static com.io.nio.EchoServer.ServerConstant.PORT;
import static java.nio.channels.SelectionKey.*;

/**
 * Created by Administrator on 2017/4/1.
 */
public class NIOServer implements Runnable{

    private static final int BUFFSIZE = 1024;
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(BUFFSIZE);
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BUFFSIZE);
    private InetSocketAddress address;

    public NIOServer(int port){
        address=new InetSocketAddress(port);
    }

    public NIOServer(InetSocketAddress address){
        this.address=address;
    }

    public static void main(String[] args) {
        new Thread(new NIOServer(PORT)).start();
    }

    @Override
    public void run(){
        try{
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            //绑定监听地址
            ServerSocket serverSocket=serverSocketChannel.socket();
            serverSocket.bind(this.address);
            //注册接受事件
            Selector selector=Selector.open();
            serverSocketChannel.register(selector, OP_ACCEPT);
            while (selector.select()>0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey sKey = it.next();
                    it.remove();
                    if(!sKey.isValid()) continue;
                    //有accept事件发生
                    if (sKey.isAcceptable()) {
                        System.out.println("isAcceptable");
                        ServerSocketChannel ssc = (ServerSocketChannel)sKey.channel();
                        SocketChannel client = ssc.accept();
                        client.configureBlocking(false);
                        client.register(selector,OP_READ|OP_WRITE);
                    }
                    if(sKey.isReadable()){
                        System.out.println("isReadable");
                        SocketChannel socketChannel = (SocketChannel)sKey.channel();
                        while (true){
                            receiveBuffer.clear();
                            int count = socketChannel.read(receiveBuffer);
                            if(count>0){
                                sendBuffer.clear();
                                sendBuffer.put(receiveBuffer.array(),0,count);
                                sendBuffer.flip();
                                socketChannel.write(sendBuffer);
                            }else break;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
