package org.oleksandr.tours.repo;

import org.oleksandr.tours.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

	public interface UserRepository extends JpaRepository<User, Long> {
		@Query("Select u from User u Where u.email = ?1")
		User findByEmail(String email);

//	    @Query("SELECT u FROM User u WHERE u.username = :username")
//	    public User getUserByUsername(@Param("username") String username);
}