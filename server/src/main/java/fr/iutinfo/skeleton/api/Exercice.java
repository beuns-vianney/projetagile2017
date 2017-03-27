package fr.iutinfo.skeleton.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Exercice {
	private int id;
	private String code;
	private String titre;
	private List<Boolean> tests = new ArrayList<>();

	public Exercice(int id, String titre, String code) {
		this(id, titre);
		this.setCode(code);
	}
	public Exercice(int id, String titre){
		this.setId(id);
		this.setTitre(titre);
	}

	public static File StringtoJava(String stojava,String filepath){
		File file = new File(filepath); // chemin absolu
		try {
			file.createNewFile();
			FileWriter writer=new FileWriter(file);
			writer.write(stojava);  // écrire une ligne dans le fichier resultat.txt
			writer.close(); // fermer le fichier à la fin des traitements
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the tests
	 */
	public List<Boolean> getTests() {
		return tests;
	}
	/**
	 * @param tests the tests to set
	 */
	public void setTests(List<Boolean> tests) {
		this.tests = tests;
	}

}
