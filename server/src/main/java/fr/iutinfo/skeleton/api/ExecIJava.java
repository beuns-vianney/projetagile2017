package fr.iutinfo.skeleton.api;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
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
		Process pro = Runtime.getRuntime().exec("java -cp .."+ System.getProperty("file.separator")+"ap.jar:. "+nameProgramm,null,dir);
		List<String> outputProgamm=printLines(pro.getInputStream());
		outputProgamm.addAll(printLines(pro.getErrorStream()));
		pro.waitFor();
		return outputProgamm;
	}

	public static List<String> runTestsIJava(String nameProgram, File dir) throws Exception{
		Process pro = Runtime.getRuntime().exec("java -Dtest -cp .."+ System.getProperty("file.separator")+"ap.jar:. "+nameProgram,null,dir);
		List<String> outputProgamm=printLines(pro.getInputStream());
		outputProgamm.addAll(printLines(pro.getErrorStream()));
		pro.waitFor();
		return outputProgamm;
	}

}
