package uk.co.wansdykehouse.abridged;

import junit.framework.TestCase;

import org.junit.Test;

public class HashTest extends TestCase {

	@Test
	public void testGet() {
		assertFalse(Hash.get().equals(Hash.get()));
	}
}
