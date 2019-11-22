package cn.entity;

import cn.service.SocketService;

public class Order {
	public static byte[] bt1 = {90,00,00,00,00,00,00,00,00,00,00,00,00,91};;
	
	public void setOrder(byte[] bt){
		this.bt1 = bt;
		System.out.println("实体类改变为"+bt1[3]);
		SocketService.flag=true;
	}
	public byte[] getOrder(){
		return bt1;
	}
	

}
