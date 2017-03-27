package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.gitlab4j.api.MethodeGitApi;
import org.gitlab4j.api.models.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurRessource {
    
	final static Logger logger = LoggerFactory.getLogger(UserResource.class);
    private static UserDao dao = getDbi().open(UserDao.class);
	
	public UtilisateurRessource() throws SQLException {
        if (!tableExist("users")) {
            logger.debug("Create table users");
            dao.createUserTable();
            dao.createTpTable();
            dao.createProgresTable();
            dao.insert(new User("belsa","bels","alexis",""));
        }
    }

	
	@POST
	@Path("/connexion")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response connection(@FormParam("identifiant") String compte, @FormParam("mdp") String mdp) {
		Utilisateur user = null;
		MethodeGitApi mtgapi;
		NewCookie cookie = null;
	    
		try{
			mtgapi = new MethodeGitApi(); 
			Session session = mtgapi.login(compte, mdp);
			if (session != null) {
//				user = RequeteBDD.usersByToken(mtgapi.getPrivateToken());
//				if (user == null) {
//					RequeteBDD.insert(session.getEmail().split(".")[1].split("@")[0], session.getEmail().split(".")[0], 1, 1, session.getPrivateToken());
//				}
				cookie = new NewCookie("ILEARN_TOKEN", mtgapi.getPrivateToken());
				
				java.net.URI location = new java.net.URI("../Index/index.html");
				ResponseBuilder r = Response.temporaryRedirect(location);
				r.cookie(cookie);
				
				return r.build();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		cookie = new NewCookie("ILEARN_TOKEN", null);
		return Response.noContent().cookie(cookie).build();
    }
}
