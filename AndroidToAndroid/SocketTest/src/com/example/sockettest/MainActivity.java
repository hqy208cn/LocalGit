package com.example.sockettest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TextView textview1;
	private TextView textview2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textview1 = (TextView)findViewById(R.id.TextView1);
		textview2 = (TextView)findViewById(R.id.TextView2); 
        new Thread(new Runnable() {
            @Override
            public void run() {
				ServerSocket server;
				try {
					server = new ServerSocket(7777);
					int i = 0;
					while(true){
						Socket socket = server.accept();
						i++;
					startThread(socket,i);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}     
            }
        }).start();
	}	

    private  Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String info= (String) msg.obj;
            if (msg.what==1){
                textview1.append(info);
            }
            if (msg.what==2){
                textview2.append(info);
            }
        }
    };

    private void startThread(Socket socket,final int i){
    	final Socket socket1 = socket;
		Thread thread = new Thread () {
	    	  @Override
	    	  public void run() {
	    		  try {
	    				while(true){
	    				 InputStream in = socket1.getInputStream();
	    				 byte[] bt = new byte[50];
                       in.read(bt);
                       String str2 = new String(bt, "UTF-8"); 
                       Message msg = new Message();
                       msg.what = i;
                       msg.obj = str2;
                       handler.sendMessage(msg);
                       try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    				}
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}	    
	    						    			     
	    	          }
	    	     };
	    	     thread.start();
    }
}
