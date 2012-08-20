package uk.co.wansdykehouse.abridged.api;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import uk.co.wansdykehouse.abridged.Mapping;
import uk.co.wansdykehouse.abridged.PersistenceManager;

@Path("/")
public class Redirect {
	
	public PersistenceManager p = PersistenceManager.get();

	@GET
	@Path("/{hash : [a-zA-Z0-9]{7}}")
	public String get(@PathParam("hash") String hash) throws URISyntaxException {
		final Mapping mapping = p.lookup(hash);
				
		throw new WebApplicationException(Response.seeOther(new URI(mapping.getUrl())).build());
	}
}
