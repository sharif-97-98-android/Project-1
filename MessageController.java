package com.example.myapplication;
import java.io.IOException;
import java.util.ArrayList;
public class MessageController {

    StorageManager storageManager;
    ConnectionManager connectionManager;
    private NotificationCenter notificationCenter;
    ArrayList<Integer> messages;
    public MessageController(final NotificationCenter notificationCenter) throws IOException {
        final int[] a = new int[10];
        storageManager = new StorageManager();
        connectionManager = new ConnectionManager(100);
        this.notificationCenter = notificationCenter;
        messages = new ArrayList();
    }
    public void fetch(boolean fromCache) throws InterruptedException, IOException {

            if (fromCache) {
                Thread storage = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            storageManager.load(messages.size());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                storage.start();
                Thread.sleep(1000);
                if (storageManager.message[0] != 0) {
                    int[] x = new int[10];
                    int[] y = new int[10];
                    x = storageManager.message;
                    storageManager.message = y;
                    for (int i = 0; i < 10; ++i) {
                        messages.add(x[i]);
                    }
                    notificationCenter.data_loaded(x);
                }
            }else {
                Thread cloud = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connectionManager.load(messages.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                cloud.start();
                Thread.sleep(connectionManager.time + 1000);
                if (connectionManager.message[0] != 0) {
                    int[] x = new int[10];
                    int[] y = new int[10];
                    x = connectionManager.message;
                    connectionManager.message = y;
                    for (int i = 0; i < 10; ++i) {
                        messages.add(x[i]);
                    }
                    notificationCenter.data_loaded(x);
                    storageManager.save(x[9]);
                }
            }
        }
    }
