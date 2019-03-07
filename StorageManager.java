package com.example.myapplication;

public class StorageManager{
    private int lastmessage = 0;
    int[] message = new int[10];

    public StorageManager(){
    }

    public void load(int firstmessage){
        if(firstmessage < lastmessage) {

            int[] messages = new int[10];
            for (int i = firstmessage; i < firstmessage + 10; ++i) {
                message[i - firstmessage] = i + 1;
            }
        }
    }
    public void save(int lastmessage){
        this.lastmessage = lastmessage;

    }
}
