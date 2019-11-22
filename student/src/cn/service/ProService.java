package cn.service;

import java.sql.ResultSet;
import java.util.List;


import cn.entity.Project;
import cn.entity.User;

public interface ProService {
	public Project getPro(String proname);
	public ResultSet selectAllPro();
}
