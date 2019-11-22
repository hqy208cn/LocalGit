package cn.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cn.entity.Order;

public class SocketService {
	Socket socket;
	long getTime;
	ServerSocket server;
	Order order= new Order();
	public static Boolean flag = false;//判断指令是否更改的标志位
	public SocketService(){

	}
	public void receive(){	 	 
	try {	
		System.out.println("opensocket已开始");	
		server = new ServerSocket(7777);
		System.out.println("serversocket已建立，开始监听...");
		socket = server.accept();
		System.out.println("建立连接完成");
		getTime=System.currentTimeMillis();
		System.out.println("开始计时，1000秒未收到有效数据自动断开");
		while(true){	
			if(flag==true){	//true时，order内的数据发生改变，在此时立刻获取，进行发送			
				send(order.getOrder());
				System.out.println("发送成功");
				flag=false;//发送完毕后，标志位改成false，意味着数据已使用过
			}
		InputStream in = socket.getInputStream();
		if(in.available()>0){
			byte[] bt = new byte[0];
			bt = new byte[in.available()];
	        in.read(bt);	        
	        String str2 = new String(bt, "UTF-8"); 
	        System.out.println("收到灯带信息:"+str2);
	        getTime =  System.currentTimeMillis();		
		}
		if(System.currentTimeMillis()-getTime>1000000){
			System.out.println("1000秒无数据，自动断开");
			break;	
		}
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void send(byte[] bt){
		try{
			OutputStream out = socket.getOutputStream();
			out.write(bt);
			System.out.println("socket获取到"+bt[3]);
			String getStr = new String(bt, "UTF-8");
			System.out.println(getStr);
		System.out.println("flush完成");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
