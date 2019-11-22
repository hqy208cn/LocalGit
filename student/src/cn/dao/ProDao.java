package cn.dao;

import java.sql.ResultSet;
import java.util.List;


import cn.entity.Project;
import cn.entity.User;

public interface ProDao {
	public Project getPro(String proname);
	public ResultSet selectAllPro();
}
