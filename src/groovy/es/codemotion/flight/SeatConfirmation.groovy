package es.codemotion.flight

import groovy.transform.Canonical

@Canonical
public class SeatConfirmation {
    ChargedBooking chargedBooking
    Seat seat
}
