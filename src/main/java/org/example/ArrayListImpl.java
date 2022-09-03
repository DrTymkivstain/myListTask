package org.example;

public class ArrayListImpl<T> implements MyList<T> {
    private int size;
    private T[] elements;

    public ArrayListImpl() {
        elements = (T[]) new Object[10];
    }

    @Override
    public void add(T element) {
        elements[size++] = element;
    }

    @Override
    public T get(int index) {
        return null;
    }
}
