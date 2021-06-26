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
	
	
	@RequestMapping("/chatting/{roomId}")
	public String goChatRoom(@PathVariable String roomId) {
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
	
//    @MessageMapping("/hello/{roomNo}")
//    @SendTo("/subscribe/chat/{roomNo}")
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
	    return new Chat(chat.getName(), chat.getMessage()+"---"+now_time );
    }
	

}