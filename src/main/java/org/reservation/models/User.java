package org.reservation.models;


import lombok.Data;
import org.eclipse.persistence.annotations.UuidGenerator;

import java.util.List;

@Data
public class User {
    @UuidGenerator(name = "id")
    private int id;

    private String email;

    private List<String> roles;

    private String password;

    private List<Reservation> reservations;
}
