package uk.co.wansdykehouse.shortn.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uk.co.wansdykehouse.shortn.Hash;
import uk.co.wansdykehouse.shortn.Mapping;
import uk.co.wansdykehouse.shortn.PersistenceManager;

@Path("/api/mappings")
public class Mappings {
	
	private static PersistenceManager p = new PersistenceManager();

	@POST
	@Path("/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getHash(final String link) {
		Mapping mapping = p.find(link.trim());
		
		if (mapping == null) {
			mapping = new Mapping();
			mapping.setUrl(link);
			
			String hash = null;
			
			while (hash == null || p.exists(hash)) {
				hash = Hash.get();
			}
			
			mapping.setHash(hash);
			
			p.persist(mapping);
		}
		
		return mapping.getHash();
	}
}
