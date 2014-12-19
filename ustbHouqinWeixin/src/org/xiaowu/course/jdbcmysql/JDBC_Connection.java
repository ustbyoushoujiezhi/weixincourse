package org.xiaowu.course.jdbcmysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.PreparedStatement;



public class JDBC_Connection {
	static String drivername = "com.mysql.jdbc.Driver";
	static String URL = "jdbc:mysql://localhost:3306/weixin";//后边加数据库名
	static String username = "root";
	static String password = "udbvj";
	
	
	 public static Connection getConnection(){
		 Connection conn = null;
		 try{
				Class.forName(drivername);
			}catch (ClassNotFoundException e){
				 ;
			}
		 try{
			 conn = (Connection) DriverManager.getConnection(URL, username, password); 
		 }catch (SQLException e){
			 e.printStackTrace();
		 }
		 return conn;
	 }
	 
	public static void free(ResultSet rs,Connection conn,Statement stmt)
	{
		try {
			if(rs != null)
			{rs.close();}
		}catch (SQLException e){
			;
		}finally {
			try {
				if(conn!=null){
					conn.close();
				}
			}catch (SQLException e){
				;
			}finally {
				try {
					if(stmt != null)
						stmt.close();
				}catch (SQLException e){
					;
				}
			}
		}
	}
	
	
	
}
