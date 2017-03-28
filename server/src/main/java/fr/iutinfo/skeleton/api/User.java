package fr.iutinfo.skeleton.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.UserDto;

public class User implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);
    private String login;
    private String nom;
    private String prenom;
    private String token;
    private String groupe;
    private int rang;


    public User(String login, String nom,String prenom,String token) {
        this.login = login;
        this.nom = nom;
        this.prenom=prenom;
        this.token=token;
    }


    public User() {
    }

    @Override
    public String toString() {
        return login + ": " + nom + ", " + prenom + " <" + token + ">";
    }

    public void initFromDto(UserDto dto) {
        this.setLogin(dto.getLogin());
        this.setNom(dto.getNom());
        this.setPrenom(dto.getPrenom());
        this.setToken(dto.getToken());
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


	public String getGroupe() {
		return groupe;
	}


	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}


	public int getRang() {
		return rang;
	}


	public void setRang(int rang) {
		this.rang = rang;
	}


	public UserDto convertToDto() {
        UserDto dto = new UserDto();
        dto.setNom(this.getNom());
        dto.setPrenom(this.getPrenom());
        dto.setLogin(this.getLogin());
        dto.setToken(this.getToken());
        return dto;
    }


	@Override
	public String getName() {
		return nom;
	}
}
