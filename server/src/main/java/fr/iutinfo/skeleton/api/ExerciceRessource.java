package fr.iutinfo.skeleton.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/exercice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExerciceRessource {
	
	@POST
	public String createExercice(Exercice exo) {
		System.out.println(exo.getCode());
		return exo.getCode();
	}

}
