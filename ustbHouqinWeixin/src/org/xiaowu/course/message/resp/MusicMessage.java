package org.xiaowu.course.message.resp;
/**
*  音频消息
* @author xiaowu 
* @date 2014-06-18
*/  
public class MusicMessage extends BaseMessage{
	  // 音乐  
    private Music Music;  
  
    public Music getMusic() {  
        return Music;  
    }  
  
    public void setMusic(Music music) {  
        Music = music;  
    }  
}
