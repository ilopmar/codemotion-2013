beans = {

    xmlns si:   "http://www.springframework.org/schema/integration"

    // Flight
    si.poller(default:"true", "fixed-rate":"1000")

    si.channel(id: "bookingConfirmationRequests")
    si."service-activator"("input-channel": "bookingConfirmationRequests",
                           "output-channel": "chargedBookings",
                            ref: "billForBookingService")

    si.channel(id: "chargedBookings")
    si."service-activator"("input-channel": "chargedBookings",
                           "output-channel": "emailConfirmationRequests",
                            ref: "seatAvailabilityService",
                            method: "confirmSeat")

    si.channel(id: "emailConfirmationRequests") {
        queue(capacity: 2)
    }
    si."outbound-channel-adapter"(channel: "emailConfirmationRequests",
                                  ref: "emailConfirmationService")

}
