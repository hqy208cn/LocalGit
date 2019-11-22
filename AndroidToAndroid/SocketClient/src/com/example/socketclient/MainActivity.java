package com.example.socketclient;

import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView client_textView;
    private EditText client_editText;
    private Button client_button;

    private SocketClient client;
    private static final String TAG_LOG = "SocketClient_Actdebug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client_textView = (TextView) findViewById(R.id.textView1);
        client_editText = (EditText) findViewById(R.id.editText1);
        client_button = (Button) findViewById(R.id.button1);

        client = new SocketClient(this, "192.168.1.102", 7777);//服务端的IP地址和端口号
        client.openClientThread();//开启客户端接收消息线程

        client_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.sendMsg(client_editText.getText().toString().trim());//发送消息
            }
        });

        //接受消息
        SocketClient.ClientHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                client_textView.setText(msg.obj.toString());
                Log.d(TAG_LOG, msg.obj.toString());
            }
        };
    }
}
