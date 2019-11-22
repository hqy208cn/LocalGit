package cn.util;

import java.io.IOException;
 
 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;

import cn.service.SocketService;
 
 public class SocketListener implements ServletContextListener {//监听类保证项目一启动就执行线程中的内容
 
     private MyThread myThread;
     @Override
     public void contextDestroyed(ServletContextEvent arg0) {
         if (myThread!=null&&myThread.isInterrupted()) {
             myThread.interrupt();
         }
 
     }
 
     @Override
     public void contextInitialized(ServletContextEvent arg0) {
         String str =null;
         if (str==null&&myThread==null) {
             myThread=new MyThread();
             myThread.start();//servlet上下文初始化时启动socket
         }
     }
     
          /*
      * 自定义一个Class线程类继承自线程类，重写run()方法，用于从后台获取处理数据
      * 
      */
     class MyThread extends Thread{
         public void run() {
             while (!this.isInterrupted()) {//线程未中断执行循环
                 try {
                     Thread.sleep(10000);//每隔2000ms执行一次
                     
                     
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 
                 //----------------------开始执行---------------------
                 System.out.println("TIME:"+System.currentTimeMillis());
                 SocketService socketservice = new SocketService();  
                 socketservice.receive();            
                 System.out.println("下一轮线程开始");
                 socketservice.close();
             }
         }
     }

 }