package com.loupgarou.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.loupgarou.model.Utilisateur;


public class UtilisateurPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
	
	public UtilisateurPrincipal(Utilisateur utilisateur) {
		if (utilisateur == null) {
			throw new UsernameNotFoundException("L'utilisateur n'existe pas.");
		}
		this.utilisateur = utilisateur;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> myAuthorities = new ArrayList<GrantedAuthority>();
		//myAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(this.utilisateur.getUserID()==1 || this.utilisateur.getUserID()==2) { 
			myAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
		}
		else { myAuthorities.add(new SimpleGrantedAuthority("ROLE_USER")); } 
	
		return myAuthorities;
	}

	public String getPassword() {
		return this.utilisateur.getPassWord();
	}

	public String getUsername() {
		return this.utilisateur.getUserName();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		if(this.utilisateur.getIsBanni())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	

}
