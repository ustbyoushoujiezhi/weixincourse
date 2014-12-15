package org.xiaowu.course.message.studentmessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import org.xiaowu.course.jdbcmysql.JDBC_Connection;
//import org.xiaowu.course.message.studentmessage.dumpenergy;
public class Studentmessage {
	
	private String stuname;
	private String studentnum;
	private String IDcode6;
	private String openid;
	private String telenum;
	
	public void settelenum(String telenum1)
	{
		telenum = telenum1;
	}
	public String gettelenum()
	{
		return telenum;
	}
	public void setopenid(String user)
	{
		openid = user;
	}
	public String getopenid()
	{
		return openid;
	}
	public void setstuname(String name)
	{
		stuname = name;
	}
	public String getstuname()
	{
		return stuname;
	}
	public void setstudentnum(String num)
	{
		studentnum = num;	
	}
	public String getstudentnum()
	{
		return studentnum;
	}
	public void setIDcode6(String idcode)
	{
		IDcode6 = idcode;
	}
	public String getIDcode6()
	{
		return IDcode6;
	}
	public void deletestu(String Openid)
	{
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try{
			conn = JDBC_Connection.getConnection();
			pstm = conn.prepareStatement("DELETE  FROM studentmessage WHERE openid = ?");
			pstm.setString(1, Openid);
			pstm.executeUpdate();
		}catch(Exception e)
		{
			;
		}finally{
			JDBC_Connection.free(rs, conn, pstm);
		}
		
	}
	public void addstu(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Connection.getConnection();
			String sql = "INSERT INTO studentmessage(stuname,studentnum,id6,telenum,openid) VALUES (?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, getstuname());
			pstm.setString(2,getstudentnum());
			pstm.setString(3, getIDcode6());
			pstm.setString(4, gettelenum());
			pstm.setString(5, getopenid());
			
			pstm.executeUpdate();
		}catch (Exception e)
		{
			;
		}finally{
			JDBC_Connection.free(rs,conn,pstm);
		}
		
	}
	 
	public Studentmessage verifstu(Studentmessage stu) 
	{
		/** 
	     * 验证学生
	     *  
	     * @param request 
	     * @return Boolean
	     */  
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
	    int i=0;
		try {
			conn = JDBC_Connection.getConnection();
			String sql = "SELECT * FROM dumpenergy WHERE stunum = ? AND id6 = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, stu.getstudentnum());
			pstm.setString(2, stu.getIDcode6());
			rs = pstm.executeQuery();
			
			while(rs.next()&&(i<=1))
			{   
				i++;
				stu.setstuname(rs.getString("stuname"));
			}
		}catch (Exception e)
		{
			;
		}finally{
			JDBC_Connection.free(rs,conn,pstm);
		}
		if(i==0)
		{
			return null;
		}
		else
		{
			return stu;
		}
	}
	
}
