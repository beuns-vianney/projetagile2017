package fr.iutinfo.skeleton.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Exercice {
	private String username;
	private String pathtp, code;
	private int numeroExercice;

	public Exercice(String code) {
		this.setCode(code);
	}
	public Exercice(String username,String pathtp,int numeroExercice){
		this.username=username;
		this.pathtp=pathtp;
		this.numeroExercice=numeroExercice;
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
		/*
		FileOutputStream fos=null;
		File stoj=null;
		try{
			stoj=new File(filepath);
			fos =new FileOutputStream(stoj);

			if(!stoj.exists()){
				stoj.createNewFile();
			}

			byte [] tab= stojava.getBytes();

			fos.write(tab);
			fos.flush();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fos !=null){
					fos.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return stoj;*/
	}

	public String getUsername(){
		return username;
	}

	public String getExercice(){
		return pathtp;
	}

	public int getNumeroExercice(){
		return numeroExercice;
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

}
