package org.xiaowu.course.message.req;
/**
*  文字消息
* @author xiaowu 
* @date 2014-06-18
*/  
public class TextMessage extends BaseMessage{
	 // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
    public void setContent(String content) {  
        Content = content;  
    }  
}
