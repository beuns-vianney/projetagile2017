package fr.iutinfo.skeleton.api;

import java.util.ArrayList;
import java.util.List;

public class TravailPratique {
	private int id;
	private String titre, categorie;
	private List<Exercice> exercices = new ArrayList<>();
	
	public TravailPratique(int id, String titre, String categorie) {
		this.setId(id);
		this.setTitre(titre);
		this.setCategorie(categorie);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @return the exercices
	 */
	public List<Exercice> getExercices() {
		return exercices;
	}

	/**
	 * @param exercices the exercices to set
	 */
	public void setExercices(List<Exercice> exercices) {
		this.exercices = exercices;
	}
}
