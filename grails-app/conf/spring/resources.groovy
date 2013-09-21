beans = {

    xmlns si:   "http://www.springframework.org/schema/integration"
    xmlns file: "http://www.springframework.org/schema/integration/file"
    xmlns xmpp: "http://www.springframework.org/schema/integration/xmpp"

    // Flight
    si.poller(default:"true", "fixed-rate":"1000")

    si.channel(id: "bookingConfirmationRequests")
    si."service-activator"("input-channel": "bookingConfirmationRequests",
                           "output-channel": "chargedBookings",
                            ref: "billForBookingService")

    si.channel(id: "chargedBookings")
    si."service-activator"("input-channel": "chargedBookings",
                           "output-channel": "completedBookings",
                            ref: "seatAvailabilityService",
                            method: "confirmSeat")

    si."publish-subscribe-channel"(id: "completedBookings")
    si.bridge("input-channel": "completedBookings",
              "output-channel":"emailConfirmationRequests")

    si.channel(id: "emailConfirmationRequests") {
        queue(capacity: 2)
    }
    si."outbound-channel-adapter"(channel: "emailConfirmationRequests",
                                  ref: "emailConfirmationService")

    si."service-activator"("input-channel": "completedBookings",
                            ref: "statsService",
                            method: "gatherStats")

    // Photos
    fileService(es.codemotion.photo.FileService) {
        grailsApplication = ref("grailsApplication")
    }
    imageConverterService(es.codemotion.photo.ImageConverter)

    si.channel(id: "incomingFiles")
    file."inbound-channel-adapter"(channel: "incomingFiles", directory: "work")
    si."service-activator"("input-channel": "incomingFiles",
                           "output-channel": "filesToConvert",
                            ref: "fileService",
                            method: "processFiles")

    si.channel(id: "filesToConvert")
    si."service-activator"("input-channel": "filesToConvert",
                           "output-channel": "filesProcessed",
                            ref: "imageConverterService",
                            method: "applyEffect")

    si."publish-subscribe-channel"(id: "filesProcessed")
    si."service-activator"("input-channel": "filesProcessed",
                            ref: "fileService",
                            method: "deleteFile")
    si."service-activator"("input-channel": "filesProcessed",
                            ref: "browserPushService",
                            method: "pushToBrowser")

    // XMPP
    xmpp."xmpp-connection"(id: "xmppConnection",
                           user: grailsApplication.config.codemotion.xmpp.user,
                           password: grailsApplication.config.codemotion.xmpp.password,
                           host: grailsApplication.config.codemotion.xmpp.host,
                           "service-name": grailsApplication.config.codemotion.xmpp.service,
                           port: grailsApplication.config.codemotion.xmpp.port)

    // XMPP Send messages to user
    si.channel(id: "toUserChannel")
    xmpp."outbound-channel-adapter"(channel:"toUserChannel")

    // XMPP Filter
    xmpp."inbound-channel-adapter"(channel: "fromUserChannel")
    si.filter("input-channel": "fromUserChannel",
              expression: "payload.startsWith('command ')",
              "discard-channel": "chatChannelMessageReceived",
              "output-channel": "chatChannelCommand")

    // Normal message received
    si.channel(id: "chatChannelMessageReceived")
    si."service-activator"("input-channel": "chatChannelMessageReceived",
                            ref: "xmppService",
                            method: "chatMessageReceived")

    // Command message
    si.channel(id: "chatChannelCommand")
    si.transformer("input-channel": "chatChannelCommand",
                   ref: "xmppService",
                   method: "parseCommand",
                   "output-channel": "validCommands")
    // Execute the command
    si."service-activator"("input-channel": "validCommands",
                           "output-channel": "commandResponse",
                            ref: "xmppService",
                            method: "executeCommand")

    si."publish-subscribe-channel"(id: "commandResponse")
    si."service-activator"("input-channel": "commandResponse",
                            ref: "xmppService",
                            method: "sendByXMPP")

    si."service-activator"("input-channel": "commandResponse",
                            ref: "xmppService",
                            method: "sendByEmail")

}
