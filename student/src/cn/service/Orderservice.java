  package cn.service;

import java.sql.ResultSet;

import cn.entity.Order;
import cn.entity.Project;
import cn.service.impl.ProServiceimp;
import cn.util.ProjectIntToString;

public class Orderservice {
	boolean flagphone1 = false;
	boolean flagphone2 = false;
	boolean flagphone3 = false;
	boolean flagphone4 = false;
	Thread thread1;
	Thread thread2;
	Thread thread3;
	Thread thread4;
	Order order1= new Order();
	ProService proservice = new ProServiceimp();
	//ResultSet rs = proservice.getPro();
	byte[] bt={90,00,00,00,00,00,00,00,00,00,00,00,00,91};
	public void addOrder(String order){
		int phonenumber = asciiToInt(order.charAt(2));
		int devicenumber = asciiToInt(order.charAt(3));
		int speed1 = asciiToInt(order.charAt(8));
   		int speed2 = asciiToInt(order.charAt(9));
   		int speed = speed1*16+speed2;
		if((!phonenumberToFlag(phonenumber))&&!(devicenumber==0)){
			turnOnFlag(phonenumber);
			System.out.println("进程开始");
			switch(phonenumber){
			case 1: startThread1(order,phonenumber);
			break;
			case 2: startThread2(order,phonenumber);
			break;
			case 3: startThread3(order,phonenumber);
			break;
			case 4: startThread4(order,phonenumber);
			break;
			}		
		}
		//else if((phonenumberToFlag(phonenumber))&&!(devicenumber==0)){ //动态修改速度
		 // bt[phonenumber*3]=(byte)speed;
		 //order1.setOrder(bt);
		 // 
		//}
		else if((phonenumberToFlag(phonenumber))&&devicenumber==0){
			//设备停止
			phonenumberToThread(phonenumber).stop();
			bt[phonenumber*3-2]=00;
			bt[phonenumber*3-1]=00;
			bt[phonenumber*3]=00;
			 order1.setOrder(bt);
			System.out.println("进程停止");
			turnOffFlag(phonenumber);
		}
	}
    private void startThread1(String order,int phonenumber){
			  thread1 = new Thread () {
	    	  @Override
	    	  public void run() {
	    		  int devicenumber = asciiToInt(order.charAt(3));
	    		  int all = phonenumber*16+devicenumber;
	    		  int model1 = asciiToInt(order.charAt(4));
	    		  int model2 = asciiToInt(order.charAt(5));
	    		  int model =model1*16+model2;
	    		  int location1 = asciiToInt(order.charAt(6));
	    		  int location2 = asciiToInt(order.charAt(7));
	    		  int location = location1*16+location2;
	    		  int speed1 = asciiToInt(order.charAt(8));
	    		  int speed2 = asciiToInt(order.charAt(9));
	    		  int speed = speed1*16+speed2;
	    		  ProjectIntToString its = new ProjectIntToString();
	    		  String proname = its.findPro(model);	
	    		  Project pro = new Project();
	    		  ProService proservice = new ProServiceimp();
	    			pro= proservice.getPro(proname);
    			  bt[phonenumber*3-2] = (byte)all;
	    		  bt[phonenumber*3-1] = (byte)location;
	    		  bt[phonenumber*3] = (byte)speed;	
	    		  System.out.println("写入新指令"+bt[3]);
	    		  order1.setOrder(bt);
	    		  try {
					sleep(10000);
				} catch (InterruptedException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}
	    		  System.out.println("新指令写入完成");
	    		  System.out.println("指令为："+phonenumber+","+devicenumber+","+model+","+location+","+speed);
	    		  System.out.println(bt[1]+","+bt[2]+","+bt[3]);
	    			int e1 = pro.gete1();
	    			int r1 = pro.getr1();
	    			int e2 = pro.gete2();
	    			int r2 = pro.getr2();
	    			int e3 = pro.gete3();
	    			int r3 = pro.getr3();
	    			int e4 = pro.gete4();
	    			int r4 = pro.getr4();
	    			int e5 = pro.gete4();
	    			int r5 = pro.getr4();
	    			int e6 = pro.gete4();
	    			int r6 = pro.getr4();
	    			int e7 = pro.gete4();
	    			int r7 = pro.getr4();
	    			int e8 = pro.gete4();
	    			int r8 = pro.getr4();
	    			int e9 = pro.gete4();
	    			int r9 = pro.getr4();
	    			int e10 = pro.gete4();
	    			int r10 = pro.getr4();
	    			int e11 = pro.gete4();
	    			int r11 = pro.getr4();
	    			int e12 = pro.gete4();
	    			int r12 = pro.getr4();
		    		  System.out.println("e1~e4:"+e1+","+e2+","+e3+","+e4+","+r1+","+r2+","+r3+","+r4);
	    			if(!(e1==0)){
	    				 System.out.println("开始执行e1");
	    				bt[phonenumber*3] = (byte)e1;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r1*1000);   					
	    					System.out.println("e1执行完毕");
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}
	    			if(!(e2==0)){
	    				System.out.println("开始执行e2");
	    				bt[phonenumber*3] = (byte)e2;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r2*1000);	    					
	    					System.out.println("e2执行完毕");
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}
	    			if(!(e3==0)){
	    				System.out.println("开始执行e3");
	    				bt[phonenumber*3] = (byte)e3;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r3*1000);
	    					System.out.println("e3执行完毕");
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}
	    			if(!(e4==0)){
	    				bt[phonenumber*3] = (byte)e4;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r4*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			}   	
	    			if(!(e5==0)){
	    				bt[phonenumber*3] = (byte)e5;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r5*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			if(!(e6==0)){
	    				bt[phonenumber*3] = (byte)e6;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r6*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			if(!(e7==0)){
	    				bt[phonenumber*3] = (byte)e7;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r7*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			if(!(e8==0)){
	    				bt[phonenumber*3] = (byte)e8;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r8*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			if(!(e9==0)){
	    				bt[phonenumber*3] = (byte)e9;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r9*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			if(!(e10==0)){
	    				bt[phonenumber*3] = (byte)e10;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r10*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			if(!(e11==0)){
	    				bt[phonenumber*3] = (byte)e11;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r11*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			if(!(e12==0)){
	    				bt[phonenumber*3] = (byte)e12;
	    				try {
	    					order1.setOrder(bt);
	    					sleep(r12*1000);    					
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	    			} 
	    			turnOffFlag(phonenumber);
	    	          }
	    	     };
	    	     
	    	     thread1.start();
    }
    private void startThread2(String order,int phonenumber){
		  thread2 = new Thread () {
  	  @Override
  	  public void run() {
  		  int devicenumber = asciiToInt(order.charAt(3));
  		  int all = phonenumber*16+devicenumber;
  		  int model1 = asciiToInt(order.charAt(4));
  		  int model2 = asciiToInt(order.charAt(5));
  		  int model =model1*16+model2;
  		  int location1 = asciiToInt(order.charAt(6));
  		  int location2 = asciiToInt(order.charAt(7));
  		  int location = location1*16+location2;
  		  int speed1 = asciiToInt(order.charAt(8));
  		  int speed2 = asciiToInt(order.charAt(9));
  		  int speed = speed1*16+speed2;
  		  ProjectIntToString its = new ProjectIntToString();
  		  String proname = its.findPro(model);	
  		  Project pro = new Project();
  		  ProService proservice = new ProServiceimp();
  			pro= proservice.getPro(proname);
			  bt[phonenumber*3-2] = (byte)all;
  		  bt[phonenumber*3-1] = (byte)location;
  		  bt[phonenumber*3] = (byte)speed;	
  		  System.out.println("写入新指令"+bt[3]);
  		  order1.setOrder(bt);
  		  try {
				sleep(10000);
			} catch (InterruptedException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
  		  System.out.println("新指令写入完成");
  		  System.out.println("指令为："+phonenumber+","+devicenumber+","+model+","+location+","+speed);
  		  System.out.println(bt[1]+","+bt[2]+","+bt[3]);
  			int e1 = pro.gete1();
  			int r1 = pro.getr1();
  			int e2 = pro.gete2();
  			int r2 = pro.getr2();
  			int e3 = pro.gete3();
  			int r3 = pro.getr3();
  			int e4 = pro.gete4();
  			int r4 = pro.getr4();
  			int e5 = pro.gete4();
  			int r5 = pro.getr4();
  			int e6 = pro.gete4();
  			int r6 = pro.getr4();
  			int e7 = pro.gete4();
  			int r7 = pro.getr4();
  			int e8 = pro.gete4();
  			int r8 = pro.getr4();
  			int e9 = pro.gete4();
  			int r9 = pro.getr4();
  			int e10 = pro.gete4();
  			int r10 = pro.getr4();
  			int e11 = pro.gete4();
  			int r11 = pro.getr4();
  			int e12 = pro.gete4();
  			int r12 = pro.getr4();
	    		  System.out.println("e1~e4:"+e1+","+e2+","+e3+","+e4+","+r1+","+r2+","+r3+","+r4);
  			if(!(e1==0)){
  				 System.out.println("开始执行e1");
  				bt[phonenumber*3] = (byte)e1;
  				try {
  					order1.setOrder(bt);
  					sleep(r1*1000);   					
  					System.out.println("e1执行完毕");
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
  			if(!(e2==0)){
  				System.out.println("开始执行e2");
  				bt[phonenumber*3] = (byte)e2;
  				try {
  					order1.setOrder(bt);
  					sleep(r2*1000);	    					
  					System.out.println("e2执行完毕");
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
  			if(!(e3==0)){
  				System.out.println("开始执行e3");
  				bt[phonenumber*3] = (byte)e3;
  				try {
  					order1.setOrder(bt);
  					sleep(r3*1000);
  					System.out.println("e3执行完毕");
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}
  			if(!(e4==0)){
  				bt[phonenumber*3] = (byte)e4;
  				try {
  					order1.setOrder(bt);
  					sleep(r4*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			}   	
  			if(!(e5==0)){
  				bt[phonenumber*3] = (byte)e5;
  				try {
  					order1.setOrder(bt);
  					sleep(r5*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			if(!(e6==0)){
  				bt[phonenumber*3] = (byte)e6;
  				try {
  					order1.setOrder(bt);
  					sleep(r6*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			if(!(e7==0)){
  				bt[phonenumber*3] = (byte)e7;
  				try {
  					order1.setOrder(bt);
  					sleep(r7*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			if(!(e8==0)){
  				bt[phonenumber*3] = (byte)e8;
  				try {
  					order1.setOrder(bt);
  					sleep(r8*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			if(!(e9==0)){
  				bt[phonenumber*3] = (byte)e9;
  				try {
  					order1.setOrder(bt);
  					sleep(r9*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			if(!(e10==0)){
  				bt[phonenumber*3] = (byte)e10;
  				try {
  					order1.setOrder(bt);
  					sleep(r10*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			if(!(e11==0)){
  				bt[phonenumber*3] = (byte)e11;
  				try {
  					order1.setOrder(bt);
  					sleep(r11*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			if(!(e12==0)){
  				bt[phonenumber*3] = (byte)e12;
  				try {
  					order1.setOrder(bt);
  					sleep(r12*1000);    					
  				} catch (InterruptedException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  			} 
  			turnOffFlag(phonenumber);
  	          }
  	     };
  	     
  	     thread2.start();
}
    private void startThread3(String order,int phonenumber){
		  thread3 = new Thread () {
	  @Override
	  public void run() {
		  int devicenumber = asciiToInt(order.charAt(3));
		  int all = phonenumber*16+devicenumber;
		  int model1 = asciiToInt(order.charAt(4));
		  int model2 = asciiToInt(order.charAt(5));
		  int model =model1*16+model2;
		  int location1 = asciiToInt(order.charAt(6));
		  int location2 = asciiToInt(order.charAt(7));
		  int location = location1*16+location2;
		  int speed1 = asciiToInt(order.charAt(8));
		  int speed2 = asciiToInt(order.charAt(9));
		  int speed = speed1*16+speed2;
		  ProjectIntToString its = new ProjectIntToString();
		  String proname = its.findPro(model);	
		  Project pro = new Project();
		  ProService proservice = new ProServiceimp();
			pro= proservice.getPro(proname);
			  bt[phonenumber*3-2] = (byte)all;
		  bt[phonenumber*3-1] = (byte)location;
		  bt[phonenumber*3] = (byte)speed;	
		  System.out.println("写入新指令"+bt[3]);
		  order1.setOrder(bt);
		  try {
				sleep(10000);
			} catch (InterruptedException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
		  System.out.println("新指令写入完成");
		  System.out.println("指令为："+phonenumber+","+devicenumber+","+model+","+location+","+speed);
		  System.out.println(bt[1]+","+bt[2]+","+bt[3]);
			int e1 = pro.gete1();
			int r1 = pro.getr1();
			int e2 = pro.gete2();
			int r2 = pro.getr2();
			int e3 = pro.gete3();
			int r3 = pro.getr3();
			int e4 = pro.gete4();
			int r4 = pro.getr4();
			int e5 = pro.gete4();
			int r5 = pro.getr4();
			int e6 = pro.gete4();
			int r6 = pro.getr4();
			int e7 = pro.gete4();
			int r7 = pro.getr4();
			int e8 = pro.gete4();
			int r8 = pro.getr4();
			int e9 = pro.gete4();
			int r9 = pro.getr4();
			int e10 = pro.gete4();
			int r10 = pro.getr4();
			int e11 = pro.gete4();
			int r11 = pro.getr4();
			int e12 = pro.gete4();
			int r12 = pro.getr4();
	    		  System.out.println("e1~e4:"+e1+","+e2+","+e3+","+e4+","+r1+","+r2+","+r3+","+r4);
			if(!(e1==0)){
				 System.out.println("开始执行e1");
				bt[phonenumber*3] = (byte)e1;
				try {
					order1.setOrder(bt);
					sleep(r1*1000);   					
					System.out.println("e1执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!(e2==0)){
				System.out.println("开始执行e2");
				bt[phonenumber*3] = (byte)e2;
				try {
					order1.setOrder(bt);
					sleep(r2*1000);	    					
					System.out.println("e2执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!(e3==0)){
				System.out.println("开始执行e3");
				bt[phonenumber*3] = (byte)e3;
				try {
					order1.setOrder(bt);
					sleep(r3*1000);
					System.out.println("e3执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!(e4==0)){
				bt[phonenumber*3] = (byte)e4;
				try {
					order1.setOrder(bt);
					sleep(r4*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}   	
			if(!(e5==0)){
				bt[phonenumber*3] = (byte)e5;
				try {
					order1.setOrder(bt);
					sleep(r5*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e6==0)){
				bt[phonenumber*3] = (byte)e6;
				try {
					order1.setOrder(bt);
					sleep(r6*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e7==0)){
				bt[phonenumber*3] = (byte)e7;
				try {
					order1.setOrder(bt);
					sleep(r7*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e8==0)){
				bt[phonenumber*3] = (byte)e8;
				try {
					order1.setOrder(bt);
					sleep(r8*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e9==0)){
				bt[phonenumber*3] = (byte)e9;
				try {
					order1.setOrder(bt);
					sleep(r9*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e10==0)){
				bt[phonenumber*3] = (byte)e10;
				try {
					order1.setOrder(bt);
					sleep(r10*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e11==0)){
				bt[phonenumber*3] = (byte)e11;
				try {
					order1.setOrder(bt);
					sleep(r11*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e12==0)){
				bt[phonenumber*3] = (byte)e12;
				try {
					order1.setOrder(bt);
					sleep(r12*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			turnOffFlag(phonenumber);
	          }
	     };
	     
	     thread3.start();
}
    private void startThread4(String order,int phonenumber){
		  thread4 = new Thread () {
	  @Override
	  public void run() {
		  int devicenumber = asciiToInt(order.charAt(3));
		  int all = phonenumber*16+devicenumber;
		  int model1 = asciiToInt(order.charAt(4));
		  int model2 = asciiToInt(order.charAt(5));
		  int model =model1*16+model2;
		  int location1 = asciiToInt(order.charAt(6));
		  int location2 = asciiToInt(order.charAt(7));
		  int location = location1*16+location2;
		  int speed1 = asciiToInt(order.charAt(8));
		  int speed2 = asciiToInt(order.charAt(9));
		  int speed = speed1*16+speed2;
		  ProjectIntToString its = new ProjectIntToString();
		  String proname = its.findPro(model);	
		  Project pro = new Project();
		  ProService proservice = new ProServiceimp();
			pro= proservice.getPro(proname);
			  bt[phonenumber*3-2] = (byte)all;
		  bt[phonenumber*3-1] = (byte)location;
		  bt[phonenumber*3] = (byte)speed;	
		  System.out.println("写入新指令"+bt[3]);
		  order1.setOrder(bt);
		  try {
				sleep(10000);
			} catch (InterruptedException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
		  System.out.println("新指令写入完成");
		  System.out.println("指令为："+phonenumber+","+devicenumber+","+model+","+location+","+speed);
		  System.out.println(bt[1]+","+bt[2]+","+bt[3]);
			int e1 = pro.gete1();
			int r1 = pro.getr1();
			int e2 = pro.gete2();
			int r2 = pro.getr2();
			int e3 = pro.gete3();
			int r3 = pro.getr3();
			int e4 = pro.gete4();
			int r4 = pro.getr4();
			int e5 = pro.gete4();
			int r5 = pro.getr4();
			int e6 = pro.gete4();
			int r6 = pro.getr4();
			int e7 = pro.gete4();
			int r7 = pro.getr4();
			int e8 = pro.gete4();
			int r8 = pro.getr4();
			int e9 = pro.gete4();
			int r9 = pro.getr4();
			int e10 = pro.gete4();
			int r10 = pro.getr4();
			int e11 = pro.gete4();
			int r11 = pro.getr4();
			int e12 = pro.gete4();
			int r12 = pro.getr4();
	    		  System.out.println("e1~e4:"+e1+","+e2+","+e3+","+e4+","+r1+","+r2+","+r3+","+r4);
			if(!(e1==0)){
				 System.out.println("开始执行e1");
				bt[phonenumber*3] = (byte)e1;
				try {
					order1.setOrder(bt);
					sleep(r1*1000);   					
					System.out.println("e1执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!(e2==0)){
				System.out.println("开始执行e2");
				bt[phonenumber*3] = (byte)e2;
				try {
					order1.setOrder(bt);
					sleep(r2*1000);	    					
					System.out.println("e2执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!(e3==0)){
				System.out.println("开始执行e3");
				bt[phonenumber*3] = (byte)e3;
				try {
					order1.setOrder(bt);
					sleep(r3*1000);
					System.out.println("e3执行完毕");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!(e4==0)){
				bt[phonenumber*3] = (byte)e4;
				try {
					order1.setOrder(bt);
					sleep(r4*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}   	
			if(!(e5==0)){
				bt[phonenumber*3] = (byte)e5;
				try {
					order1.setOrder(bt);
					sleep(r5*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e6==0)){
				bt[phonenumber*3] = (byte)e6;
				try {
					order1.setOrder(bt);
					sleep(r6*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e7==0)){
				bt[phonenumber*3] = (byte)e7;
				try {
					order1.setOrder(bt);
					sleep(r7*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e8==0)){
				bt[phonenumber*3] = (byte)e8;
				try {
					order1.setOrder(bt);
					sleep(r8*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e9==0)){
				bt[phonenumber*3] = (byte)e9;
				try {
					order1.setOrder(bt);
					sleep(r9*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e10==0)){
				bt[phonenumber*3] = (byte)e10;
				try {
					order1.setOrder(bt);
					sleep(r10*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e11==0)){
				bt[phonenumber*3] = (byte)e11;
				try {
					order1.setOrder(bt);
					sleep(r11*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(!(e12==0)){
				bt[phonenumber*3] = (byte)e12;
				try {
					order1.setOrder(bt);
					sleep(r12*1000);    					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			turnOffFlag(phonenumber);
	          }
	     };
	     
	     thread4.start();
}
    public int asciiToInt(int i){
    	if(i<58){
    		i=i-48;
    		return i;
    	}
    	else{
    		i=i-55;
    		return i;
    	}
    }
    public boolean phonenumberToFlag(int i){
    	boolean flag = false; 
    	switch(i){
    	case 1: flag = flagphone1;
    	break;
    	};
    	switch(i){
    	case 2: flag = flagphone2;
    	break;
    	};
    	switch(i){
    	case 3: flag = flagphone3;
    	break;
    	};
    	switch(i){
    	case 4: flag = flagphone4;
    	break;
    	};
    	return flag;
    }
    public Thread phonenumberToThread(int i){
    	Thread thread= null; 
    	switch(i){
    	case 1: thread = thread1;
    	return thread1;
    	};
    	switch(i){
    	case 2: thread = thread2;
    	return thread2;
    	};
    	switch(i){
    	case 3: thread = thread3;
    	return thread3;
    	};
    	switch(i){
    	case 4: thread = thread4;
    	return thread4;
    	};
    	return thread;
    }
    public void turnOnFlag(int i){
    	switch(i){
    	case 1: flagphone1 = true;
    	break;
    	};
    	switch(i){
    	case 2: flagphone2 = true;
    	break;
    	};
    	switch(i){
    	case 3: flagphone3 = true;
    	break;
    	};
    	switch(i){
    	case 4: flagphone4 = true;
    	break;
    	};
    }
    public void turnOffFlag(int i){
    	switch(i){
    	case 1: flagphone1 = false;
    	break;
    	};
    	switch(i){
    	case 2: flagphone2 = false;
    	break;
    	};
    	switch(i){
    	case 3: flagphone3 = false;
    	break;
    	};
    	switch(i){
    	case 4: flagphone4 = false;
    	break;
    	};
    }
}
