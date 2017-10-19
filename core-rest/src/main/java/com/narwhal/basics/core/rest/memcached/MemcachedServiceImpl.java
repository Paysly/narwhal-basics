package com.narwhal.basics.core.rest.memcached;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;

/**
 * MemcacheService implementation
 *
 * @author Tomas de Priede
 */
@Singleton
public class MemcachedServiceImpl implements MemcachedService {

    @Inject
    public MemcachedServiceImpl() {
        super();
    }

    @Override
    public void put(String key, Serializable cacheableObject) {
        put(key, cacheableObject, null);
    }

    @Override
    public void put(String key, Serializable cacheableObject, Expiration expiration) {
        Preconditions.checkNotNull(key);
        String cacheKey = key;
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        if (expiration != null) {
            syncCache.put(cacheKey, cacheableObject, expiration);
        } else {
            syncCache.put(cacheKey, cacheableObject);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Serializable get(String key) {
        Preconditions.checkNotNull(key);
        String cacheKey = key;
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        Serializable cachableObject = (Serializable) (syncCache.get(cacheKey));
        return cachableObject;
    }

    @Override
    public void putFilteringByNamespace(String namespace, String key, Serializable cacheableObject) {
        putFilteringByNamespace(namespace, key, cacheableObject, null);
    }

    @Override
    public void putFilteringByNamespace(String namespace, String key,
                                        Serializable cacheableObject, Expiration expiration) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(cacheableObject);

        String cacheKey = key;
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService(namespace);
        if (expiration != null) {
            syncCache.put(cacheKey, cacheableObject, expiration);
        } else {
            syncCache.put(cacheKey, cacheableObject);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Serializable getFilteringByNamespace(String namespace, String key) {
        Preconditions.checkNotNull(key);
        String cacheKey = key;
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService(namespace);
        Serializable cachableObject = (Serializable) (syncCache.get(cacheKey));
        return cachableObject;
    }

    @Override
    public void delete(String key) {
        delete(null, key);
    }

    @Override
    public void delete(String namespace, String key) {
        Preconditions.checkNotNull(key);

        String cacheKey = key;
        MemcacheService syncCache;
        if (namespace != null) {
            syncCache = MemcacheServiceFactory.getMemcacheService(namespace);
        } else {
            syncCache = MemcacheServiceFactory.getMemcacheService();
        }
        syncCache.delete(cacheKey);
    }

}