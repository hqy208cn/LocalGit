package cn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.UserDao;
import cn.entity.Student;
import cn.entity.User;

public class UserDaoimp extends BaseDao implements UserDao{
	@Override
	public boolean findUser(User user){
		boolean flag = true;
		String sql = "select count(1) from tb_user where `username`=? and `password`=?";
		Object[] params = {user.getUsername(),user.getPassword()};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try{
			while(rs.next()){		//.next()�����ж�rs��������Ƿ������
				int num = rs.getInt(1);
				if(num>0){
					System.out.println("存在这个用户");
					flag = true;
				}
				else{
					System.out.println("不存在这个用户");
					flag = false;
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();			
		}
		return flag;
	}
	public int registerUser(User user){
		int row = 0;
		String sql = "insert into tb_user(`username`,`password`) values(?,?)";
		Object[] params = {user.getUsername(),user.getPassword()};
		row = this.executeUpdateSQL(sql, params);
		if(row>0){
			System.out.println("增加用户成功");
		}else{
			System.out.println("增加用户失败");
		}
		return row;
	}
	@Override
	//获取所有学生信息
	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		String sql = "select `username`,`password` from tb_user ";
		Object[] params = {};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
