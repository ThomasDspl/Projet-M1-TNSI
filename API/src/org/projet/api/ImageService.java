package org.projet.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.projet.api.bdd.DataBaseService;

@Path("/upload")
public class ImageService {

	DataBaseService dataBaseService = new DataBaseService();

	private String name = "test3.png";
	final static String DOSSIER_IMAGE = "C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\";

	/**
	 * poster une image dans la base de donnée
	 * 
	 * @param input l'image en string
	 * @return response
	 */
	@POST
	@Path("/send")
	public Response postImage(final String input) {
		try {
			JSONObject js = new JSONObject(input);
			String dataImage = js.getString("image");
			String pseudoUploader = js.getString("pseudo");
			String data = dataImage.split(",")[1];
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(data);
			String file = DOSSIER_IMAGE + name;
			File outputfile = new File(file);
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
			outputStream.write(imageBytes);
			outputStream.close();
			String result = dataBaseService.uploadImage(name, pseudoUploader, DOSSIER_IMAGE);
			if(result != "") {
				return Response.ok().entity(result).build(); 
			}else {
				return Response.ok().build();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}

}
