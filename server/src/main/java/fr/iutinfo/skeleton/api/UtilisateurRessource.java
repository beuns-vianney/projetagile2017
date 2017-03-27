package fr.iutinfo.skeleton.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurRessource {
    
	@GET
	@Path("/connexion")
	@Produces("application/json")
	public Utilisateur connection(@FormParam("identifiant") String compte, @FormParam("mdp") String mdp) {
		Utilisateur user = null;
		MethodeGitApi mtgapi;
		try{
			mtgapi =new MethodeGitApi(compte,mdp);
			mtgapi.getPrivateToken();
			
		}catch(Exception e){}
		
		
		return user;
    }
}
