package es.codemotion.flight

import groovy.transform.Canonical

@Canonical
public class ChargedBooking {
    Booking booking
    Long confirmationNumber
}
