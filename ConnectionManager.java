package com.example.myapplication;

public class ConnectionManager{
    int firstMessage = 0;
    boolean check = false;
    int[] message = new int[10];
    public void load(int firstMessage) throws InterruptedException {
            Thread.sleep(100);
            for (int i = 0; i < 10; ++i) {
                this.message[i] = firstMessage + i + 1;
            }
    }
}
