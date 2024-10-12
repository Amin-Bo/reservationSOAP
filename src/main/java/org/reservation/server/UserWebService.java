package org.reservation.server;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.reservation.models.Reservation;
import org.reservation.models.User;

import java.util.ArrayList;

@WebService(name = "Users")
public class UserWebService {
    public static ArrayList<User> users = new ArrayList<>();

    // get all users
    @WebMethod
    public ArrayList<User> getAllUsers() {
        return this.users;
    }

    // get user by id
    @WebMethod
    public User getUser(int id) {
        User user = this.users.stream().filter((r) -> r.getId() == id).findFirst().get();
        user.setReservations(ReservationWebService.reservations.stream().filter((r) -> r.getUserId() == id).toList());
        return user;
    }

    // add reservation for user
    @WebMethod
    public User addUser(@WebParam(name = "User") User user) {

        // check if the user already exists
        if (this.users.stream().anyMatch((r) -> r.getEmail() == user.getEmail() && r.getId() == user.getId())) {
            throw new IllegalArgumentException("User already exists");
        }

        // add the reservation
        this.users.add(user);
        return user;
    }

}
