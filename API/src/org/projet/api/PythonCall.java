package org.projet.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.projet.api.constantes.Chemins;


/**
 * 
 * Classe qui gère l'appelle au script python du modele de classification d'image
 *
 */
public class PythonCall {
	Process mProcess;
	Chemins c = Chemins.getInstance();
	public void runScript(String path, String pathResult) {
		Process process;
		try {
			//on construit le Process pour executer le script python
			ProcessBuilder pb = new ProcessBuilder(c.getPATH_PYTHON(), 
					c.getDOSSIER_MODELE() + "classifieur.py", 
					path, 
					c.getDOSSIER_MODELE() + "poids_modele/poids",
					pathResult);
			process = pb.start();
			mProcess = process;
		} catch (Exception e) {
			System.out.println("Exception Raised" + e.toString());
		}
		
		// Permet d'afficher la sortie terminal du script dans la console
		
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
