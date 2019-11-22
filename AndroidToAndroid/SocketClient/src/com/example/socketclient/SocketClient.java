package com.example.socketclient;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class SocketClient {
    private Socket clientSocket;
    private Context mContext;
    private String site;//端口
    private PrintWriter out;
    private InputStream mInputStream;
    private String getStr;
    private int port;//IP
    private boolean isClient = false;
    public static Handler ClientHandler;
    private static final String TAG_LOG = "SocketClient_debug";

    public SocketClient(Context context, String site, int port) {
        this.mContext = context;
        this.site = site;
        this.port = port;
    }

    //@effect 开启线程建立连接开启客户端
    public void openClientThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(site, port);//connect()步骤
//                    client.setSoTimeout ( 5000 );//设置超时时间
//                    clientSocket = new Socket();
//                    SocketAddress socketAddress = new InetSocketAddress(site, port);
//                    clientSocket.connect(socketAddress, 3000);
                    if (clientSocket != null) {
                        isClient = true;
                        getOutString();
                        getInString();
                    } else {
                        isClient = false;
                        Toast.makeText(mContext, "网络连接失败 openClientThread", Toast.LENGTH_LONG).show();
                    }
                    Log.d(TAG_LOG, "site = " + site + " , port = " + port);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    Log.d(TAG_LOG, "UnknownHostException");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG_LOG, "IOException");
                }

            }
        }).start();
    }

    //得到输出字符串
    public void getOutString() {
        Log.d(TAG_LOG, "getOutString() invoked");
        try {
            out = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG_LOG, "getOutString() IOException");
        }
    }

    /**
     * @steps read();
     * @effect 得到输入字符串
     */
    public void getInString() {
        Log.d(TAG_LOG, "getInString() invoked");
        while (isClient) {
            try {
                mInputStream = clientSocket.getInputStream();
                byte[] bt = new byte[50];//得到的是16进制数，需要进行解析
                mInputStream.read(bt);
                getStr = new String(bt, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (getStr != null) {
                Message msg = new Message();
                msg.obj = getStr;
                ClientHandler.sendMessage(msg);
            }
        }
    }

    //@steps write();    @effect 发送消息
    public void sendMsg(final String str) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (clientSocket != null) {
                    out.print(str);
                    out.flush();
                    Log.d(TAG_LOG, "client send message : " + str);
                } else {
                    isClient = false;
                }
            }
        }).start();
    }
}