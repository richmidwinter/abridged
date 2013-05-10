package uk.co.wansdykehouse.abridged;

import junit.framework.TestCase;

import org.junit.Test;

import uk.co.wansdykehouse.abridged.api.Mappings;

public class TestMapping extends TestCase {
	
	@Test
    public void testPersist() throws Exception {
        final Mapping mapping = new Mapping();
        mapping.setUrl("google.co.uk");
        mapping.setHash(new Mappings().getHash(mapping.getUrl()));
        
        PersistenceManager p = PersistenceManager.get();

        final Mapping result = p.lookup(mapping.getHash());
        
        assertEquals("http://" + mapping.getUrl(), result.getUrl());
        assertEquals(mapping.getHash(), result.getHash());
	}
}
