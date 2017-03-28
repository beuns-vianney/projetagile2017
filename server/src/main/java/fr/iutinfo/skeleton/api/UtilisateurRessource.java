package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.gitlab4j.api.MethodeGitApi;
import org.gitlab4j.api.models.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.UserDto;

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
			dao.insertUser(new User("belsa","bels","alexis",""));
		}
		if (!tableExist("users")) {
			dao.createTpTable();
			dao.insertTp(new Tp(0,"m0000","tp1","./"));
		}
		if (!tableExist("progres")) {
			dao.createProgresTable();
		}
	}

	@GET
	@Path("/{login}")
	public UserDto getUser(@PathParam("login") String login) {
		User user = dao.findByLogin(login);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user.convertToDto();
	}

	@POST
	@Path("/connexion")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response connection(@FormParam("identifiant") String compte, @FormParam("mdp") String mdp) {
		User user = null;
		MethodeGitApi mtgapi;
		NewCookie cookie = null;

		try{
			mtgapi = new MethodeGitApi(); 
			Session session = mtgapi.login(compte, mdp);
			if (session != null) {
				user = dao.findByToken(mtgapi.getPrivateToken());
				if(user == null){
					User u = new User(compte, session.getEmail().split(".")[1].split("@")[0], session.getEmail().split(".")[0], mtgapi.getPrivateToken());
					dao.insertUser(u);
				}
				cookie = new NewCookie("ILEARN_TOKEN", mtgapi.getPrivateToken());
				java.net.URI location;
				if (compte.equals("ilearn"))
					location = new java.net.URI("../Admin/stats.html");
				else
					location = new java.net.URI("../Index/index.html");
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
