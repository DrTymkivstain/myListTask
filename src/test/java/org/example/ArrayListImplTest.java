package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListImplTest {

    @Test
    public void addToList() throws IllegalAccessException {
        MyList<Integer> arraylist = new ArrayListImpl<>();
        arraylist.add(5);
        arraylist.add(10);
        arraylist.add(15);

        Field arrayField = Arrays.stream(ArrayList.class.getDeclaredFields())
                .filter(field -> field.getType().isArray())
                .findAny()
                .orElseThrow();
        arrayField.setAccessible(true);
        Object[] internalArray = (Object[]) arrayField.get(arraylist);

        Assert.assertEquals(5, internalArray[0]);
        Assert.assertEquals(10, internalArray[0]);
        Assert.assertEquals(15, internalArray[0]);

    }

}
