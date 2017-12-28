import com.work.assignments.list.CustomArrayList;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


@Test
public class CustomArrayListTest {

    private void populateList(List<Integer> list) {
        list.add(6);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(0, 1);
        list.add(3, 33);
        list.add(6, 66);
    }

    @Test
    public void testAdd() {
        List list = getNewList();
        Assert.assertEquals(list.size(), 0);
        populateList(list);
        //System.out.println(list.get(1));
        Assert.assertEquals(new Integer(1), list.get(0));
        Assert.assertEquals(new Integer(6), list.get(1));
        Assert.assertEquals(new Integer(4), list.get(5));
        Assert.assertEquals(new Integer(33), list.get(3));
        Assert.assertEquals(new Integer(66), list.get(6));
        Assert.assertEquals(7, list.size());
    }

    @Test
    public void testSet() {
        List list = getNewList();
        populateList(list);
        list.set(0, 1);
        Assert.assertEquals(new Integer(1), list.get(0));
    }

    @Test
    public void testContains() {
        List list = getNewList();
        populateList(list);
        Assert.assertEquals(true, list.contains(66));
        Assert.assertEquals(false, list.contains(155));

    }

    @Test
    public void testRemove() {
        List list = getNewList();
        populateList(list);
        Integer i = 33;
        list.remove(6);
        list.remove(i);
        Assert.assertEquals(false, list.contains(66));
        Assert.assertEquals(false, list.contains(33));

    }

    @Test
    public void testClear() {
        List list = getNewList();
        populateList(list);
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testSubList() {
        List list = getNewList();
        populateList(list);
        List<Integer> subList = list.subList(0, 4);
        try {
            List<Integer> subList2 = list.subList(0, 25);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e + ": Array Index is out of Bounds!");
        }
        Assert.assertEquals(4, subList.size());

    }

    @Test
    public void testIterator() {
        List list = getNewList();
        populateList(list);
        Iterator<Integer> iterator = list.iterator();
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(new Integer(1), iterator.next());
        try {
            while (true)
                iterator.next();
        } catch (NoSuchElementException e) {
            System.err.println(e);
        }
    }

    @Test
    public void testAddAll() {
        List list = getNewList();
        populateList(list);

        //Testing with a populated Array List.
        List<Integer> integerList = new ArrayList<Integer>(5);
        populateList(integerList);
        list.addAll(integerList);
        Assert.assertEquals(14, list.size());
        Assert.assertEquals(new Integer(66), list.get(13));
        integerList.clear();

        //Sending an empty Collection, should return false because list has not changed.
        Assert.assertEquals(false, list.addAll(integerList));

        //Add all from a given index.
        populateList(list);
        populateList(integerList);
        list.addAll(3,integerList);
        Assert.assertEquals(14, list.size());


    }


    private List getNewList() {
        return new CustomArrayList();
    }

}

