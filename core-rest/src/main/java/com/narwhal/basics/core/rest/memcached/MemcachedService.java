package com.narwhal.basics.core.rest.memcached;

import com.google.appengine.api.memcache.Expiration;
import com.google.inject.ImplementedBy;

import java.io.Serializable;

/**
 * Memcache Manager
 *
 * @author Tomas de Priede
 */
@ImplementedBy(MemcachedServiceImpl.class)
public interface MemcachedService {

    /**
     * Updates the memcache
     *
     * @param key
     * @author Tomas de Priede
     */
    void put(String key, Serializable cacheableObject);

    /**
     * Updates the memcache
     *
     * @param key
     * @author Tomas de Priede
     */
    void put(String key, Serializable cacheableObject, Expiration expiration);

    /**
     * Retrieves a list of objects from the memcache
     *
     * @param key
     * @return list of T
     * @author Tomas de Priede
     */
    Serializable get(String key);

    /**
     * Updates the memcache filtering by namespace
     *
     * @param namespace
     * @param key
     * @param cacheableObject
     * @author Tomas de Priede
     */
    void putFilteringByNamespace(String namespace, String key, Serializable cacheableObject);

    /**
     * Updates the memcache filtering by namespace
     *
     * @param namespace
     * @param key
     * @param cacheableObject
     * @param expiration
     * @author Tomas de Priede
     */
    void putFilteringByNamespace(String namespace, String key, Serializable cacheableObject, Expiration expiration);

    /**
     * Retrieves a list of objects from the memcache filtering by namespace
     *
     * @param namespace
     * @param key
     * @return
     * @author Tomas de Priede
     */
    Serializable getFilteringByNamespace(String namespace, String key);

    /**
     * Deletes the entries under a specific key
     *
     * @param key
     * @author Tomas de Priede
     */
    public void delete(String key);

    /**
     * Deletes the entries under a specific key and namespace
     *
     * @param namespace
     * @param key
     * @author Tomas de Priede
     */
    public void delete(String namespace, String key);


}