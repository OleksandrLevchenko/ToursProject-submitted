package org.oleksandr.tours.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "attractions")
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//
    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL)
    private List<TourSchedule> tours;
//


    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    public Attraction(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Attraction() {}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}