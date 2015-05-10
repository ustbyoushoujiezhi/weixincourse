package org.xiaowu.course.service;

//import java.sql.PreparedStatement;
/**********************************************
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
************************************************/
import java.util.Date;  
import java.util.Map;  
import javax.servlet.http.HttpServletRequest;  
import org.xiaowu.course.message.resp.TextMessage;  
import org.xiaowu.course.until.MessageUtil;  
import org.xiaowu.course.message.studentmessage.Studentmessage;
import org.xiaowu.course.message.studentmessage.dumpenergy;
//import org.xiaowu.course.jdbcmysql.JDBC_Connection;

public class CoreService {
	 /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
	static char zhuce_flag =0;
	private static Studentmessage studentmessage = new Studentmessage();
	
	 /**********************************
     * 处理微信发来的请求 
     *  
     * @param  
     * @return menustring
     */  
	private static String getmainmenu()//
	{
		StringBuffer menu = new StringBuffer();
		menu.append("您好，请回复数字选择服务\n");
		menu.append("1  查询宿舍电费余量\n");
		menu.append("2 查询饭卡费用");
		menu.append("回复？  显示主菜单");
		
		return menu.toString();
	}
	
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息 内容  
            StringBuffer respContent = new StringBuffer();
           // respContent.append("请求处理异常，请稍候尝试！"); 
            
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");            
            
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	
            	//respContent.append("您发送的是文本消息！").append("\n");  
            	//respContent.append(requestMap.get("Content"));
            	String textmessage = requestMap.get("Content");
            	textService textservice = new textService(fromUserName,textmessage);
            	respContent.append(textservice.service());
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
            	respContent.append( "您发送的是图片消息！");  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
            	respContent.append( "您发送的是地理位置消息！");  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
            	respContent.append( "您发送的是链接消息！");  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
            	respContent.append( "您发送的是音频消息！");  
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                	respContent.append("谢谢您的关注！").append("\n"); 
                	respContent.append("请输入“注册+学号+身份证号后6位”以完成注册\n");
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                	studentmessage.deletestu(fromUserName);
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
                }  
            }  
  
            textMessage.setContent(respContent.toString());  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  

}
