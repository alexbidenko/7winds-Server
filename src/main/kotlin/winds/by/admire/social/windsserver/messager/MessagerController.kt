package winds.by.admire.social.windsserver.messager

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class MessagerController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    @Throws(Exception::class)
    fun sender(message: MessageObject): MessageObject {
        message.time = System.currentTimeMillis()
        return message
    }
}