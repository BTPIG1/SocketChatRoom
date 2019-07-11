package com.lyb.server;

import com.lyb.constant.Constant;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/11 14:16
 * @Description:
 */
public class Server {

    public static void main(String[] args) {
        start();
    }

    private static void start() {

        List<Socket> socketClientList;
        ServerSocket serverSocket;
        try {

            socketClientList = new ArrayList<>();

            serverSocket = new ServerSocket(Constant.PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("被堵塞了...");
                socketClientList.add(socket);
                ServerThread serverThread = new ServerThread(socket,socketClientList);
                serverThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
