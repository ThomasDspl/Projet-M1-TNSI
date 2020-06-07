package org.projet.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.projet.api.bdd.DataBaseService;
import org.projet.api.constantes.Chemins;

@Path("/upload")
public class ImageService {

	DataBaseService dataBaseService = new DataBaseService();
	Chemins c = Chemins.getInstance();

	/**
	 * Permet de classifier et de mettre en base une image
	 * 
	 * @param input JSON contenant le pseudo et l'image en String Base64
	 */
	@POST
	@Path("/send")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postImage(final String input) {
		System.out.println("CHEMINS: \n"+c.getDOSSIER_IMAGE() + "\n"+ c.getDOSSIER_MODELE());
		try {
			String name = "";
			//récupération du Json
			JSONObject js = new JSONObject(input);
			//récupération des champs
			String dataImage = js.getString("image");
			String pseudoUploader = js.getString("pseudo");
			//On split la chaine image car est de format data....,DONNEE_VOULUT avec l'appelle web
			String data = dataImage.split(",")[1];
			
			//génération d'un nom unique pour l'image de type UUID(sans tiret) + pseudo
			StringBuilder sb = new StringBuilder();
			String uuid = UUID.randomUUID().toString();
			for(String s: uuid.split("-")) {
				sb.append(s);
			}
			sb.append(pseudoUploader);
			name = sb.toString() + ".png";
			//Déchhiffrage de l'image: on récupère un tableau d'octet
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(data);
			//Crréation du fichier image
			String file = c.getDOSSIER_IMAGE() + name;
			File outputfile = new File(file);
			//On écrit dans le fichier les octets de l'images
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
			outputStream.write(imageBytes);
			outputStream.close();
			//on appelle uploadImage pour avoir sa classe et la mettre en base
			String result = dataBaseService.uploadImage(name, pseudoUploader, c.getDOSSIER_IMAGE());
			if(result != "") {
				return Response.ok().entity(result).build(); 
			}else {
				return Response.serverError().build();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	
	}

}
