package com.io.nio.EchoServer.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.io.nio.EchoServer.ServerConstant.PORT;

/**
 * Created by Administrator on 2017/4/5.
 */
public class MultiThreadBlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true){
            Socket client = serverSocket.accept();
            System.out.println("有新用户连接。");
            new Thread(new Handler(client)).start();
        }
    }

    public static class Handler implements Runnable{

        Socket client;
        PrintStream printStream;
        BufferedReader bufferedReader;

        public Handler(Socket socket){
            this.client=socket;
            try {
                printStream=new PrintStream(client.getOutputStream());
                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while(true){
                try {
                    String str = bufferedReader.readLine()+"\n";
                    printStream.write(str.getBytes());
                } catch (IOException e) {
                    try {
                        client.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }finally {
                        return;
                    }
                }
            }
        }
    }
}
