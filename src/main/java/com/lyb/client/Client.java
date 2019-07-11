package com.lyb.client;

import com.lyb.constant.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/11 14:52
 * @Description:
 */
public class Client {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        try {

            Socket socket = new Socket(Constant.HOST, Constant.PORT);

            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;
            while ((msg = bufferedReader.readLine()) != null) {

                System.out.println(msg);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
