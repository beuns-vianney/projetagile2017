package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;

public class UserDto implements Principal {
    final static Logger logger = LoggerFactory.getLogger(UserDto.class);
    private String login;
    private String nom;
    private String prenom;
    private String token;
    private char groupe;
    private int rang;
    
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return nom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public char getGroupe() {
		return groupe;
	}

	public void setGroupe(char groupe) {
		this.groupe = groupe;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public static Logger getLogger() {
		return logger;
	}

   

}
