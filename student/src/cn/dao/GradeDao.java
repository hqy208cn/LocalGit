package cn.dao;

import cn.entity.Grade;

public interface GradeDao {
	public Grade getGrade(Grade grade);
	public Grade getGrade(String gname);

}
