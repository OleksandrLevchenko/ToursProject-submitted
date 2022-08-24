package org.oleksandr.tours.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
//////////
//	private boolean enabled;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();
//////////
	@ManyToMany(targetEntity = TourSchedule.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "reservations")
	private List<TourSchedule> userTours;

	@OneToMany(mappedBy = "tourSchedule", cascade = CascadeType.ALL)
	private List<Reservation> reservations;

	public User(Long id, String email, String password, String firstName, String lastName, List<TourSchedule> userTours) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userTours = userTours;
	}

	public User() {}

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}

	public String getFirstName() {return firstName;}

	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}

	public void setLastName(String lastName) {this.lastName = lastName;}

//////////
//	public boolean isEnabled() {return enabled;}
//
//	public void setEnabled(boolean enabled) {this.enabled = enabled;}

	public Set<Role> getRoles() {return roles;}

	public void setRoles(Set<Role> roles) {this.roles = roles;}

	public List<Reservation> getReservations() {return reservations;}

	public void setReservations(List<Reservation> reservations) {this.reservations = reservations;}
//////////
	public List<TourSchedule> getUserTours() {return userTours;}

	public void setUserTours(List<TourSchedule> userTours) {this.userTours = userTours;}
}