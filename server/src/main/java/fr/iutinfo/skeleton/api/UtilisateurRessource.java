package fr.iutinfo.skeleton.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.gitlab4j.api.models.Session;

@Path("/utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurRessource {
    
	@POST
	@Path("/connexion")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response connection(@FormParam("identifiant") String compte, @FormParam("mdp") String mdp) {
		Utilisateur user = null;
		MethodeGitApi mtgapi;
		NewCookie cookie = null;
	    
		try{
			System.out.println("COMPTE: "+compte);
			mtgapi = new MethodeGitApi(); 
			Session session = mtgapi.login(compte, mdp);
			System.out.println("MDP: "+mdp);
			if (session != null) {
//				user = RequeteBDD.usersByToken(mtgapi.getPrivateToken());
//				if (user == null) {
//					RequeteBDD.insert(session.getEmail().split(".")[1].split("@")[0], session.getEmail().split(".")[0], 1, 1, session.getPrivateToken());
//				}
				System.out.println("TOKEN: "+mtgapi.getPrivateToken());
				cookie = new NewCookie("ILEARN_TOKEN", session.getPrivateToken());
				
				java.net.URI location = new java.net.URI("../Index/index.html");
				ResponseBuilder r = Response.temporaryRedirect(location);
				r.cookie(cookie);
				
				
				return r.build();
//				System.out.println(user);
//				System.out.println("================> PAR ICI 3");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		cookie = new NewCookie("ILEARN_TOKEN", null);
		return Response.noContent().cookie(cookie).build();
		//return user;
    }
}
