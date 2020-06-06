package org.projet.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.projet.api.bdd.DataBaseService;

@Path("/users")
public class UserService {

	private final Utils ultils = new Utils();

	private final DataBaseService dataBaseService = new DataBaseService();

	@GET
	@Path("/{score}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("score") int score) {
		User user = new User("Stephane", "COCQUEBERT", score);
		return user;
	}

	/**
	 * Connection
	 * 
	 * @param params objet JSON contenant email, password
	 * @return Response avec les infos de l'utilisateur
	 */
	@POST
	@Path("/logging")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loging(final String params) {
		JSONObject paramsJSON = new JSONObject(params);
		final String email = paramsJSON.getString("email");
		final String password = ultils.generateHash(paramsJSON.getString("password"));
		Response response = null;
		User user = dataBaseService.connection(email, password);
		if (user != null) {
			response = Response.ok().entity(user).build();
		} else {
			response = Response.status(Response.Status.FORBIDDEN).build();
		}
		return response;
	}

	/**
	 * Inscription
	 * 
	 * @param params objet JSON contenant password,email,surname,name
	 * @return Response indiquant si le compte a été creer ou non.
	 */
	@POST
	@Path("/registration")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registration(final String params) {
		JSONObject paramsJSON = new JSONObject(params);
		final String email = paramsJSON.getString("email");
		final String password = ultils.generateHash(paramsJSON.getString("password"));
		final String name = paramsJSON.getString("name");
		final String surname = paramsJSON.getString("surname");
		final String pseudo = paramsJSON.getString("pseudo");
		Boolean response = dataBaseService.registration(name, surname, email, password, pseudo);
		if (response == true) {
			return Response.ok("{message: \"Your account it been created\"}").build();
		}
		return Response.status(Response.Status.FORBIDDEN).build();
	}

	/**
	 * Renvoie le classement des utilisateurs trié par leur score de manière
	 * décroissante
	 * 
	 * @return Response contenant la réponse JSON des utilisateurs classé par leur
	 *         score de manière décroissante.
	 */
	@GET
	@Path("/classement")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClassement() {
		Response response = null;
		String resultat = dataBaseService.getClassement();
		if (resultat != null) {
			response = Response.ok(resultat).build();
		} else {
			response = Response.serverError().build();
		}
		return response;
	}

	/**
	 * recupère le nombres d'image 0,1,2 analysé dans la base au total
	 * 
	 * @return reponse contenant en JSON le nombre d'objet 0,1,2 analysé au total.
	 */
	@GET
	@Path("/stats")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStats() {
		Response response = null;
		String result = dataBaseService.getStatsImages();
		if (result != null) {
			response = Response.ok(result).build();
		} else {
			response = Response.serverError().build();
		}
		return response;
	}
	
}
