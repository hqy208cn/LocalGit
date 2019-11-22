package cn.service.impl;

import java.sql.ResultSet;

import cn.dao.DealDao;
import cn.dao.GradeDao;
import cn.dao.impl.DealDaoimp;
import cn.dao.impl.GradeDaoimp;
import cn.service.DealService;

public class DealServiceimp implements DealService{
	private DealDao dealDao = null;
	public 	DealServiceimp(){
		dealDao = new DealDaoimp();
	}
	@Override
	public ResultSet selectDeal(String username) {
		// TODO Auto-generated method stub
		return dealDao.selectDeal(username);
	}
	@Override
	public ResultSet selectTop10() {
		// TODO Auto-generated method stub
		return dealDao.selectTop10();
	}
	@Override
	public ResultSet getAveHeart(String username) {
		// TODO Auto-generated method stub
		return dealDao.getAveHeart(username);
	}
	@Override
	public ResultSet selectAllChufang() {
		// TODO Auto-generated method stub
		return dealDao.selectAllChufang();
	}
	@Override
	public void doUpLoad(String name, String project, String heart, String maxheart, String limheart, String aveheart,
			String deal, String cal) {
		// TODO Auto-generated method stub
		dealDao.doUpLoad(name,project,heart,maxheart,limheart,aveheart,deal,cal);
	}

}
