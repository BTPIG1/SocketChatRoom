package com.lyb.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/11 14:53
 * @Description:
 */
public class ClientThread extends Thread{

    private Socket socket;

    ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            String msg;

            while (true) {

                msg = bufferedReader.readLine();
                printWriter.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
