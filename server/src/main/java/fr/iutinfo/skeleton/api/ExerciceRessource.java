package fr.iutinfo.skeleton.api;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/exercice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExerciceRessource {
	
	@POST
//	@Consumes("application/json")
	@Produces("application/json")
	public Message createExercice(String code) {
		String codeNettoye = code.split(":")[1];
		codeNettoye = codeNettoye.substring(1, codeNettoye.length()-2);
		codeNettoye = codeNettoye.replaceAll("\\\\n", "\n");
		codeNettoye = codeNettoye.replaceAll("\\\\\"", "\"");
		File fichier = Exercice.StringtoJava(codeNettoye, "./test.java");
		StringBuffer reponseCompilation = new StringBuffer();
		ArrayList<String> l = (ArrayList<String>) JavaCompilerProject.CompilationIJava(fichier);
		for (String string : l) {
			reponseCompilation.append(string+"\n");
		}
		if (reponseCompilation.toString().isEmpty())
			reponseCompilation.append("Compilation Successful !");
		Message msg = new Message();
		msg.setRetour(reponseCompilation.toString());		
		return msg;
	}

}
