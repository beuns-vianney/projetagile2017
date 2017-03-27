package org.gitlab4j.api;

import java.util.List;

import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Session;

public class GitTestApi {

	public static void main(String[] args) {
		GitLabApi gitLabApi = new GitLabApi("https://git-iut.univ-lille1.fr", "ZMzK4uyHXpvCBwut2yka");
		Session s;
		try {
			s = gitLabApi.getSessionApi().login("belsa",null, "151019971info");
			System.out.println(s.getPrivateToken());
			gitLabApi=new GitLabApi("https://git-iut.univ-lille1.fr", s.getPrivateToken());
		} catch (GitLabApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			List<Project> projects = gitLabApi.getProjectApi().getProjects();
			for (Project project : projects) {
				
				System.out.println(project.getName()+"  branche "+project.getDefaultBranch());
			}
			//System.out.println(gitLabApi.getSessionApi().login("belsa", "alexis.bels@etudiant.univ-lille1.fr", "151019971info").getBio());
			
			
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

