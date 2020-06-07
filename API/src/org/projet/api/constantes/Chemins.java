package org.projet.api.constantes;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class Chemins {
	
	private static Chemins chemins = null;
	private String DOSSIER_MODELE;
	private String DOSSIER_IMAGE;
	private String PATH_PYTHON;
	private Chemins() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("config.json");
		
		String jsonTxt = "";
		try {
			jsonTxt = IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(jsonTxt);
		DOSSIER_IMAGE = json.getString("path_images");
		DOSSIER_MODELE = json.getString("path_model");
		PATH_PYTHON = json.getString("path_python");
	}
	
	public static Chemins getInstance() {
		if(chemins == null) {
			chemins = new Chemins();
		}
		return chemins;
	}

	public String getDOSSIER_MODELE() {
		return DOSSIER_MODELE;
	}

	public String getDOSSIER_IMAGE() {
		return DOSSIER_IMAGE;
	}
	
	
	public String getPATH_PYTHON() {
		return PATH_PYTHON;
	}
}
 