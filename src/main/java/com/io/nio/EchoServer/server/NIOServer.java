package com.io.nio.EchoServer.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/1.
 */
public class NIOServer implements Runnable{
    private InetSocketAddress address;

    public NIOServer(int port){
        address=new InetSocketAddress(port);
    }

    public NIOServer(InetSocketAddress address){
        this.address=address;
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
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey sKey = it.next();
                    it.remove();
                    //有accept事件发生
                    if (sKey.isAcceptable()) {
                        ServerSocketChannel serv = (ServerSocketChannel) sKey.channel();
                        SocketChannel sc = serv.accept();
                        sc.configureBlocking(true);
                        new Thread(new ChannelThread(sc)).start();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
