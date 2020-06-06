package org.projet.api.bdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.projet.api.Image;
import org.projet.api.PythonCall;
import org.projet.api.User;
import org.projet.api.constantes.Chemins;

public class DataBaseService {

	public User getUser() {
		return BDD.getInstance().getMapUser().get(1);
	}

	/**
	 * Vérifie si un compte est présent avec le mot de passe et l'email renseigné.
	 * 
	 * @param email    de connection
	 * @param password de connection
	 * @return user si présent en base null sinom
	 */
	public User connection(final String email, final String password) {
		User u = null;
		Configuration config = new Configuration();
		config.configure();
		config.addClass(User.class);
		try {
			SessionFactory sf = config.buildSessionFactory();
			Session session = sf.openSession();
			TypedQuery<User> query = session
					.createQuery("Select u from User u where u.email = :email and u.password = :password", User.class)
					.setParameter("email", email).setParameter("password", password);
			u = query.getSingleResult();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (NoResultException e) {
			u = null;
		}

		return u;
	}

	/**
	 * Creation du compte en base de donnée. Il ne doit pas y avoir de compte avec
	 * l'adresse email en base pour creer le compte !
	 * 
	 * @param name     prénom de la personne
	 * @param surname  nom de la personne
	 * @param email    de la personne
	 * @param password de la personne
	 * @return vrai si le compte a etait creer faux sinom
	 */
	public Boolean registration(final String name, final String surname, final String email, final String password, final String pseudo) {
		if (name != null && surname != null && password != null && email != null) {
			Configuration config = new Configuration();
			config.configure();
			config.addClass(User.class);
			User u = null;
			try {
				SessionFactory sf = config.buildSessionFactory();
				Session session = sf.openSession();
				TypedQuery<User> query = session.createQuery("Select u from User u where u.email = :email", User.class)
						.setParameter("email", email);
				try {
					u = query.getSingleResult();
				} catch (NoResultException e) {

				}
				
				query = session.createQuery("Select u from User u where u.pseudo = :pseudo", User.class)
						.setParameter("pseudo", pseudo);
				try {
					u = query.getSingleResult();
				} catch (NoResultException e) {

				}
				if (u == null) {
					User n = new User(name, surname, 0, email, password, pseudo);
					Transaction tx = session.beginTransaction();
					session.persist(n);
					tx.commit();
					session.close();
				} else {
					return false;
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/**
	 * Retourne le nombre d'objets 0,1, 2 analysés au total
	 */
	public String getStatsImages() {
		Configuration config = new Configuration();
		config.configure();
		try {
			SessionFactory sf = config.buildSessionFactory();
			Session session = sf.openSession();

			Query query = session.createQuery("Select Count(*) from Image");
			Long nb_image = (Long) query.uniqueResult();
			Long nb_class[] = new Long[3];
			for (int i = 0; i < 3; i++) {
				Query q = session.createQuery("Select Count(*) from Image i where i.classe=:i").setParameter("i",
						String.valueOf(i));
				nb_class[i] = (Long) q.uniqueResult();
			}
			session.close();
			JSONObject result = new JSONObject();
			result.put("nb_image_analyser", String.valueOf(nb_image));
			JSONObject r = new JSONObject();
			for (int i = 2; i >= 0; i--) {
				r.put("nb_" + String.valueOf(i), String.valueOf(nb_class[i]));
			}
			result.put("class", r);
			return result.toString();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * Récupérer le classement trié par ordre décroissant du score de chaque
	 * utilisateur
	 * 
	 * @return String (formaté en JSON) du classement
	 */
	public String getClassement() {
		Configuration config = new Configuration();
		config.configure();
		config.addClass(User.class);
		try {
			SessionFactory sf = config.buildSessionFactory();
			Session session = sf.openSession();

			Query query = session.createQuery("Select u.pseudo, Count(i), u.score from User u, Image i where i.idUser = u.id "
					+ "GROUP BY u.pseudo "
					+ "Order by u.score DESC");
			JSONObject result = new JSONObject();
			List results = query.list();
			session.close();
			results.forEach((lr)->{
				JSONObject json = new JSONObject();
				Object[] r = (Object[])lr;
				json.put("pseudo", (String)r[0]);
				json.put("nb_image_analysee", String.valueOf((Long)r[1]));
				json.put("score", String.valueOf((int)r[2]));
				//String pseudo = (String)r[0];
				//Long count = (Long)r[1];
				//int score = (int)r[2];
				result.append("users", json);
				//System.out.println("Email: "+ email +"\tCount: " + count + "\tScore: " + score);
			});
			
			return result.toString();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String uploadImage(final String imageName, final String pseudoUploader, final String imagePath) {
		Configuration config = new Configuration();
		config.configure();
		config.addClass(User.class);
		try {
			SessionFactory sf = config.buildSessionFactory();
			Session session = sf.openSession();

			Query query = session.createQuery("Select u.id from User u where u.pseudo = :pseudo").setParameter("pseudo", pseudoUploader);
			int id = (int)query.uniqueResult();
			PythonCall scriptPython = new PythonCall();
			String path = imagePath + imageName;
			scriptPython.runScript(path, Chemins.DOSSIER_MODELE);
			File prediction = new File(Chemins.DOSSIER_MODELE+"prediction.json");
			Scanner myReader;
			String jsonText = "";
			try {
				myReader = new Scanner(prediction);
				while(myReader.hasNextLine()) {
					jsonText = myReader.nextLine();
				}
				myReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			prediction.delete();
			JSONObject js = new JSONObject(jsonText);
			String classe = js.getString("prediction");
			JSONObject prediction_prob = js.getJSONObject("prediction_propabilite");
			Image imageBDD = new Image(id, imagePath, imageName, classe);
			Transaction tx = session.beginTransaction();
			session.persist(imageBDD);
			tx.commit();
			session.close();
			js = new JSONObject();
			js.put("class", classe);
			js.put("proba", prediction_prob);
			return js.toString();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
