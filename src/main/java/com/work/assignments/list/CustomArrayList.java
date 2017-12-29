package com.work.assignments.list;

import java.util.*;

public class CustomArrayList<T> implements List<T> {
    private int capacity;
    private int currentIndex = -1;
    private T[] elementData;

    public CustomArrayList() {
        this(20);
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        elementData = (T[]) new Object[capacity];
    }

    /**
     * resizes the backed up array if the numberOfElements to be added exceeds capacity
     * @return true if resized, otherwise false
     */
    private boolean resizeIfNeeded(int numberOfElements) {
        int newSizeOfArrayList = this.currentIndex + 1 + numberOfElements;
        boolean resized = false;
        while (capacity < newSizeOfArrayList) {
            capacity = capacity*2;
            resized = true;
        }
        if (resized) {
            elementData = Arrays.copyOf(elementData, capacity);
        }
        return resized;
    }

    private int linearSearch(Object e, boolean parseFromBeginning)
    {
        if(parseFromBeginning)
        {
            for (int index = 0; index <= currentIndex; index++) {
                if (Objects.equals(elementData[index], e)) {
                    return index;
                }
            }

        }
        else
        {
            for(int index = currentIndex; index >= 0; index--){
                if (Objects.equals(elementData[index], equals(e))) {
                    return index;
                }
            }
        }
        return -1;
    }

    public int size() {
        return currentIndex + 1;
    }

    public boolean isEmpty() {
        return (currentIndex >= 0);
    }

    public boolean contains(Object o)  {
        return linearSearch(o, true) >= 0;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return (index <= currentIndex);
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return elementData[index++];
                } else {
                    throw new NoSuchElementException("No more Elements left.");
                }
            }

        };
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T t) {
        resizeIfNeeded(1);
        elementData[++currentIndex] = t;
        return true;
    }

    public boolean remove(Object o) {
        for (int index = 0; index <= currentIndex; index++) {
            if (Objects.equals(elementData[index], o)) {
                this.remove(index);
                return true;
            }
        }

        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for(Object e: c){
            if(linearSearch((T)e,true) == -1) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        int numberOfElements = c.size();
        resizeIfNeeded(numberOfElements);
        byte editFlag = 0;
        for (T ele : c) {
            elementData[++currentIndex] = ele;
            editFlag = 1;
        }
        return (editFlag == 1);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        T[] updatedElementData = (T[]) new Object[capacity];
        System.arraycopy((T[]) c.toArray(), 0, elementData, index, c.size());
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear(){
        for (int index = 0; index <= currentIndex; index++) {
            this.remove(index);
        }
        currentIndex = -1;
    }

    public T get(int index) {
        return elementData[index];
    }

    public T set(int index, T element) {
        T previousElement = elementData[index];
        elementData[index] = element;
        return previousElement;
    }

    public void add(int index, T element){
        resizeIfNeeded(1);

        for (int i = currentIndex; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }

        elementData[index] = element;
        currentIndex++;
    }

    public T remove(int index) {
        T removedData = elementData[index];
        if (index == currentIndex) {
            elementData[index] = null;
        } else {

            for (int i = index; i < currentIndex; i++) {
                elementData[i] = elementData[i + 1];
            }
            elementData[currentIndex] = null;
        }
        currentIndex--;
        return removedData;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        List<T> subList = new CustomArrayList<T>();

        for (int index = fromIndex; index < toIndex; index++) {
            subList.add(elementData[index]);
        }
        return subList;
    }
}
