package org.gitlab4j.api;

import java.util.List;

import org.gitlab4j.api.models.Project;

public class GitTestApi {

	public static void main(String[] args) {
		GitLabApi gitLabApi = new GitLabApi("https://git-iut.univ-lille1.fr", "Hq7godj-TazXi_HF_7Yo");
		try {
			List<Project> projects = gitLabApi.getProjectApi().getProjects();
			for (Project project : projects) {
				System.out.println(project.getName());
			}
		} catch (GitLabApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
