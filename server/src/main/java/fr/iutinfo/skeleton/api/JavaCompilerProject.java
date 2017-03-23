package fr.iutinfo.skeleton.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaCompilerProject {

	public static List<String> CompilationIJava(File fichier) {
		List<String> listError = new ArrayList<>();
		
		String[] options= {"-cp", "../ap.jar:."};
		DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<>();
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticsCollector, null, null);
		List<File> pathList = new ArrayList<>();
		pathList.add(fichier);
		
		List<String> optionsList = new ArrayList<>(Arrays.asList(options));
		
		Iterable<String> optionsCompilation = optionsList;
		Iterable<? extends JavaFileObject> compilationUnits =fileManager.getJavaFileObjectsFromFiles (pathList);
		CompilationTask task = compiler.getTask(null,fileManager,diagnosticsCollector, optionsCompilation, null, compilationUnits);
		boolean result = task.call();
		if (result) {
			System.out.println ("Compilation was successful");
		} else {
			
			System.out.println ("Compilation failed");
			List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticsCollector.getDiagnostics();
	        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
	            // read error dertails from the diagnostic object
	            listError.add("Ligne "+diagnostic.getLineNumber()+": "+diagnostic.getKind()+" "+diagnostic.getMessage(null) );
	        }
		}
		try {
			fileManager.close ();
		} catch (IOException e) {
		}
		return listError;
	}
}
