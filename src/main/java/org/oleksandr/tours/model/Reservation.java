package org.oleksandr.tours.model;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "users.id", nullable = false)
    private Long users_id;

    @Column(name = "tours.id", nullable = false)
    private Long tours_id;

}
