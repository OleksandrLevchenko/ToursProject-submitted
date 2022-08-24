package org.oleksandr.tours.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tours")
public class TourSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "price", nullable = false, length = 10)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attraction attraction;

    @OneToMany(mappedBy = "tourSchedule", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public TourSchedule(Long id, Date date, Attraction attraction, Double price) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.attraction = attraction;
    }

    public TourSchedule() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}

    public Attraction getAttraction() {return attraction;}

    public void setAttraction(Attraction attraction) {this.attraction = attraction;}
}