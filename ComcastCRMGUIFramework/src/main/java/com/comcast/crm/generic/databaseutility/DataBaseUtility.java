package com.comcast.crm.generic.databaseutility;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtility {
	Connection con;
	public void getDBconnection() throws SQLException {
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		}catch (Exception e) {
			
		}
	}
	public void getDBconnection(String url,String username,String password) throws SQLException {
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url,username,password);
		}catch (Exception e) {
		}
	}
		public void closeDBconnection()throws SQLException{
			try {
			con.close();
			}catch (Exception e) {
			}
		}
		public ResultSet executeSelectQuery(String query) throws SQLException {
			ResultSet result=null;
			try {
			Statement stat = con.createStatement();
			 result= stat.executeQuery(query);
			}catch (Exception e) {
			}
			return result;
		}
		public int executeNonSelectQuery(String query) {
			int result=0;
			try {
				Statement stat=con.createStatement();
				stat.executeUpdate(query);
			}catch (Exception e) {
			}
				return result;
			}
		
		}
	

	

