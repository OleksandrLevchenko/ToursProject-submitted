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

//    @Column(name = "users.id", nullable = false)
//    private Long users_id;
////
//    @ManyToOne(fetch = FetchType.LAZY)

////
//    @JoinColumn(name = "reservation.id")
////

//    @Column(name = "tours.id", nullable = false)
//    private Long tours_id;

    public Reservation(Long id, User user, TourSchedule tourSchedule) {
        this.id = id;
        this.user = user;
        this.tourSchedule = tourSchedule;
//        this.users_id = users_id;
//        this.tours_id = tours_id;
    }

    public Reservation() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TourSchedule getTourSchedule() {
        return tourSchedule;
    }

    public void setTourSchedule(TourSchedule tourSchedule) {
        this.tourSchedule = tourSchedule;
    }

    //    public Long getUsers_id() {return users_id;}
//
//    public void setUsers_id(Long users_id) {this.users_id = users_id;}
//
//    public Long getTours_id() {return tours_id;}
//
//    public void setTours_id(Long tours_id) {this.tours_id = tours_id;}
}