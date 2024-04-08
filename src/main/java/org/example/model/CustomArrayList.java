package org.example.model;

import java.util.Arrays;
import java.util.Collection;

public class CustomArrayList<E> implements CustomList<E> {

    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity should be greater than or equal to zero: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public CustomArrayList(Collection<E> collection) {
        this(collection.size() + INITIAL_CAPACITY);
        addAll(collection);
    }

    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        this.elements[size++] = element;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        ensureCapacity(size + 1);

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (E) elements[index];
    }

    @Override
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return true;
    }

    @Override
    public boolean remove(E element) {
        int index = indexOf(element);

        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public void addAll(Collection<E> collection) {
        if (collection.isEmpty()) {
            return;
        }
        ensureCapacity(size + collection.size());

        for (E element : collection) {
            elements[size++] = element;
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length + (elements.length >> 1), minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }
}
