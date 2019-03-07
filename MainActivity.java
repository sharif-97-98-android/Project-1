package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;
//import android.widget
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    NotificationCenter notificationCenter;
    MessageController messageController;
    //public MainActivity(){

    //}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationCenter = new NotificationCenter();
        notificationCenter.register(this);
        messageController = new MessageController(notificationCenter);
        Button get=(Button) findViewById(R.id.get);
        final Button refresh=(Button)findViewById(R.id.refresh);
        Button clear=(Button)findViewById(R.id.clear);
        final RelativeLayout my_layout=(RelativeLayout)findViewById(R.id.my_layout);
        clear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                RelativeLayout layout=(RelativeLayout) findViewById(R.id.my_layout);
                LinearLayout list =findViewById(R.id.list);
                list.removeView(findViewById(1));
                list.removeView(findViewById(2));
                list.removeAllViews();
                messageController.messages.clear();

            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                System.out.println("in main activity");
                try {
                    messageController.fetch(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                try {
                    messageController.fetch(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
    @SuppressLint("ResourceType")
    @Override
    public void update(Observable o, Object message) {
        System.out.println("in update of main activity");
        int [] messages = (int[]) message;
        TextView textView=new TextView(getApplicationContext());
        textView.setGravity(1);
        textView.setId(1);
        StringBuilder numList=new StringBuilder();
        LinearLayout layout=findViewById(R.id.list);

        for (int i = 0; i <messages.length; i++) {
            numList.append(messages[i]);
            numList.append("\n");
        }
        textView.setText(numList);
        layout.addView(textView);
    }
}
