package cn.dao;

import java.util.List;

import cn.entity.Student;
import cn.entity.User;

public interface UserDao {
	public boolean findUser(User user);
	public int registerUser(User user);
	public List<User> getAllUser();
}
