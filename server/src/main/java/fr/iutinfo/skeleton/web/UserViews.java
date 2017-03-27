package fr.iutinfo.skeleton.web;


import fr.iutinfo.skeleton.api.BDDFactory;
import fr.iutinfo.skeleton.api.UserDao;
import org.glassfish.jersey.server.mvc.Template;
import fr.iutinfo.skeleton.api.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public class UserViews {
    private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);

    @GET
    @Template
    public List<User> getAll() {
        return dao.all();
    }

    @GET
    @Template(name = "detail")
    @Path("/{login}")
    public User getDetail(@PathParam("login") String login) {
        User user = null;
        if ("-1".equals(login)) {
            //user = User.getAnonymousUser();
        } else {
            user = dao.findByLogin(login);
        }
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }

}

