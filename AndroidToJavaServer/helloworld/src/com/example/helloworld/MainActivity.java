package com.example.helloworld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText mEditText;
    private TextView mTextView;
    private static final String TAG = "TAG";
    private static final String HOST = "192.168.1.102";
    private static final int PORT = 9999;
    private PrintWriter printWriter;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    private String receiveMsg;
    private Button button1;
    private Button button2;
    private Button button3;
    byte[] bt = new byte[50];
    private InputStream mInputStream;
    private String getStr;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				connect();
			};
        });         
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				send();
			};
        });     
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				disconnect();
			};
        });     
        mExecutorService = Executors.newCachedThreadPool();
    }
 
    public void connect() {
        mExecutorService.execute(new connectService());  //��һ���µ��߳������� Socket ����
    }

    public void send() {
        String sendMsg = mEditText.getText().toString();
        mExecutorService.execute(new sendService(sendMsg));
    }
 
    public void disconnect() {
        mExecutorService.execute(new sendService("0"));
    }
 
    private class sendService implements Runnable {
        private String msg;
 
        sendService(String msg) {
            this.msg = msg;
        }
 
        @Override
        public void run() {
            printWriter.println(this.msg);
        }
    }
 
    private class connectService implements Runnable {
        @Override
        public void run() {//���Կ����ڴ˴����һ��whileѭ������������catch��䣬ʵ��Socket�����ȡʧ�ܺ�ĳ�ʱ������ֱ���ɹ�����Socket����
            try {
                Socket socket = new Socket(HOST, PORT);      //����һ
                socket.setSoTimeout(60000);
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(   //�����
                        socket.getOutputStream(), "UTF-8")), true);
                //in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                mInputStream = socket.getInputStream();
                receiveMsg();
            } catch (Exception e) {
                Log.e(TAG, ("��װ����"+"connectService:" + e.getMessage())); 
               //���Socket�����ȡʧ�ܣ������ӽ���ʧ�ܣ����ߵ�����߼�
            }
        }
    }
 
    private void receiveMsg() {
        try {
            while (true) {                                      //������
                mInputStream.read(bt);
                getStr = new String(bt, "UTF-8");
                    Log.d(TAG, "���ձ���"+"receiveMsg:" + receiveMsg);
                  
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(getStr + "\n\n" + mTextView.getText());
                        }
                    });
                }
            
        } catch (IOException e) {
            Log.e(TAG, "receiveMsg: ");
            e.printStackTrace();
        }
    }
}