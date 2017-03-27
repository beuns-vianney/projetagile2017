package fr.iutinfo.skeleton.api;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExecIJava {

	  private static List<String> printLines(InputStream ins) throws Exception {
		    String line = null;
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(ins));
		    List<String> outputProgramm=new ArrayList();
		    while ((line = in.readLine()) != null) {
		        outputProgramm.add(line);
		    }
		    return outputProgramm;
		  }

		  public static List<String> runProgrammIJava(String nameProgramm,File dir) throws Exception {
		    Process pro = Runtime.getRuntime().exec("java -cp ../ap.jar:. "+nameProgramm,null,dir);
		    List<String> outputProgamm=printLines(pro.getInputStream());
		    outputProgamm.addAll(printLines(pro.getErrorStream()));
		    pro.waitFor();
		    return outputProgamm;
		  }
		  
		  public static void main(String[] args) {
			try {
				for (String s : ExecIJava.runProgrammIJava("Mdr", new File(".."))) {
					System.out.println(s);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
