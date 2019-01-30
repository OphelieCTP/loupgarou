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

import ch.qos.logback.core.net.SyslogOutputStream;


@ComponentScan("fr.formation.security")
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/img/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/api/**").permitAll()
		.antMatchers("/index/inscription/**").permitAll()
		.antMatchers("/**").hasAnyRole("ADMIN", "USER")
		.and()
		.formLogin()
			.loginPage("/index")
			.loginProcessingUrl("/perform_login")
			.defaultSuccessUrl("/rules", true)
			.failureUrl("/index?error=true")
			.permitAll() //acc�der � la page en �tant d�connect�
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/index")
			.permitAll()
			.and().csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		return new BCryptPasswordEncoder();
		
	}
}
