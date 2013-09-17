package es.codemotion.flight

import org.springframework.integration.annotation.MessageEndpoint
import org.springframework.integration.annotation.ServiceActivator

@MessageEndpoint
public class BillForBookingService {

    @ServiceActivator
    public ChargedBooking pay(Booking booking) {
        return new ChargedBooking(booking, 1)
    }
}