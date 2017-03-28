package fr.iutinfo.skeleton.api;

public class StatisticEtu {
	String categ;
	String titre;
	int nbcompil;
	int progress;
	
	public StatisticEtu(String categ,String titre,int nbcompil,int progress){
		this.categ=categ;
		this.titre=titre;
		this.nbcompil=nbcompil;
		this.progress=progress;
	}

	public String getCateg() {
		return categ;
	}

	public void setCateg(String categ) {
		this.categ = categ;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbcompil() {
		return nbcompil;
	}

	public void setNbcompil(int nbcompil) {
		this.nbcompil = nbcompil;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
}
