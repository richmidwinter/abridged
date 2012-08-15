package uk.co.wansdykehouse.shortn;

import junit.framework.TestCase;

import org.junit.Test;

public class TestMapping extends TestCase {
	
	@Test
    public void testPersist(){
        Mapping mapping = new Mapping();
        mapping.setUrl("http://twitter.com");
        mapping.setHash("xkfwe8");
        
        PersistenceManager p = new PersistenceManager();
        
        p.persist(mapping);
        
        Mapping result = p.find(mapping.getUrl());

        assertEquals(mapping.getUrl(), result.getUrl());
        assertEquals(mapping.getHash(), result.getHash());
	}
}
