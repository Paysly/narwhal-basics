package com.narwhal.basics.core.rest.utils;

import com.narwhal.basics.core.rest.model.paging.Cursor;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Tomas de Priede
 */
public class ObjectSerializationUtilsTest {

    @Test
    public void test_serialization() throws IOException, ClassNotFoundException {
        Cursor cursor = new Cursor();
        cursor.setCursor("asd");
        cursor.setIndex(3);
        byte[] bytes = ObjectSerializationUtils.serialize(cursor);
        Assert.assertEquals(cursor, ObjectSerializationUtils.deserialize(bytes));
    }
}
