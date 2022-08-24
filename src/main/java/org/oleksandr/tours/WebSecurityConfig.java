package org.oleksandr.tours;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsService userDetailsService() {return new CustomUserDetailsService();}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider  authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/register").permitAll()
				.antMatchers("/process_register").permitAll()
				.antMatchers("register_success").permitAll()
//				.antMatchers("/login*").permitAll()
//				.antMatchers("/login**").permitAll()
//				.antMatchers("/login/**").permitAll()
//				.antMatchers("/login/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/save_attraction").hasAnyAuthority("admin")
				.antMatchers("editAttraction").hasAnyAuthority("admin")
				.antMatchers("/admin/**").hasAnyAuthority("admin")
//				.antMatchers("/user/**").hasAnyAuthority("admin", "user")
				.antMatchers("/user/**").authenticated()
//				.usernameParameter("email")
//				.defaultSuccessUrl("/")
				.anyRequest().authenticated()
				.and()
				.formLogin().permitAll()
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
				.logout().logoutSuccessUrl("/").permitAll();

//				.and()
//				.formLogin()
//				.loginPage("/login.html")
//				.loginProcessingUrl("/login").permitAll()
//				.permitAll();
	}





//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/login").setViewName("login");
//		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//	}
}