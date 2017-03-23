package fr.iutinfo.skeleton.api;

import java.io.File;
import java.util.ArrayList;

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
	public String createExercice(String code) {
		String codeNettoye = code.split(":")[1];
		codeNettoye = codeNettoye.substring(1, codeNettoye.length()-2);
		codeNettoye.replaceAll("\\n", "");
		codeNettoye.replaceAll("\\\"", "\"");
		File fichier = Exercice.StringtoJava(codeNettoye, "./test.java");
		StringBuffer reponseCompilation = new StringBuffer();
		ArrayList<String> l = (ArrayList<String>) JavaCompilerproject.CompilationIJava(fichier);
		for (String string : l) {
			reponseCompilation.append(string+"\n");
		}
		System.out.println("ICI ==============> " + reponseCompilation.toString());
		return reponseCompilation.toString();
	}

}
