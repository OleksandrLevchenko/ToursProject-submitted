package org.oleksandr.tours.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tours")
public class TourSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "attraction.id")
    private Attraction attraction;

    @Column(name = "price", nullable = false, length = 10)
    private Double price;

    public TourSchedule(Long id, Date date, Attraction attraction, Double price) {
        this.id = id;
        this.date = date;
        this.attraction = attraction;
        this.price = price;
    }

    public TourSchedule() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Attraction getAttraction() {return attraction;}

    public void setAttraction(Attraction attraction) {this.attraction = attraction;}

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}
}