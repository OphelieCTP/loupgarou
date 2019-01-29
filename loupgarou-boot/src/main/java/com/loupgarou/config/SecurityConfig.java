package com.loupgarou.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.loupgarou.security.AuthService;


@ComponentScan("fr.formation.security")
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthService authService;
	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user").password("{noop}user").roles("USER")
//		.and()
//		.withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
//	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/asset/**").permitAll()
		.antMatchers("/**").hasAnyRole("ADMIN", "USER")
		.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/perform_login")
			.defaultSuccessUrl("/home", true)
			.failureUrl("/login?error=true")
			.permitAll() //acc�der � la page en �tant d�connect�
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.permitAll();
	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.authService);
////		auth.inMemoryAuthentication()
////		.withUser("admin").password("{noop}admin123456").roles("ADMIN")
////		.and()
////		.withUser("user").password("{noop}user123456").roles("USER");
//	}
//	
//	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		return new BCryptPasswordEncoder();
	}
}
