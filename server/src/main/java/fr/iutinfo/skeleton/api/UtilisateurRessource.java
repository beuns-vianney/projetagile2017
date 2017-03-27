package fr.iutinfo.skeleton.api;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gitlab4j.api.MethodeGitApi;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurRessource {
    
	@GET
	@Path("/connexion")
	@Produces("application/json")
	public Utilisateur connection(@FormParam("identifiant") String compte, @FormParam("mdp") String mdp) {
		Utilisateur user = null;
		try{
			MethodeGitApi mtgapi =new MethodeGitApi(compte,mdp);
		}catch(Exception e){}
		
		return user;
    }
}
