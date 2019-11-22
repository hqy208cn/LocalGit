package cn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.StudentDao;
import cn.entity.Student;
import cn.entity.deal;

/*
 * @ Copyright (c) Create by JASON  Date:2018-02-10  All rights reserved.
 *
 * @ class description：对学生表的操作类
 *
 */
public class StudentDaoimp extends BaseDao implements StudentDao {

	@Override
	//获取所有学生信息
	public List<Student> getAllStudent() {
		List<Student> list = new ArrayList<Student>();
		String sql = "select `id`,`name`,`gradeId`,`age`,`sex`,`profile` from tb_student ";
		Object[] params = {};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setAge(rs.getInt("age"));
				stu.setGradeId(rs.getInt("gradeId"));
				stu.setSex(rs.getString("sex"));
				stu.setName(rs.getString("name"));
				stu.setProfile(rs.getString("profile"));
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list.toString());
		return list;
	}
	@Override
	//获取所有学生信息
	public List<deal> getStudent(String name) {
		String name1 = name;
		List<deal> list = new ArrayList<deal>();
		String sql = "select `name`,`project`,`heart`,`maxheart`,`limheart`,`aveheart`,`deal`,`id`,`time`,`cal` from tb_deal where name="+"'"+name1+"'";
		Object[] params = {};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while (rs.next()) {	
				deal de = new deal();
				de.setname(rs.getString("name"));
				de.setproject(rs.getString("project"));
				de.setheart(rs.getString("heart"));
				de.setmaxheart(rs.getString("maxheart"));
				de.setlimheart(rs.getString("limheart"));
				de.setaveheart(rs.getString("aveheart"));
				de.setdeal(rs.getString("deal"));
				de.settime(rs.getString("time"));
				de.setId(rs.getInt("id"));
				de.setcal(rs.getString("cal"));
				list.add(de);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list.toString());
		return list;
	}
	@Override
	//获取所有学生信息
	public List<deal> getPaihang() {
		List<deal> list = new ArrayList<deal>();
		String sql ="select name,sum(cal) from tb_deal group by name order by sum(cal) desc limit 0,10";
		Object[] params = {};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while (rs.next()) {	
				deal de = new deal();
				de.setname(rs.getString("name"));
				de.setcal(rs.getString("sum(cal)"));
				list.add(de);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list.toString());
		return list;
	}
	@Override
	//获取指定学生信息
	public deal getDealMoreInfo(deal deal) {
		deal de = deal;
		String sql = "select `name`,`project`,`heart`,`maxheart`,`limheart`,`aveheart`,`deal`,`cal`,`time` from tb_deal where `id`=?";
		Object[] params = {deal.getId()};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while (rs.next()) {
				de.setname(rs.getString("name"));
				de.setproject(rs.getString("project"));
				de.setheart(rs.getString("heart"));
				de.setmaxheart(rs.getString("maxheart"));
				de.setlimheart(rs.getString("limheart"));
				de.setaveheart(rs.getString("aveheart"));
				de.setdeal(rs.getString("deal"));
				de.setcal(rs.getString("cal"));
				de.settime(rs.getString("time"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return de;
		
	}

	@Override
	//添加学生
	public int addStudent(Student student) {
		int row = 0;
		String sql = "insert into tb_student(`id`,`name`,`age`,`sex`,`gradeId`,`profile`) values(?,?,?,?,?,?)";
		Object[] params = {student.getId(),student.getName(),student.getAge(),student.getSex(),student.getGradeId(),student.getProfile()};
		row = this.executeUpdateSQL(sql, params);
		if(row>0){
			System.out.println("增加学生成功");
		}else{
			System.out.println("增加学生失败");
		}
		return row;
	}

	@Override
	//删除学生
	public int delStudent(deal deal) {
		int row = 0;
		String sql = "delete from tb_deal where `id`=?";
		Object[] params = {deal.getId()};
		row = this.executeUpdateSQL(sql, params);
		if(row>0){
			System.out.println("删除记录成功");
		}else{
			System.out.println("删除记录失败");
		}
		return row;
	}

	@Override
	//修改学生信息
	public int modifyStudent(Student student) {
		int row = 0;
		String sql = "update tb_student set `name`=?,`sex`=?,`gradeId`=?,`age`=?,`profile`=?  where `id`=?";
		System.out.println(student.getId());
		Object[] params = {student.getName(),student.getSex(),student.getGradeId(),student.getAge(),student.getProfile(),student.getId()};
		row = this.executeUpdateSQL(sql, params);
		if(row>0){
			System.out.println("修改学生成功");
		}else{
			System.out.println("修改学生失败");
		}
		
		return row;
	}
	
	
	//查找指定的学生存在不存在
	public boolean findStudent(Student student){
		boolean flag = true;
		int row = 0;
		String sql = "select count(1) from tb_student where  name=? and age=? and sex=? and gradeId=? and profile=?";
		Object[] params = {student.getName(),student.getAge(),student.getSex(),student.getGradeId(),student.getProfile()};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while(rs.next()){
				row = rs.getInt(1);//你可以通过索引或者列名来获得查询结果集中的某一列的值。 等价于rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(row>0){
			System.out.println("此学生已经存在");
			flag = true;
		}else{
			System.out.println(row);
			System.out.println("没有这个学生");
			flag = false;
		}
		
		return flag;
		
	}
}