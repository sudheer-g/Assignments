package com.work.assignments.list;

import javax.jws.Oneway;
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
     *
     * @return true if resized, otherwise false
     */
    private boolean resizeIfNeeded(int numberOfElements) {
        int newSizeOfArrayList = this.currentIndex + 1 + numberOfElements;
        boolean resized = false;
        while (capacity < newSizeOfArrayList) {
            capacity = capacity * 2;
            resized = true;
        }
        if (resized) {
            elementData = Arrays.copyOf(elementData, capacity);
        }
        return resized;
    }

    private int linearSearch(Object e, boolean traverseFromBeginning) {
        if (traverseFromBeginning) {
            for (int index = 0; index <= currentIndex; index++) {
                if (Objects.equals(elementData[index], e)) {
                    return index;
                }
            }

        } else {
            for (int index = currentIndex; index >= 0; index--) {
                if (Objects.equals(elementData[index], e)) {
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

    public boolean contains(Object o) {
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
        Object[] arr;
        arr = elementData;
        return arr;
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
        for (Object e : c) {
            if (linearSearch((T) e, true) == -1) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        int numberOfElements = c.size();
        resizeIfNeeded(numberOfElements);
        for (T ele : c) {
            elementData[++currentIndex] = ele;
        }
        return c.size() > 0;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        int numberOfElements = c.size();
        resizeIfNeeded(numberOfElements);
        for (int i = currentIndex; i >= index; i--) {
            elementData[i + numberOfElements] = elementData[i];
        }
        currentIndex += numberOfElements;
        int indexToAdd = index;
        for(T ele: c) {
            elementData[indexToAdd++] = ele;
        }
        return c.size() > 0;
    }

    public boolean removeAll(Collection<?> c) {
        for(Object o : c)
        {
            this.remove(o);
        }
        return c.size() > 0;
    }

    public boolean retainAll(Collection<?> c) {
        boolean changed = false;

        for(int index = 0;index <= currentIndex;index++)
        {
            Object o = this.get(index);
            if(!(c.contains(o))){
                this.remove(index);
                changed = true;
            }
        }
        return changed;
    }

    public void clear() {
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

    public void add(int index, T element) {
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
        return linearSearch(o,true);
    }

    public int lastIndexOf(Object o) {
        return linearSearch(o, false);
    }

    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            int index = -1, lastReturnedElement = -1;
            @Override
            public boolean hasNext() {
                return (index < currentIndex);
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return elementData[++index];
                } else {
                    throw new NoSuchElementException("No more Elements left.");
                }
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                if(hasPrevious()){
                    lastReturnedElement = index--;
                    return elementData[lastReturnedElement];
                }
                else {
                    throw new NoSuchElementException("No more Elements left.");
                }
            }

            @Override
            public int nextIndex() {
                if(hasNext()){
                    return index + 1;
                }
                else {
                    return currentIndex + 1;
                }
            }

            @Override
            public int previousIndex() {
                if(hasPrevious()){
                    return index;
                }
                else{
                    return -1;
                }
            }

            @Override
            public void remove() {
                CustomArrayList.this.remove(lastReturnedElement);
            }

            @Override
            public void set(T t) {
                CustomArrayList.this.set(lastReturnedElement, t);

            }

            @Override
            public void add(T t) {
                CustomArrayList.this.add(index++, t);
                lastReturnedElement = -1;
            }
        };
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > currentIndex || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        List<T> subList = new CustomArrayList<T>(toIndex-fromIndex+1);

        for (int index = fromIndex; index < toIndex; index++) {
            subList.add(elementData[index]);
        }
        return subList;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
