package com.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2017/3/31.
 */
public class NIOBase {

    String srcName = "data.txt";
    String destName = "dest.txt";

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public void printFile(String name) throws IOException {
        String path = getPath(name);
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel inChannel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(256);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        file.close();
    }

    public void fileChannel() throws IOException {
        printFile(srcName);
    }

    public void emptyDestFile() throws IOException {
        String path =getPath(destName);
        File file = new File(path);
        file.createNewFile();
    }

    private String getPath(String name){
        return Thread.currentThread().getContextClassLoader().getResource(name).getPath();
    }

    public void channelTransfer() throws IOException {
        String srcPath = getPath(srcName);
        RandomAccessFile fromFile = new RandomAccessFile(srcPath, "rw");
        FileChannel fromChannel = fromFile.getChannel();
        String destPath = getPath(destName);
        RandomAccessFile toFile = new RandomAccessFile(destPath, "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel,position,count);

    }
}
