package fr.iutinfo.skeleton.api;

import java.io.File;
import java.io.FileOutputStream;


public class Exercice {
	private String username;
	private String pathtp;
	private int numeroExercice;

	public Exercice(String username,String pathtp,int numeroExercice){
		this.username=username;
		this.pathtp=pathtp;
		this.numeroExercice=numeroExercice;
	}

	public File StringtoJava(String stojava,String filepath){
		
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
		return stoj;
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

}
