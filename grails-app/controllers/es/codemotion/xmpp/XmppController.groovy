package es.codemotion.xmpp

import org.springframework.integration.Message
import org.springframework.integration.support.MessageBuilder
import org.springframework.integration.xmpp.XmppHeaders

class XmppController {

    def grailsApplication
    def toUserChannel

    def chat(String msg) {
        msg = msg ?: "Hello Codemotion from Spring Integration XMPP!"

        Message<String> message = MessageBuilder.withPayload(msg)
                .setHeader(XmppHeaders.TO, grailsApplication.config.codemotion.xmpp.friend)
                .build()

        toUserChannel.send(message)

        render "Done!"
    }
}