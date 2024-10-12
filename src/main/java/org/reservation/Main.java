package org.reservation;

import jakarta.xml.ws.Endpoint;
import org.reservation.server.BookWebService;
import org.reservation.server.ReservationWebService;
import org.reservation.server.UserWebService;

public class Main {
    public static void main(String[] args) {
        String url = "http://localhost:8088/";
        Endpoint.publish(url + "books", new BookWebService());
        System.out.println(url + "books" + " deployed");
        Endpoint.publish(url + "reservations", new ReservationWebService());
        System.out.println(url + "reservations" + " deployed");
        Endpoint.publish(url + "users", new UserWebService());
        System.out.println(url + "users" + " deployed");
    }
}