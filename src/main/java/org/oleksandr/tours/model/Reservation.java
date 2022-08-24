package org.oleksandr.tours.model;

import javax.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private TourSchedule tourSchedule;

    @Column(name = "user_tours_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userToursId;

    public Reservation(Long id, User user, TourSchedule tourSchedule) {
        this.id = id;
        this.user = user;
        this.tourSchedule = tourSchedule;
    }

    public Reservation() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public TourSchedule getTourSchedule() {return tourSchedule;}

    public void setTourSchedule(TourSchedule tourSchedule) {this.tourSchedule = tourSchedule;}
}