package cn.service.impl;

import java.util.List;

import cn.dao.UserDao;
import cn.dao.impl.UserDaoimp;
import cn.entity.Student;
import cn.entity.User;
import cn.service.UserService;

public class UserServiceimp implements UserService{
	
	UserDao userdao = null;
	
	public UserServiceimp(){
		userdao = new UserDaoimp();
	}

	@Override
	public boolean findUser(User user) {
		return userdao.findUser(user);
	}
	

	@Override
	public List<User> getAllUser() {
		return userdao.getAllUser();
	}

	@Override
public int registerUser(User user){
	return userdao.registerUser(user);
}
}
