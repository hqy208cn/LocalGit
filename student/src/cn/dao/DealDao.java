package cn.dao;

import java.sql.ResultSet;

public interface DealDao {
public ResultSet selectDeal(String username);
public ResultSet selectTop10();
public ResultSet getAveHeart(String username);
public ResultSet selectAllChufang();
public void doUpLoad(String name, String project, String heart, String maxheart, String limheart, String aveheart,
		String deal, String cal);
}
