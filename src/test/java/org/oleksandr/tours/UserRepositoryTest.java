package org.oleksandr.tours;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.oleksandr.tours.model.User;
import org.oleksandr.tours.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository repo;

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("Ika@Gmail.com");
		user.setPassword("ikaabe123");
		user.setFirstName("Ika");
		user.setLastName("Shala");
		User savedUser = repo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	}
	
	@Test
	public void TestFindUserByEmail() {
		String email = "Irakli0608@gmail.com";
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull();
	}
}
