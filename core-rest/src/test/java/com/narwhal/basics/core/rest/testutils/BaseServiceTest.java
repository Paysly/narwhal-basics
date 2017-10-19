package com.narwhal.basics.core.rest.testutils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.fail;

/**
 * @author Tomas de Priede
 */
public abstract class BaseServiceTest<H extends Object> {

    private Class<H> clazz;

    protected BaseServiceTest(Class<H> clazz) {
        this.clazz = clazz;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_singleton() {
        if (clazz.getAnnotation(com.google.inject.Singleton.class) == null && clazz.getAnnotation(javax.inject.Singleton.class) == null) {
            fail(clazz + " is not singleton. Please annotate with @Singleton");
        }
    }
}