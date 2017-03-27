package fr.iutinfo.skeleton.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gitlab4j.api.models.Session;

@Path("/utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurRessource {
    
	@POST
	@Path("/connexion")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Utilisateur connection(@FormParam("identifiant") String compte, @FormParam("mdp") String mdp) {
		System.out.println("================> PAR ICI");
		Utilisateur user = null;
		MethodeGitApi mtgapi;
		try{
			System.out.println("COMPTE: "+compte);
			mtgapi = new MethodeGitApi(); 
			Session session = mtgapi.login(compte, mdp);
			System.out.println("================> PAR ICI 2");
			if (session != null) {
				user = RequeteBDD.usersByToken(mtgapi.getPrivateToken());
				if (user == null) {
					RequeteBDD.insert(session.getEmail().split(".")[1].split("@")[0], session.getEmail().split(".")[0], 1, 1, session.getPrivateToken());
				}
				System.out.println(user);
				System.out.println("================> PAR ICI 3");
			} else
				return null;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
		return user;
    }
}
