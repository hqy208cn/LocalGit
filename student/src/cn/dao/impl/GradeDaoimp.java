package cn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.dao.BaseDao;
import cn.dao.GradeDao;
import cn.entity.Grade;

public class GradeDaoimp extends BaseDao implements GradeDao{

	@Override
	public Grade getGrade(Grade grade) {
		String sql = "select gradeName from tb_grade where gradeId=?";
		Object[] params = {grade.getGradeId()};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try{
			while(rs.next()){
				grade.setGradeName(rs.getString("gradeName"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return grade;
	}

	@Override
	public Grade getGrade(String gname){
		Grade grade = new Grade();
		String sql = "select gradeId from tb_grade where gradeName=?";
		Object[] params = {gname};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while(rs.next()){
				grade.setGradeName(gname);
				grade.setGradeId(rs.getInt("gradeId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grade;
		
	}

	
}
