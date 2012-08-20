package uk.co.wansdykehouse.abridged.api;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uk.co.wansdykehouse.abridged.Hash;
import uk.co.wansdykehouse.abridged.Mapping;
import uk.co.wansdykehouse.abridged.PersistenceManager;

@Path("/api/mappings")
public class Mappings {
	
	private static PersistenceManager p = PersistenceManager.get();

	@POST
	@Path("/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getHash(String link) {
		try {
	        if (new URI(link.trim()).getScheme() == null) {
	        	link = "http://" + link;
	        }
		} catch (URISyntaxException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println("Using link " +link);
		
		Mapping mapping = p.find(link);
		
		if (mapping == null) {
			System.out.println("Mapping not found");
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
