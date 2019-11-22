package cn.service;

import java.util.List;

import cn.entity.Student;
import cn.entity.User;

public interface UserService {
	public boolean findUser(User user);
	public int registerUser(User user);
	public List<User> getAllUser();
}
