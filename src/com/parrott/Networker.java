package com.parrott;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Networker {
    public static Socket socket;
    public static DataOutputStream outputStream;

    public static boolean doneDirections = false;

    public static final Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            connect();
            sendMode();
            while (!doneDirections) {
                sendMode();

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });

    public static void sendLeftSpeed(int leftSpeed){
        byte[] bytes = {0x00, 0x31, (byte)(short) leftSpeed};
        write(bytes);
    }

    public static void sendRightSpeed(int rightSpeed){
        byte[] bytes = {0x00, 0x32, (byte)(short) rightSpeed};
        write(bytes);
    }

    private static void sendMode(){
        byte[] bytes = {0x00, 0x2b, 0x00};
        write(bytes);
    }

    private static boolean done = false;

    public static synchronized void write(byte[] bytes){
        if (done) {
            System.out.println(bytes);
            try {
                outputStream.write(bytes);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            write(bytes);
        }
    }

    public static String address;
    public static int port;

    public static void start(String address, int port){
        Networker.address = address;
        Networker.port = port;
        thread.start();
    }

    private static void connect(){
        boolean connected = true;
        do {
            try {
                socket = new Socket(address, port);
                outputStream = new DataOutputStream(socket.getOutputStream());
                connected = false;
                done = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (connected);
    }

}
