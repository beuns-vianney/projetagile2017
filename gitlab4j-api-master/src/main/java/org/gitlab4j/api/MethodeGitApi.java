package org.gitlab4j.api;

import java.util.List;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;

public class MethodeGitApi {
	int nombredecommit=0;
	String serveur;
	private String Private_token;
	GitLabApi glapi;
	
	public MethodeGitApi(String serveur,String Private_token){
		this.serveur=serveur;
		this.Private_token=Private_token;
		
		GitLabApi gitLabApi = new GitLabApi("https://git-iut.univ-lille1.fr", "Hq7godj-TazXi_HF_7Yo");
		glapi=gitLabApi;
	}
	
	/*public static void main(String[] args) {
		GitLabApi gitLabApi = new GitLabApi("https://git-iut.univ-lille1.fr", "Hq7godj-TazXi_HF_7Yo");
		try {
			List<Project> projects = gitLabApi.getProjectApi().getProjects();
			for (Project project : projects) {
				System.out.println(project.getName());
			}
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	public void Construire_Project(GitLabApi glapi,String projectName,String description,String importUrl) throws GitLabApiException{
		
		ProjectApi prjapi=new ProjectApi(glapi);
		prjapi.createProject(projectName, null, description, null, null, null, null, null, null, null, importUrl);
		System.out.println("projet creer");
	}
	
	public void push_fichier(GitLabApi glapi){
		
	}
	
	public void modifier_fichier(GitLabApi glapi,Integer projectid,String cheminfichier,boolean compil){
		RepositoryFileApi rpfapi=new RepositoryFileApi(glapi);
		//rpfapi.updateFile(rpfapi,projectid,cheminfichier,"Compilation "+nombredecommit+"res: "+compil);
	}
	
	public static void main(String[] args) throws GitLabApiException {
		MethodeGitApi test=new MethodeGitApi("https://git-iut.univ-lille1.fr", "Hq7godj-TazXi_HF_7Yo");
		test.Construire_Project(test.getGLAPI(), "test2", "dossier test 2", null);
		
	}
	
	public GitLabApi getGLAPI(){
		return this.glapi;
	}
	
}
