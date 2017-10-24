package com.narwhal.basics.core.exceptions.dao;

import com.narwhal.basics.core.exceptions.guice.StoredExceptionModule;
import com.narwhal.basics.core.exceptions.model.StoredException;
import com.narwhal.basics.core.rest.exceptions.api.NotFoundException;
import com.narwhal.basics.core.rest.guice.SubModule;
import com.narwhal.basics.core.rest.model.paging.PagingResult;
import com.narwhal.basics.core.rest.testutils.BaseDaoTest;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StoredExceptionDaoObjectifyImplTest extends BaseDaoTest<StoredException, StoredExceptionDao> {

    public StoredExceptionDaoObjectifyImplTest() {
        super(StoredException.class, StoredExceptionDao.class);
    }

    @Override
    protected SubModule getGuiceSubModule() {
        return new StoredExceptionModule();
    }

    @Override
    protected StoredException newInstance() {
        NotFoundException notFoundException = new NotFoundException("message");
        StoredException storedException = new StoredException();
        storedException.init(notFoundException);
        //
        return storedException;
    }

    @Test
    public void test_getStoredExceptions() {
        PagingResult<StoredException> list1 = dao.getStoredExceptions(null);
        assertEquals(0, list1.getResultList().size());
        //
        PagingResult<StoredException> list = dao.getStoredExceptions("NotFoundException");
        assertEquals(0, list.getResultList().size());
        //
        dao.save(newInstance());
        //
        list = dao.getStoredExceptions("NotFoundException");
        assertEquals(1, list.getResultList().size());
        assertEquals(NotFoundException.class.getName(), list.getResultList().get(0).getExceptionName());
        //
        list = dao.getStoredExceptions("com.narwhal.basics.core.rest.exceptions.api");
        assertEquals(1, list.getResultList().size());
        assertEquals(NotFoundException.class.getName(), list.getResultList().get(0).getExceptionName());
        //
        list = dao.getStoredExceptions("notfoundexception");
        assertEquals(1, list.getResultList().size());
        assertEquals(NotFoundException.class.getName(), list.getResultList().get(0).getExceptionName());
        //
        list = dao.getStoredExceptions("notfound");
        assertEquals(1, list.getResultList().size());
        assertEquals(NotFoundException.class.getName(), list.getResultList().get(0).getExceptionName());
        //
        list = dao.getStoredExceptions("hola");
        assertEquals(0, list.getResultList().size());
    }
}
