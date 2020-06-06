
package org.projet.api.bdd;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.projet.api.User;

public class BDD {

	private Map<Integer, User> bdd;

	private BDD() {
		
		bdd = new HashMap<Integer, User>();
		
		bdd.put(1, new User("Stephane", "COCQUEBERT", 458, "a@a.com", "CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB", "pseudo"));

	}

	private static BDD INSTANCE = new BDD();

	public static BDD getInstance() {
		return INSTANCE;
	}

	public Map<Integer, User> getMapUser() {
		return bdd;
	}

}
