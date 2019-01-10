package com.loupgarou.datajpa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.loupgarou.config.JPAConfig;
import com.loupgarou.model.Utilisateur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfig.class})

public class IDAOUtilisateurTest {
	
	@Autowired(required=false)
	IDAOUtilisateur daoU;
	
	@Test
	public void authentification()
	{
		Optional<Utilisateur> userTest = daoU.findByUserNameAndPassWordAndIsBanni("Adrien", "12345", false);
		assertTrue("L'utilisateur n'a pas été trouvé", userTest.isPresent());
		assertNotNull("Utilisateur trouvé mais résultat nul !", userTest.get());
		assertNotNull("Utilisateur trouvé mais informatio non remonté", userTest.get().getUserName());
		
		userTest = null;
		userTest = daoU.findByUserNameAndPassWordAndIsBanni("Adrien", "12345", true);
		assertFalse("L'utilisateur banni a été trouvé", userTest.isPresent());
		
	}
}
