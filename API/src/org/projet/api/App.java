package org.projet.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class App extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(CORSFilter.class);
		s.add(UserService.class);
		s.add(ImageService.class);
		return s;
	}
	
}
