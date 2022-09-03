package org.example;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class ArrayListImplTest {

    MyList<Integer> arrayList = new ArrayListImpl<>();

    @Test
    public void addToList() throws IllegalAccessException {
        arrayList.add(5);
        arrayList.add(10);
        arrayList.add(15);

        Field arrayField = Arrays.stream(ArrayList.class.getDeclaredFields())
                .filter(field -> field.getType().isArray())
                .findAny()
                .orElseThrow();
        arrayField.setAccessible(true);
        Object[] internalArray = (Object[]) arrayField.get(arrayList);

        Assert.assertEquals(5, internalArray[0]);
        Assert.assertEquals(10, internalArray[0]);
        Assert.assertEquals(15, internalArray[0]);

    }

    @Test
    public void getFromList() throws IllegalAccessException {
        fillTestArray(5,10,15);

        Assert.assertEquals(Optional.ofNullable(arrayList.get(0)), 5);
        Assert.assertEquals(Optional.ofNullable(arrayList.get(1)), 10);
        Assert.assertEquals(Optional.ofNullable(arrayList.get(2)), 15);
    }

    @SneakyThrows
    private void fillTestArray(Object... elements) {
        Field arrayField = arrayList.getClass().getDeclaredField(getTestArrayName());
        Field sizeField = arrayList.getClass().getDeclaredField("size");
        arrayField.setAccessible(true);
        sizeField.setAccessible(true);
        arrayField.set(arrayList, elements);
        sizeField.set(arrayList, elements.length);
    }

    private String getTestArrayName() {
        Field[] fields = arrayList.getClass().getDeclaredFields();
        String name = null;
        for (Field field : fields) {
            if (field.getType().isArray()) {
                field.setAccessible(true);
                name = field.getName();
            }
        }
        return name;
    }

}
