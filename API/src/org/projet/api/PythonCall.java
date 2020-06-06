package org.projet.api;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class PythonCall {
	final static String DOSSIER = "C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Modele_IA\\";
	Process mProcess;

	public void runScript(String path, String pathResult) {
		Process process;
		try {
			System.out.println("PATH: " + path);
			System.out.println("PATH RESULT: " + pathResult);
			ProcessBuilder pb = new ProcessBuilder("python", 
					DOSSIER + "classifieur.py", 
					path, 
					DOSSIER + "poids_modele\\poids",
					pathResult);
			//ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\Stephane\\Documents\\hw.py", "a1");
			process = pb.start();
			mProcess = process;
		} catch (Exception e) {
			System.out.println("Exception Raised" + e.toString());
		}
		
		InputStream stdout = mProcess.getInputStream();
		InputStream stsin = mProcess.getErrorStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
		BufferedReader readerError = new BufferedReader(new InputStreamReader(stsin, StandardCharsets.UTF_8));
		String line;
		try {
			while (((line = reader.readLine()) != null) || ((line = readerError.readLine()) != null)) {
				System.out.println("stdout: " + line);
			}
		} catch (IOException e) {
			System.out.println("Exception in reading output" + e.toString());
		}
	}

}
