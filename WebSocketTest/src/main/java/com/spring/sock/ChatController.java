package com.spring.sock;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;


@Controller
public class ChatController {
	
	@RequestMapping("/chatting")
	public String goChatRoom() {
		return "chatting" ; 
	}
	
	// 다중 채팅방 전용 
	// DB에서 읽어와서 roomId가 들어간 주소를 호출한다. 
	@RequestMapping("/chatting/{roomId}")
	public String goChatRoom(@PathVariable String roomId) {
		
		// 보내주기 전에 검사할 것
		// roomId 와 session에 저장된 유저 정보를 가지고 DB로 가서
		// 현재 방에 포함된 인원인지 확인하기 
		
		// 만약 1:1 채팅방인데 잘못 들어온 거라면 
		// 잘못된 접근으로 처리하기 
		
		// 경매 채팅방이라면 그냥 바로 들어오도록 처리하기
		// 1:1 채팅방과 경매 채팅방을 구분해줘야 할까? 
		return "chatting" ; 
	}
	
	@RequestMapping("/chatting/1")
	public String goChatRoom1() {
		return "chatting" ; 
	}
	
	@RequestMapping("/chatting/2")
	public String goChatRoom2() {
		return "chatting" ; 
	}

	  @MessageMapping("/hello")
	  @SendTo("/topic/greetings")
	  public Greeting greeting(HelloMessage message) throws Exception {
	    Thread.sleep(1000); // simulated delay
	    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	  }
	
	  @MessageMapping("/hello2")
	  @SendTo("/topic/greetings2")
	  public Greeting greeting2(HelloMessage message) throws Exception {
	    Thread.sleep(1000); // simulated delay
	    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	  }
	
	@MessageMapping("/chat")
  	@SendTo("/topic/chat")
	public Chat broadcasting(Chat chat) {
    	Date date = new Date(System.currentTimeMillis()); 
	    SimpleDateFormat format = new SimpleDateFormat("HH:mm"); 
	    String now_time = format.format(date); 
	    return new Chat(chat.getName(), chat.getMessage()+"---"+now_time );
    }
    
	
	@MessageMapping("/chat/{roomId}")
  	@SendTo("/topic/chat/{roomId}")
	public Chat broadcasting2(@DestinationVariable String roomId, Chat chat) {
    	Date date = new Date(System.currentTimeMillis()); 
	    SimpleDateFormat format = new SimpleDateFormat("HH:mm"); 
	    String now_time = format.format(date); 
	    
	    // 채팅도 여기에서 저장해주자. 
	    
	    return new Chat(chat.getName(), chat.getMessage()+"---"+now_time );
    }
	

}