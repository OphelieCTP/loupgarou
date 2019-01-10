package com.loupgarou;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.loupgarou.config.JPAConfig;


public class SpringApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext myContext = new AnnotationConfigApplicationContext(JPAConfig.class);
		myContext.getBeanFactory().createBean(PrincipaleJpaData.class).run(args);
		myContext.close();
	}

}
