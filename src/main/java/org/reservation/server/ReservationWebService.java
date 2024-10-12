package org.reservation.server;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.reservation.models.Book;
import org.reservation.models.Reservation;

import java.util.ArrayList;

@WebService(name = "Reservations")
public class ReservationWebService {
    public static ArrayList<Reservation> reservations = new ArrayList<>();

    // get all reservations
    @WebMethod
    public ArrayList<Reservation> getAllReservations() {
        return this.reservations;
    }

    // get reservation by id
    @WebMethod
    public Reservation getReservation(int id) {
        return this.reservations.stream().filter((r) -> r.getId() == id).findFirst().get();
    }

    // add reservation for user
    @WebMethod
    public Reservation addReservation(@WebParam(name = "Reservation") Reservation reservation) {
        // check if the book exists
        if (BookWebService.booksList.stream().noneMatch((b) -> b.getId() == reservation.getBookId())) {
            throw new IllegalArgumentException("Book does not exist");
        }
        // check if the user exists
        if (UserWebService.users.stream().noneMatch((u) -> u.getId() == reservation.getUserId())) {
            throw new IllegalArgumentException("User does not exist");
        }
        // check if the reservation already exists
        if (this.reservations.stream().anyMatch((r) -> r.getBookId() == reservation.getBookId() && r.getDateDebut() == reservation.getDateDebut())) {
            throw new IllegalArgumentException("Reservation already exists");
        }

        // add the reservation
        this.reservations.add(reservation);
        return reservation;
    }

}
