package com.xiaopi3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost/db_book?useSSL=false";
	private String userName="root";
	private String password="123456";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	/**
	 * 连接数据库
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(jdbcName);
		Connection conn=DriverManager.getConnection(dbUrl, userName, password);
		return conn;
	}
	
	public void close(Connection conn) throws SQLException {
		if(conn!=null)
			conn.close();
	}
	
	public static void main(String[] args) {
		DbUtil db=new DbUtil();
		Connection conn=null;
		try {
			conn=db.getConn();
			System.out.println("连接成功！");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("连接失败！");
			e.printStackTrace();
		}finally {
			try {
				db.close(conn);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
