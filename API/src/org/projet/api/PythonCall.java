package org.projet.api;

import org.projet.api.constantes.Chemins;


/**
 * 
 * Classe qui gère l'appelle au script python du modele de classification d'image
 *
 */
public class PythonCall {
	public final static String DOSSIER_MODELE = "C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Modele_IA\\";
	Process mProcess;

	public void runScript(String path, String pathResult) {
		Process process;
		try {
			//on construit le Process pour executer le script python
			ProcessBuilder pb = new ProcessBuilder("python", 
					Chemins.DOSSIER_MODELE + "classifieur.py", 
					path, 
					Chemins.DOSSIER_MODELE + "poids_modele\\poids",
					pathResult);
			process = pb.start();
			mProcess = process;
		} catch (Exception e) {
			System.out.println("Exception Raised" + e.toString());
		}
		
		// Permet d'afficher la sortie terminal du script dans la console
		
//		InputStream stdout = mProcess.getInputStream();
//		InputStream stsin = mProcess.getErrorStream();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
//		BufferedReader readerError = new BufferedReader(new InputStreamReader(stsin, StandardCharsets.UTF_8));
//		String line;
//		try {
//			while (((line = reader.readLine()) != null) || ((line = readerError.readLine()) != null)) {
//				System.out.println("stdout: " + line);
//			}
//		} catch (IOException e) {
//			System.out.println("Exception in reading output" + e.toString());
//		}
	}

}
