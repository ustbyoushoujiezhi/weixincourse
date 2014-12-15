package org.xiaowu.course.dataport;


import java.io.IOException;    
//import java.util.ArrayList;
//import java.util.List;

import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class dataport {
	boolean getstudent(String stunum,String id6) throws IOException 
	{
		StringBuffer url=new StringBuffer("http://114.242.175.77:7070/ams/src/infointerface/CheckPersonByIDandIdentity.action?pNumandID=");
		url=url.append(stunum).append(id6);
		String geturl=url.toString();
		 CloseableHttpClient httpclient = HttpClients.createDefault();
	        HttpGet httpget = new HttpGet(geturl);  
	       // System.out.println("executing request " + httpget.getURI());  
	        // 执行get请求.    
	        CloseableHttpResponse response = httpclient.execute(httpget);  
	        try {  
	            // 获取响应实体    
	            HttpEntity entity = response.getEntity();  
	            //System.out.println("--------------------------------------");  
	            // 打印响应状态    
	           // System.out.println(response.getStatusLine());  
	            if (entity != null) {  
	                // 打印响应内容长度    
	               // System.out.println("Response content length: " + entity.getContentLength());  
	                // 打印响应内容    
	                System.out.println("Response content: " + EntityUtils.toString(entity)); 
	            	
	            	return true;
	            }  
	            else return false;
	            //System.out.println("------------------------------------");  
	        } finally {  
	            response.close();  
	        }  
	}

}
