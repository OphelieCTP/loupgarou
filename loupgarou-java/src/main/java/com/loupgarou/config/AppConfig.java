package com.loupgarou.config;

import java.util.Properties;

import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.loupgarou")
@PropertySource("classpath:data-source.properties")
public class AppConfig {
	@Bean
	public PropertyPlaceholderConfigurer propSource() {
		return new PropertyPlaceholderConfigurer();
	}

}
