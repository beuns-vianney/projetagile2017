package fr.iutinfo.skeleton.api;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/exercice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExerciceRessource {
	
	private static String name;
	
	@POST
//	@Consumes("application/json")
	@Produces("application/json")
	public Message createExercice(String code) {
		String codeNettoye = code.split(":")[1];
		codeNettoye = codeNettoye.substring(1, codeNettoye.length()-2);
		codeNettoye = codeNettoye.replaceAll("\\\\n", "\n");
		codeNettoye = codeNettoye.replaceAll("\\\\\"", "\"");
		while (codeNettoye.startsWith(" ")){
			codeNettoye = codeNettoye.substring(1);
		}
		name = codeNettoye.split(" ")[1];
		System.out.println(name);
		File fichier = Exercice.StringtoJava(codeNettoye, "./" + name + ".java");
		StringBuffer reponseCompilation = new StringBuffer();
		ArrayList<String> l = (ArrayList<String>) JavaCompilerProject.CompilationIJava(fichier);
		for (String string : l) {
			reponseCompilation.append(string+"\n");
		}
		if (reponseCompilation.toString().isEmpty())
			reponseCompilation.append("Compilation Successful !");
		Message msg = new Message();
		msg.setRetour(reponseCompilation.toString());	
		msg.setName(name);
		return msg;
	}
	
	@GET
	@Produces("application/json")
	public ExecReturn getExecution(){
		ArrayList<String> results =null;
		try {
			results = (ArrayList<String>) ExecIJava.runProgrammIJava(name, new File("."));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExecReturn msg = new ExecReturn();
		msg.setRetour(results.toArray(new String[0]));
		
		return msg;
	}

}
