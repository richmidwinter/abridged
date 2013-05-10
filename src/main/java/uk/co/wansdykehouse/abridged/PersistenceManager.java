package uk.co.wansdykehouse.abridged;

import redis.clients.jedis.Jedis;

public class PersistenceManager {
	
	private static PersistenceManager _instance = new PersistenceManager();

    private Jedis jedis;

	private PersistenceManager() {
        jedis = new Jedis("localhost");
        jedis.connect();
	}
	
	public static PersistenceManager get() {
		return _instance;
	}
	
	public Mapping find(final String link) {
        final Mapping mapping = new Mapping();
        mapping.setUrl(link);
        mapping.setHash(jedis.get("abridged:url:" +link));
        return mapping.getHash() == null ? null : mapping;
	}
	
	public Mapping lookup(final String hash) {
        final Mapping mapping = new Mapping();
        mapping.setHash(hash);
        mapping.setUrl(jedis.get("abridged:hash:" +hash));
        return mapping.getUrl() == null ? null : mapping;
	}
	
	public boolean exists(final String hash) {
        return jedis.exists("abridged:hash:" +hash);
	}
	
	public void persist(final Mapping mapping) {
        jedis.set("abridged:url:" +mapping.getUrl(), mapping.getHash());
        jedis.set("abridged:hash:" +mapping.getHash(), mapping.getUrl());
	}
}
