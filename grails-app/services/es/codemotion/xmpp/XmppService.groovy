package es.codemotion.xmpp

import org.springframework.integration.Message
import org.springframework.integration.support.MessageBuilder
import org.springframework.integration.xmpp.XmppHeaders

class XmppService {
    static transactional = false

    def grailsApplication
    def toUserChannel

    public void chatMessageReceived(String msg) {
        println "<< Received: ${msg}"
    }

    public XmppCommand parseCommand(String command) {
        def cmds = command.tokenize(' ')

        if (cmds[0] == "command") {
            if (cmds.size() <= 2) {
                return new HelpCommand()
            }

            def params = cmds[2..-1]
            if (cmds[1] == "weather") {
                return new WeatherCommand(params.join(" "))
            } else if (cmds[1] == "stock") {
                return new StockCommand(params)
            }
        }

        return new HelpCommand()
    }

    public String executeCommand(XmppCommand command) {
        return command.execute()
    }

    public void sendByXMPP(String msg) {
        println "Sending by XMPP..."

        Message<String> message = MessageBuilder.withPayload(msg)
            .setHeader(XmppHeaders.TO, grailsApplication.config.codemotion.xmpp.friend)
            .build()

        toUserChannel.send(message)
    }
}