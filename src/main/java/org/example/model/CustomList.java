package org.example.model;

import java.util.ArrayList;
import java.util.Collection;

public interface CustomList<E> {


    void add(E element);
    void add(int idx, E element);
    E get(int idx);

    boolean remove(E element);
    boolean remove(int idx);
    void addAll(Collection<E> collection);
    int indexOf(E element);
    int size();
}
