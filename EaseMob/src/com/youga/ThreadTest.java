package com.youga;

import java.util.Random;

public class ThreadTest {


    public static void main(String[] args) {


        Thread thread = new Thread();
        sendDeviceTokenToServer(thread, true);
        System.out.println("哈哈哈哈");

        sendDeviceTokenToServer(thread, true);


        System.out.println("额呵呵呵呵");
    }

    public static void sendDeviceTokenToServer(Thread thread, boolean b) {
        try {
            if (b) {
                if (thread == null) {
                    thread = new Thread() {
                        public void run() {

                        }
                    };
                }
                thread.start();
            }
        } catch (Exception e) {
//            var2.printStackTrace();
        }
    }

}
