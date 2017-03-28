package fr.iutinfo.skeleton.api;

public class Tp {

	
	int id;
	String categ;
	String titre;
	String path;
	
	public Tp() {
		// TODO Auto-generated constructor stub
	}
	
	public Tp(String titre){
		this.titre=titre;
	}
	
	public Tp(String titre,String path){
		this.titre=titre;
		this.path=path;
	}
	
	public Tp(int id,String titre,String path){
		this.id=id;
		this.titre=titre;
		this.path=path;
	}
	
	public Tp(int id,String categ,String titre,String path){
		this.id=id;
		this.categ=categ;
		this.titre=titre;
		this.path=path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
