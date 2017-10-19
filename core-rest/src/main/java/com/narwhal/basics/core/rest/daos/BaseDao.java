package com.narwhal.basics.core.rest.daos;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.cmd.Query;
import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.model.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * Base class for all FlashPanel data access objects (DAO). Provides a common
 * constructor ObjectifyFactory injection point for extending DAOs. Provides a
 * common method to create DAOs with options. Class also provides generic
 * datastore CRUD operations.
 *
 * @param <T> must be a registered type with the passed in objectify factory
 * @author Tomas de Priede
 */
public interface BaseDao<T extends BaseModel> extends PagingDatasource<T> {

    char LAST_UNICODE_CHARACTER = '\ufffd';
    Integer MAX_PAGE_SIZE_ALLOWED = 1000;

    /**
     * save or update entity in datastore entity must be of a type registered
     * with the injected objectify factory
     *
     * @param entity must not be null
     * @return the Key of the saved object
     */
    Key<T> save(T entity);

    /**
     * save or update entities in datastore entities must be of a type
     * registered with the injected objectify factory
     *
     * @param entities
     * @return a map of the saved entities mapped to their datastore keys
     */
    Map<Key<T>, T> saveAll(Iterable<T> entities);

    /**
     * get object of type clazz that is stored in the datastore under the param
     * id clazz must be of a type registered with the injected objectify factory
     *
     * @param clazz the returned value type
     * @param id
     * @return the object of type clazz that matches on the id thrown if no
     * entity object could be found
     */
    T get(Class<T> clazz, String id) throws EntityNotFoundException;

    /**
     * get object of type clazz that is stored in the datastore under the param
     * id clazz must be of a type registered with the injected objectify factory
     *
     * @param clazz the returned value type
     * @param id
     * @return the object of type clazz that matches on the id
     * @throws EntityNotFoundException thrown if no entity object could be found
     * @author Tomas de Priede
     */
    LoadResult<T> getAsync(Class<T> clazz, String id) throws EntityNotFoundException;

    /**
     * get object of type clazz that is stored in the datastore under the param
     * id clazz must be of a type registered with the injected objectify factory
     *
     * @param clazz the returned value type
     * @param id
     * @return the object of type clazz that matches on the id
     * @throws EntityNotFoundException thrown if no entity object could be found
     */
    T get(Class<T> clazz, Long id) throws EntityNotFoundException;

    /**
     * get entities from datastore that match against the passed in collection
     * of ids
     *
     * @param ids the set of String or Long ids matching against those entities
     *            to be retrieved from the datastore
     * @return all entities that match on the collection of ids. no error is
     * thrown for entities not found in datastore.
     */
    Map<String, T> get(Class<T> paramClass, Iterable<String> ids);

    /**
     * get entities from datastore that match against the passed in collection
     * of ids
     *
     * @param ids the set of String or Long ids matching against those entities
     *            to be retrieved from the datastore
     * @return all entities that match on the collection of ids. no error is
     * thrown for entities not found in datastore.
     */
    Map<Long, T> gets(Class<T> paramClass, Iterable<Long> ids);

    /**
     * get entities from datastore that match against the passed in collection
     * of keys
     *
     * @param keys the set of keys matching against those entities to be
     *             retrieved from the datastore
     * @return all entities that match on the collection of keys. no error is
     * thrown for entities not found in datastore.
     */
    Map<Key<T>, T> get(Iterable<Key<T>> keys);

    /**
     * delete object of type clazz that is stored in the datastore under the
     * param id clazz must be of a type registered with the injected objectify
     * factory
     *
     * @param clazz the returned value type
     * @param id
     */
    void delete(Class<T> clazz, String id);

    /**
     * delete object of type clazz that is stored in the datastore under the
     * param id clazz must be of a type registered with the injected objectify
     * factory
     *
     * @param clazz the returned value type
     * @param id
     */
    void delete(Class<T> clazz, Long id);

    /**
     * delete entities from datastore that match against the passed in
     * collection entities must be of a type registered with the injected
     * objectify factory
     *
     * @param entities
     */
    void deleteEntities(Iterable<T> entities);

    /**
     * delete entities from datastore that match against the passed in
     * collection keys must be of a type string with the injected objectify
     * factory
     *
     * @param keys the keys to delete
     */
    void deleteEntitiesByKey(Class<T> clazz, Iterable<String> keys);

    /**
     * Get entities from datastore that match against the passed in collection
     * of ids buts using chunks of a determinated size to avoid to retrieve all
     * the entities in just one query.
     *
     * @param paramClass
     * @param keys       the list of keys of all the entities to be retrieved
     * @param chunkSize  the max number of elements to be retrieved per query
     * @return
     * @author Tomas de Priede
     * @since Jan-24-2013
     */
    Map<Long, T> getsInChunks(Class<T> paramClass, List<Long> keys, int chunkSize);

    /**
     * Returns count of entities of T
     *
     * @param clazz
     * @return
     */
    Integer getCount(Class<T> clazz);

    /**
     * Returns count of entities of T using a filter
     *
     * @param clazz
     * @return
     */
    Integer getCount(Class<T> clazz, Query<T> loadType);
}
