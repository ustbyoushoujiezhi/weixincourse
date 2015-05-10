package org.xiaowu.course.service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.xiaowu.course.jdbcmysql.JDBC_Connection;
import org.xiaowu.course.message.studentmessage.dumpenergy;
import org.xiaowu.course.message.studentmessage.Studentmessage;

import org.xiaowu.course.httpclient.checkinfojason;
import org.xiaowu.course.httpclient.checkinfo;

public class textService {
private String fromusername;
private String textmessage;
textService(String usrname,String message)
{
	fromusername = usrname;
	textmessage = message;
}
public String service() throws Exception
{
	String str = "请输入“注册+学号+身份证号后6位”以完成注册\n";
	Studentmessage student = searchusr(fromusername); 
	
	if(student!=null)
	{
		if(textmessage.compareTo("?")==0||textmessage.compareTo("？")==0)
		{
			StringBuffer menu = new StringBuffer();
			menu.append("您好，请回复数字选择服务\n");
			menu.append("1  查询宿舍卫生成绩\n");
			menu.append("2 查询饭卡费用\n");
			menu.append("回复  ? 显示主菜单");
			//String menustr = menu.toString();
			return menu.toString();
		}else if(textmessage.compareTo("1")==0)
				{
					 checkinfo dorscore = new checkinfo(student.getstudentnum());
					return dorscore.getDorscore();
				}
		else {
				return textmessage;
		     }
	}
	else //如果该用户没有注册
	{
		if(textmessage.startsWith("注册"))
		{
			
			String strs[] = textmessage.split("\\+");
			if(strs.length==3)
			{
				Studentmessage stu = new Studentmessage();
				stu.setopenid(fromusername);
				stu.setstudentnum(strs[1]);
				stu.setIDcode6(strs[2]);
				//验证学生信息
				checkinfo check = new checkinfo(strs[1],strs[2]);
				checkinfojason chjsondata = new checkinfojason();
				
				chjsondata = check.checkprocess();
				//Studentmessage stuget = stu.verifstu(stu);
				
				
				if(chjsondata.getflag() == 0)
				{
					//Studentmessage stuget = new Studentmessage();
					stu.setstuname(chjsondata.getname());
					
					StringBuffer strbuffer = new StringBuffer();
					//验证好学生信息后将学生的信息添加到表里
					stu.addstu();
					strbuffer.append(stu.getstuname()).append("你已注册成功\n");
					strbuffer.append("回复 ？ 显示主菜单");
					return strbuffer.toString();
				}
				else
				{
					if(chjsondata.getflag()==4)
						{
							return "学号和身份证信息不匹配，请输入正确的信息";
							}
					else{
						return "没有查到你的信息";
					}
				}
			}
			else
			{
				return "请输入正确的信息格式以完成注册";
			}	
		}
		else 
		{
			return str;
		}
	}
}
 private Studentmessage searchusr(String usrname)
{
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	int i = 0;
	Studentmessage stu = new Studentmessage();
	try {
		conn = JDBC_Connection.getConnection();
		String sql = "SELECT * FROM studentmessage WHERE openid = ?";
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, fromusername);
		rs = pstm.executeQuery();
		while(rs.next()&&(i<=1))
		{
			i++;
			stu.setopenid(fromusername);
			stu.setstuname(rs.getString("stuname"));
			stu.setstudentnum(rs.getString("studentnum"));
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

