package com.expenses.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionManager {
	
	private Connection dbConnection;
	
	private static DBConnectionManager dbConnectionHandle;
	
	private DBConnectionManager(){
		
	}
	
	public static DBConnectionManager getInstance(){
		if(dbConnectionHandle==null){
			dbConnectionHandle = new DBConnectionManager();
		}
		return dbConnectionHandle;
		
	}
	
	public Connection getDBConnection() {
		
		if(dbConnection == null){
			String url = "jdbc:mysql://localhost/household";
			try{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				dbConnection = DriverManager.getConnection(url, "root", "root123");
				
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return dbConnection;
		
	}

}
