package es.codemotion.flight

public class SeatAvailabilityService {

    public SeatConfirmation confirmSeat(ChargedBooking chargedBooking) {
        Seat seat = new Seat("1A")
        return new SeatConfirmation(chargedBooking, seat)
    }
}
