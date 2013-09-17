beans = {

    xmlns si:   "http://www.springframework.org/schema/integration"

    // Flight
    si.channel(id: "bookingConfirmationRequests")
    si."service-activator"("input-channel": "bookingConfirmationRequests",
                           "output-channel": "chargedBookings",
                            ref: "billForBookingService")

    si.channel(id: "chargedBookings")
    si."service-activator"("input-channel": "chargedBookings",
                           "output-channel": "emailConfirmationRequests",
                            ref: "seatAvailabilityService",
                            method: "confirmSeat")

    si.channel(id: "emailConfirmationRequests")
    si."outbound-channel-adapter"(channel: "emailConfirmationRequests",
                                  ref: "emailConfirmationService")

}
