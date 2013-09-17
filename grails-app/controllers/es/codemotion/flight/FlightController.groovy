package es.codemotion.flight

import org.springframework.integration.Message
import org.springframework.integration.support.MessageBuilder

class FlightController {

    def bookingConfirmationRequests

    def bookAFlight() {

        Booking booking = new Booking()
        booking.customerEmail = "user@example.com"
        booking.flightId = "AC100"

        Message<Booking> bookingMessage = MessageBuilder.withPayload(booking).build()
        bookingConfirmationRequests.send(bookingMessage)

        render "Done! An email has sent with booking information"
    }
}