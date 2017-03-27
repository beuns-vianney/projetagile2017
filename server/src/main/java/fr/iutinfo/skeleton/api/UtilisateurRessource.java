package fr.iutinfo.skeleton.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurRessource {
    
	@GET
	@Path("/connexion")
	@Produces("application/json")
	public Utilisateur connection(@QueryParam("identifiant") String compte, @QueryParam("mdp") String mdp) {
		Utilisateur user = null;
		MethodeGitApi mtgapi;
		try{
			mtgapi =new MethodeGitApi(compte,mdp); 
			
			user = RequeteBDD.usersByToken(mtgapi.getPrivateToken());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return user;
    }
}
