package cn.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.dao.BaseDao;
import cn.dao.DealDao;

public class DealDaoimp implements DealDao {

	@Override
	public ResultSet selectDeal(String username) {
		String sql = "select* from tb_deal where name ="+"'"+username+"'";;//根据指定姓名选择运动处方
		Object[] params = {};
		BaseDao basedao = new BaseDao();
		ResultSet rs = basedao.executeQuerySQL(sql, params);
		return rs;
	}

	@Override
	public ResultSet selectTop10() {
		// TODO Auto-generated method stub
		String sql = "select name,sum(cal) from tb_deal group by name order by sum(cal) desc limit 0,10";;
		Object[] params = {};
		BaseDao basedao = new BaseDao();
		ResultSet rs = basedao.executeQuerySQL(sql, params);
		return rs;
	}

	@Override
	public ResultSet getAveHeart(String username) {
		// TODO Auto-generated method stub
		String sql = "select aveheart from tb_deal where name ="+"'"+username+"'";;
		Object[] params = {};
		BaseDao basedao = new BaseDao();
		ResultSet rs = basedao.executeQuerySQL(sql, params);
		return rs;
	}

	@Override
	public ResultSet selectAllChufang() {
		// TODO Auto-generated method stub
		String sql = "select* from tb_chufang ";
		Object[] params = {};
		BaseDao basedao = new BaseDao();
		ResultSet rs = basedao.executeQuerySQL(sql, params);
		return rs;
	}

	@Override
	public void doUpLoad(String name, String project, String heart, String maxheart, String limheart, String aveheart,
			String deal, String cal) {
		// TODO Auto-generated method stub
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
		String sql = "insert into tb_deal(`name`,`project`,`heart`,`maxheart`,`limheart`,`aveheart`,`deal`,`cal`,`time`) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {name,project,heart,maxheart,limheart,aveheart,deal,cal,createdate};
		BaseDao basedao = new BaseDao();
		basedao.executeUpdateSQL(sql, params);
	}

}
