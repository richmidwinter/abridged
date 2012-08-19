package uk.co.wansdykehouse.shortn;

import junit.framework.TestCase;

import org.junit.Test;

import uk.co.wansdykehouse.shortn.api.Mappings;

public class TestMapping extends TestCase {
	
	@Test
    public void testPersist() throws Exception {
        Mapping mapping = new Mapping();
        mapping.setUrl("google.co.uk");
        mapping.setHash(new Mappings().getHash(mapping.getUrl()));
        
        PersistenceManager p = PersistenceManager.get();
        
        Mapping result = p.lookup(mapping.getHash());
        
        assertEquals("http://" + mapping.getUrl(), result.getUrl());
        assertEquals(mapping.getHash(), result.getHash());
	}
}
