package cn.service.impl;

import cn.dao.GradeDao;
import cn.dao.impl.GradeDaoimp;
import cn.entity.Grade;
import cn.service.GradeService;

public class GradeServiceimp implements GradeService{
	
	private GradeDao gradeDao = null;
	public 	GradeServiceimp(){
		gradeDao = new GradeDaoimp();
	}
	@Override
	public Grade getGrade(Grade grade) {
		return gradeDao.getGrade(grade);
	}

	@Override
	public Grade getGrade(String gname) {
		// TODO Auto-generated method stub
		return gradeDao.getGrade(gname);
	}
	
}
