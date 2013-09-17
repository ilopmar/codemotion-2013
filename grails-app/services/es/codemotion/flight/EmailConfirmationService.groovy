package es.codemotion.flight

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

import org.springframework.integration.Message
import org.springframework.integration.MessagingException
import org.springframework.integration.core.MessageHandler

public class EmailConfirmationService implements MessageHandler {

    private List emails = []

    CountDownLatch countDownLatch = new CountDownLatch(1)

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        SeatConfirmation seatConfirmation = (SeatConfirmation) message.payload

        ChargedBooking chargedBooking = seatConfirmation.chargedBooking
        Booking booking = chargedBooking.booking
        String emailBody = "Your booking on flight ${booking.flightId} has been confirmed. " +
                           "You have seat number ${seatConfirmation.seat.seatNumber}"
        Email email = new Email(booking.customerEmail, emailBody)

        sendEmail(email)
    }

    // We don't send an e-mail, but an integration service actually would
    public void sendEmail(Email email) {
        Thread.sleep(4000)
        countDownLatch.countDown()

        println "Email -> ${email}"
        emails << email
    }

    public List<Email> getEmails() throws InterruptedException {
        countDownLatch.await(4, TimeUnit.SECONDS)
        countDownLatch.await()

        return this.emails
    }
}
