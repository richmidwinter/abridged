package uk.co.wansdykehouse.abridged.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ResteasyApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>(2);
	
	public ResteasyApplication() {
		singletons.add(new Mappings());
		singletons.add(new Redirect());
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
