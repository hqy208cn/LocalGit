package cn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import cn.util.ConfigManager;

public class BaseDao {
	Connection connection = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	public boolean getConnection(){
		try{
			Class.forName(ConfigManager.getInstance().getString("driver"));
			String url = ConfigManager.getInstance().getString("url");
			String username = ConfigManager.getInstance().getString("username");
			String password = ConfigManager.getInstance().getString("password");
			
			connection = DriverManager.getConnection(url, username, password);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int executeUpdateSQL(String sql,Object[] params){
		int row = 0;
		if(getConnection()){
			try{
				pstm = connection.prepareStatement(sql);
				for(int i = 0; i < params.length; i++){
					pstm.setObject(i+1, params[i]);								
				}
				row = pstm.executeUpdate();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return row;
		
	}
	
	public ResultSet executeQuerySQL(String sql,Object[] params){
		if(getConnection()){
			try{
				pstm = connection.prepareStatement(sql);
				for(int i = 0; i < params.length; i++){
					pstm.setObject(i+1, params[i]);					
				}
				rs = pstm.executeQuery();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return rs;
		
	}
	
	public void closeAllResourse(){
		if(connection != null){
			try{
				connection.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		if(pstm != null){
			try{
				pstm.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		if(rs != null){
			try{
				rs.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
