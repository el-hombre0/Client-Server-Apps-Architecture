package com.example.acsa_pr4;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {


    @MessageMapping("/webs")
    @SendTo("/topic/greetings")
    public Message greeting(MainMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        return new Message(HtmlUtils.htmlEscape(message.getName()));
    }

}