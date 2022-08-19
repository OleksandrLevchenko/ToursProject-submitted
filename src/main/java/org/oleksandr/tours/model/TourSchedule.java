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

    @Column(name = "attraction.id", nullable = false, length = 20)
    private String attraction_id;

    @Column(name = "price", nullable = false, length = 10)
    private Double price;

    public TourSchedule(Long id, Date date, String attraction_id, Double price) {
        this.id = id;
        this.date = date;
        this.attraction_id = attraction_id;
        this.price = price;
    }

    public TourSchedule() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public String getAttraction_id() {return attraction_id;}

    public void setAttraction_id(String attraction_id) {this.attraction_id = attraction_id;}

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}
}