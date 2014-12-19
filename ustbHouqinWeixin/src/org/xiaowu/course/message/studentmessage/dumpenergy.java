package org.xiaowu.course.message.studentmessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import org.xiaowu.course.jdbcmysql.JDBC_Connection;
public class dumpenergy {
	private String stuname = null;
	private String stunum = null;
	private String dormityno = null;
	private String dormitybuildno = null;
	private String dumpenergy = null;
	
	public void setstuname(String stuname1)
	{
		stuname = stuname1;
	}
	public String getstuname()
	{
		return stuname;
	}
	public void setstunum(String stunum1)
	{
		stunum = stunum1;
	}
	public String getstunum()
	{
		return stunum;
	}
	public void setdormityno(String dornum)
	{
		dormityno = dornum;
	}
	public String getdormityno()
	{
		return dormityno;
	}
	public void setdormitybuildno(String dornum)
	{
		dormitybuildno = dornum;
	}
	public String getdormitybuildno()
	{
		return dormitybuildno;
	}
	public void setdumpenergy(String dornum)
	{
		dumpenergy = dornum;
	}
	public String getdumpenergy()
	{
		return dumpenergy;
	}
	public String searchenergy(String stunum)
	{
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String str = new String();
		int i=0;
		try {
			conn = JDBC_Connection.getConnection();
			String sql = "SELECT * FROM dumpenergy WHERE stunum = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, stunum);
			rs = pstm.executeQuery();
			
			while(rs.next()&&(i<=1))
			{   
				i++;
				str = rs.getString("dumpenergy");
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
			return str;
		}
	}
	
}
