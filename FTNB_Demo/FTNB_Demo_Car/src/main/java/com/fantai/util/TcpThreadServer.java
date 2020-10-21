package com.fantai.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpThreadServer {
    private static final int PORT = 6666;

    public void start() throws Exception{
        ServerSocket ss = new ServerSocket(PORT);
        while (true) {
            Socket s = ss.accept();
            Thread t = new Thread(new TCPThreadServerSocket(s));
            t.start();
        }
    }

    public static void main(String[] args) {
        int count = 1;
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("服务器已经启动。。。");
            while (true) {
                Socket s = ss.accept();
                System.out.println("第" + count + "个连接,IP地址是："
                        + s.getInetAddress());
                count++;
                Thread t = new Thread(new TCPThreadServerSocket(s));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
