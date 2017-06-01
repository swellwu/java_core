package com.io.nio.EchoServer.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Objects;

import static com.io.nio.EchoServer.ServerConstant.ADDRESS;
import static com.io.nio.EchoServer.ServerConstant.EXIT;
import static com.io.nio.EchoServer.ServerConstant.PORT;

/**
 * Created by Administrator on 2017/4/1.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket(ADDRESS,PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(client.getOutputStream());
        BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while(true){
            System.out.print("client:");
            String str = input.readLine()+"\n";
            out.write(str.getBytes());
            String echo = buf.readLine()+"\n";
            System.out.print("server:"+echo);
            if(Objects.equals(echo,EXIT)) break;
        }
        input.close();
        client.close();
    }
}
