package org.gitlab4j.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;

public class MethodeGitApi {
	int nombredecommit=1;
	String serveur;
	private String Private_token;
	GitLabApi glapi;
	Integer numeroproject;
	List<String> nomModule=new ArrayList<>();
	boolean compilation;
	String contenufichier;

	public MethodeGitApi(String serveur,String Private_token){
		this.serveur=serveur;
		this.Private_token=Private_token;

		GitLabApi gitLabApi = new GitLabApi(serveur, Private_token);
		glapi=gitLabApi;

		initialiserList();
	}

	public void initialiserList(){
		//INITIALISATION LISTES DES MODULES
		//S1
		nomModule.add("m1101");
		nomModule.add("m1102");
		nomModule.add("m1103");
		nomModule.add("m1104");
		nomModule.add("m1105");
		nomModule.add("m1106");
		//S2
		nomModule.add("m2101");
		nomModule.add("m2102");
		nomModule.add("m2103");
		nomModule.add("m2104");
		nomModule.add("m2105");
		nomModule.add("m2106");
		nomModule.add("m2107");
		//S3
		nomModule.add("m3101");
		nomModule.add("m3102");
		nomModule.add("m3103");
		nomModule.add("m3104");
		nomModule.add("m3105");
		nomModule.add("m3106c");
		//S4
		nomModule.add("m4101c");
		nomModule.add("m4102c");
		nomModule.add("m4103c");
		nomModule.add("m4104c");
		nomModule.add("m4105c");
		nomModule.add("m4106");

	}

	/*public static void main(String[] args) {
		GitLabApi gitLabApi = new GitLabApi("https://git-iut.univ-lille1.fr", "Hq7godj-TazXi_HF_7Yo");
		try {
			List<Project> projects = gitLabApi.getProjectApi().getProjects();
			for (Project project : projects) {
				System.out.println(project.getName()+":"+project.getId());
			}
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public void Construire_Project(GitLabApi glapi,String projectName,String description) throws GitLabApiException{
		Project nproject =new Project();
		nproject.setName(projectName);
		nproject.setDescription(description);
		nproject.setDefaultBranch("master");
		glapi.getProjectApi().createProject(nproject);
		System.out.println("projet fait");
	}

	public void recupNumeroProjet(GitLabApi glapi,String nameproject){
		try {
			List<Project> projects = glapi.getProjectApi().getProjects();
			for (Project project : projects) {
				System.out.println(project.getName()+":"+project.getId());
				if(project.getName().equals(nameproject)){
					System.out.println("catch ! "+project.getId());
					numeroproject=project.getId();
				}
			}
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void push_fichier(GitLabApi glapi,String chemin,String filename,String contenu,String commit,Integer numeroRepo) throws GitLabApiException{
		RepositoryFile nrpo=newRepositoryFile(chemin+"/"+filename, numeroRepo, contenu);
		glapi.getRepositoryFileApi().createFile(nrpo, numeroRepo,"master",commit);
		System.out.println("Fichier "+filename +" à l'endroit "+chemin+" créer");
	}

	public void prepare_repos(GitLabApi glapi,List<String> nomModule) throws GitLabApiException{
		for (String s : nomModule) {
			if(s.startsWith("m11")){
				push_fichier(glapi,"S1/"+s+"/", "README.md","Repertoire des tp du module "+s,"Creation README.md",getnumeroProject());
			}else if (s.startsWith("m21")){
				push_fichier(glapi,"S2/"+s+"/", "README.md","Repertoire des tp du module "+s,"Creation README.md",getnumeroProject());
			}else if(s.startsWith("m31")){
				push_fichier(glapi,"S3/"+s+"/", "README.md","Repertoire des tp du module "+s,"Creation README.md",getnumeroProject());
			}else{
				push_fichier(glapi,"S4/"+s+"/", "README.md","Repertoire des tp du module "+s,"Creation README.md",getnumeroProject());
			}
		}
		System.out.println("Préparation terminé");
	}

	public void modifier_fichier(GitLabApi glapi,Integer projectid,String cheminfichier,String contenu,boolean compil) throws GitLabApiException{
		nombredecommit++;
		glapi.getRepositoryFileApi().updateFile(newRepositoryFile(cheminfichier,projectid,contenu), projectid, "master", "Commit: "+nombredecommit+" compilation: "+compilation);
		System.out.println("Update fichier: "+cheminfichier+" commit n:"+nombredecommit);
	}

	private RepositoryFile newRepositoryFile(String cheminduFichier,Integer numeroRepos,String contenu) throws GitLabApiException {
		RepositoryFile nrpo=new RepositoryFile();
		nrpo.setFilePath(cheminduFichier);
		nrpo.setContent(contenu);
		return nrpo;
	}

	public List<String > getModulenom(){
		return nomModule;
	}

	public GitLabApi getGLAPI(){
		return this.glapi;
	}

	public Integer getnumeroProject(){
		return numeroproject;
	}


	public void recupContenuFichier(String filepath) throws GitLabApiException, UnsupportedEncodingException{
		RepositoryFile rpo =glapi.getRepositoryFileApi().getFile(filepath, numeroproject,"master");
		byte [] decoded =Base64.getDecoder().decode(rpo.getContent());
		String s=new String(decoded,"UTF-8"); 
		contenufichier=s;
	}

	public static void main(String[] args) throws GitLabApiException {
		MethodeGitApi outil=new MethodeGitApi("https://git-iut.univ-lille1.fr", "Hq7godj-TazXi_HF_7Yo");

		try{
			outil.Construire_Project(outil.getGLAPI(), "I-Learn-Repository", "Programmation DUT-Info");
		}catch(Exception e){}

		outil.recupNumeroProjet(outil.glapi,"I-Learn-Repository");

		try{
			outil.prepare_repos(outil.glapi, outil.getModulenom());
		}catch (Exception e) {}

		//outil.test(outil);
	}

	/*public void test(MethodeGitApi outil){
		try{
			outil.push_fichier(outil.glapi, "S2/m2101/","HelloWorld.java","Ah bah mince c'est pas du IJAVA", "Commit: "+outil.nombredecommit+" Compilation: "+outil.compilation, outil.numeroproject);
		}catch(Exception e){}

		try{
			outil.modifier_fichier(outil.glapi, outil.numeroproject, "/S2/m2101/HelloWorld.java","Toujours pas ",outil.compilation);
		}catch (Exception e) {}
		
		
		try{
			outil.recupContenuFichier("/S2/m2101/HelloWorld.java");
		}catch(Exception e){}
		
		System.out.println(outil.contenufichier);
	}*/

}
