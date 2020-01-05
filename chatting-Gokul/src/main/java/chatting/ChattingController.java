package chatting;

import chatting.Gokul.consumer.Receiver;
import chatting.Gokul.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChattingController {

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;
    private static String BOOT_TOPIC = "chatting";

    @MessageMapping("/message")
    public void sendMessage(ChattingMessage message) throws Exception {
        Thread.sleep(1000); 
        sender.send(BOOT_TOPIC, message.getMessage() + "|" + message.getUser());
    }

    @MessageMapping("/file")
    @SendTo("/topic/chatting")
    public ChattingMessage sendFile(ChattingMessage message) throws Exception {
        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
    }
}