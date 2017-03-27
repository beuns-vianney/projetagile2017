package fr.iutinfo.skeleton.api;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    private String nom;
    private String prenom;
    private int id = 0, groupe = 1;
    private String token;
    private List<TravailPratique> tps = new ArrayList<>();

    public Utilisateur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        Utilisateur user = (Utilisateur) arg;
        return nom.equals(user.nom) && prenom.equals(user.prenom) && token.equals(user.token) && id == user.id && groupe == user.groupe;
    }

    @Override
    public String toString() {
        return id + ": " + nom + ", " + prenom + " <" + groupe + ">";
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

	public int getGroupe() {
		return groupe;
	}

	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the tps
	 */
	public List<TravailPratique> getTps() {
		return tps;
	}

	/**
	 * @param tps the tps to set
	 */
	public void setTps(List<TravailPratique> tps) {
		this.tps = tps;
	}
}
