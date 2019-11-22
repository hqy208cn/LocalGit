package cn.service.impl;

import java.sql.ResultSet;
import java.util.List;


import cn.dao.GradeDao;
import cn.dao.ProDao;
import cn.dao.impl.GradeDaoimp;
import cn.dao.impl.ProDaoimp;
import cn.entity.Project;
import cn.entity.User;
import cn.service.ProService;
import cn.service.UserService;

public class ProServiceimp implements ProService{
	private ProDao ProDao = null;
	public 	ProServiceimp(){
		ProDao = new ProDaoimp();
	}
	@Override
	public Project getPro(String proname) {
		return ProDao.getPro(proname);
	}
	@Override
	public ResultSet selectAllPro() {
		// TODO Auto-generated method stub
		return ProDao.selectAllPro();
	}
	
}
