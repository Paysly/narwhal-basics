package com.narwhal.basics.core.rest.testutils;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import com.narwhal.basics.core.rest.daos.BaseDao;
import com.narwhal.basics.core.rest.daos.OfyService;
import com.narwhal.basics.core.rest.exceptions.EntityNotFoundException;
import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.model.BaseModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * @author Tomas de Priede
 */
public abstract class BaseDaoTest<T extends BaseModel, D extends BaseDao<T>> {

    protected LocalServiceTestHelper helper;
    protected Injector injector;
    protected D dao;
    protected Class<T> clazz;
    protected Class<D> daoClazz;
    protected Closeable session;

    protected BaseDaoTest(Class<T> clazz, Class<D> daoClazz) {
        this.clazz = clazz;
        this.daoClazz = daoClazz;
    }

    @Before
    public void setUp() {
        for (Class clazz : getGuiceSubModule().objectifyClasses()) {
            OfyService.factory().register(clazz);
        }
        session = ObjectifyService.begin();
        injector = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure() {
            }
        });
        helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
        helper.setUp();
        MockitoAnnotations.initMocks(this);
        dao = injector.getInstance(daoClazz);
    }

    @After
    public void tearDown() {
        helper.tearDown();
        session.close();
    }

    @Test
    public void test_singleton() {
        if (dao.getClass().getAnnotation(Singleton.class) == null && dao.getClass().getAnnotation(javax.inject.Singleton.class) == null) {
            fail(dao.getClass() + " is not singleton. Please annotate with @Singleton");
        }
    }

    @Test
    public void test_create_retrieve_delete() throws EntityNotFoundException {
        T entity = newInstance();
        dao.save(entity);
        //
        T retrieved = dao.get(clazz, entity.getId());
        assertEquals(entity, retrieved);
        //
        dao.delete(clazz, entity.getId());
        //
        try {
            dao.get(clazz, entity.getId());
            fail("It should throw an exception");
        } catch (EntityNotFoundException e) {
        }
    }

    protected abstract SubModule getGuiceSubModule();

    protected abstract T newInstance();
}