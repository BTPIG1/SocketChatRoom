package com.lyb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/11 14:21
 * @Description:
 */
public class ServerThread extends Thread{

    private Socket socket;

    private String msg;

    private List<Socket> socketClientList;

    public ServerThread(Socket socket,List<Socket> socketClientList) {
        this.socket = socket;
        this.socketClientList = socketClientList;
    }

    public void run() {
        try {

            System.out.println("进入线程");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            msg = "欢迎【" + socket.getInetAddress() + "】进入聊天室！当前聊天室有【" + socketClientList.size() + "】人";

            sendMsg();

            while ((msg = bufferedReader.readLine()) != null) {

                msg = String.format("【%s】说:%s", socket.getInetAddress(), msg);

                sendMsg();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg() {

        System.out.println(msg);

        try {

            for (int i = socketClientList.size() - 1; i >= 0; i--) {
                PrintWriter printWriter = new PrintWriter(socketClientList.get(i).getOutputStream(), true);
                printWriter.println(msg);
                printWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
