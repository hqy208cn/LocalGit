package cn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.ProDao;
import cn.dao.UserDao;
import cn.entity.Project;
import cn.entity.User;
import cn.util.JsonUtil;

public class ProDaoimp extends BaseDao implements ProDao{
	@Override
	//获取所有学生信息
	public Project getPro(String proname) {
		Project pro = null;
		List<Project> list = new ArrayList<Project>();
		String sql = "select `pro`,`e1`,`e2`,`e3`,`e4`,`e5`,`e6`,`e7`,`e8`,`e9`,`e10`,`e11`,`e12`,`r1`,`r2`,`r3`,`r4`,`r5`,`r6`,`r7`,`r8`,`r9`,`r10`,`r11`,`r12` from tb_pro where pro="+"'"+proname+"'";
		Object[] params = {};
		ResultSet rs = this.executeQuerySQL(sql, params);
		try {
			while (rs.next()) {
				pro = new Project();
				pro.setproname(rs.getString("pro"));
				pro.sete1(rs.getInt("e1"));
				pro.sete2(rs.getInt("e2"));
				pro.sete3(rs.getInt("e3"));
				pro.sete4(rs.getInt("e4"));
				pro.sete5(rs.getInt("e5"));
				pro.sete6(rs.getInt("e6"));
				pro.sete7(rs.getInt("e7"));
				pro.sete8(rs.getInt("e8"));
				pro.sete9(rs.getInt("e9"));
				pro.sete10(rs.getInt("e10"));
				pro.sete11(rs.getInt("e11"));
				pro.sete12(rs.getInt("e12"));
				pro.setr1(rs.getInt("r1"));
				pro.setr2(rs.getInt("r2"));
				pro.setr3(rs.getInt("r3"));
				pro.setr4(rs.getInt("r4"));
				pro.setr5(rs.getInt("r5"));
				pro.setr6(rs.getInt("r6"));
				pro.setr7(rs.getInt("r7"));
				pro.setr8(rs.getInt("r8"));
				pro.setr9(rs.getInt("r9"));
				pro.setr10(rs.getInt("r10"));
				pro.setr11(rs.getInt("r11"));
				pro.setr12(rs.getInt("r12"));
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}

	@Override
	public ResultSet selectAllPro() {
		// TODO Auto-generated method stub
		String sql = "select* from tb_pro ";
		Object[] params = {};
		BaseDao basedao = new BaseDao();
		ResultSet rs = basedao.executeQuerySQL(sql, params);
		return rs;
	}
	
}
