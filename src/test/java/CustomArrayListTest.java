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
        List<Integer> list = getNewList(3);
        Assert.assertEquals(list.size(), 0);
        populateList(list);
        //System.out.println(list.get(1));
        Assert.assertEquals(new Integer(1), list.get(0));
        Assert.assertEquals(new Integer(6), list.get(1));
        Assert.assertEquals(new Integer(4), list.get(5));
        Assert.assertEquals(new Integer(33), list.get(3));
        Assert.assertEquals(new Integer(66), list.get(6));
        Assert.assertEquals(7, list.size());

        list.add(list.size(),111);
        Assert.assertEquals(new Integer(111),list.get(list.size() - 1));

        try
        {
            list.add(255, 4);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("Illegal index entry" + e);
        }

        list.add(null);
        Assert.assertEquals(null, list.get(list.size()));
    }

    @Test
    public void testSet() {
        List<Integer> list = getNewList();
        populateList(list);
        list.set(0, 1);
        Assert.assertEquals(new Integer(1), list.get(0));
    }

    @Test
    public void testContains() {
        List<Integer> list = getNewList();
        populateList(list);
        Assert.assertEquals(true, list.contains(66));
        Assert.assertEquals(false, list.contains(155));

    }

    @Test
    public void testContainsAll() {
        List<Integer> list = getNewList();
        populateList(list);
        List<Integer> integerList = new ArrayList<Integer>(5);
        populateList(integerList);
        Assert.assertEquals(true, list.containsAll(integerList));

        List<String> stringList = new ArrayList<>();
        stringList.add("One");
        stringList.add("Two");
        Assert.assertEquals(false, list.containsAll(stringList));

        list.add(null);
        List listRandom = new ArrayList();
        listRandom.add(1);
        listRandom.add(null);
        listRandom.add("three");

        Assert.assertEquals(false, list.containsAll(listRandom));

    }

    @Test
    public void testRemove() {
        List<Integer> list = getNewList();
        populateList(list);
        Integer i = 33;
        list.remove(6);
        list.remove(i);
        Assert.assertEquals(false, list.contains(66));
        Assert.assertEquals(false, list.contains(33));

    }

    @Test
    public void testClear() {
        List<Integer> list = getNewList();
        populateList(list);
        list.clear();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testSubList() {
        List<Integer> list = getNewList();
        populateList(list);
        List<Integer> subList = list.subList(0, 4);
        try {
            List<Integer> subList2 = list.subList(0, 25);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e + ": Array Index is out of Bounds!");
        }
        Assert.assertEquals(4, subList.size());

    }

    @Test
    public void testIterator() {
        List<Integer> list = getNewList();
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
        List<Integer> list = getNewList();
        populateList(list);

        //Testing with a populated Array List.
        List<Integer> integerList = new ArrayList<Integer>(5);
        populateList(integerList);
        list.addAll(integerList);
        Assert.assertEquals(14, list.size());
        Assert.assertEquals(new Integer(66), list.get(13));

        //Sending an empty Collection, should return false because list has not changed.
        List<Integer> emptyList= getNewList();
        Assert.assertEquals(false, list.addAll(emptyList));
    }

    @Test
    public void testAddAllWithIndex() {
        List<Integer> integerList = new ArrayList<Integer>(3);
        populateList(integerList);
        System.out.println(integerList.toString());

        List<Integer> listTwo = getNewList();
        populateList(listTwo);
        //listTwo.add(100);
        //listTwo.remove(6);
        Assert.assertEquals(true, listTwo.addAll(3,integerList));
        Assert.assertEquals(14, listTwo.size());
        Assert.assertEquals(new Integer(66),listTwo.get(9));
        System.out.println(listTwo);

        List<Integer> listThree = getNewList();
        populateList(listThree);

        Assert.assertEquals(true,listThree.addAll(0,integerList));
        Assert.assertEquals(new Integer(66),listThree.get(6));
        Assert.assertEquals(new Integer(66),listThree.get(13));
        System.out.println(listThree);
    }

    @Test
    public void testResize() {
        List<Integer> list = getNewList(3);
        populateList(list);
        Assert.assertEquals(7, list.size());

        List<Integer> listAddAllTest = getNewList(7);
        populateList(listAddAllTest);
        List<Integer> integerList = new ArrayList<Integer>(5);
        populateList(integerList);
        list.addAll(integerList);
        Assert.assertEquals(14, list.size());

    }

    @Test
    public void testIndexOfAndLastIndexOf()
    {
        List<Integer> list = getNewList();
        populateList(list);
        list.add(1);
        Assert.assertEquals(0,list.indexOf(new Integer(1)));
        Assert.assertEquals(7,list.lastIndexOf(new Integer(1)));
    }

    @Test
    public void testRemoveAll()
    {
        List<Integer> testList = new ArrayList<>();
        populateList(testList);
        List<Integer> list = getNewList();
        populateList(list);
        list.removeAll(testList);
        //System.out.println(list.toString());
        Assert.assertEquals(0,list.size());
        list = getNewList();
        populateList(list);
        list.add(new Integer(111));
        list.add(new Integer(112));
        list.removeAll(testList);
        Assert.assertEquals(2,list.size());
        Assert.assertEquals(new Integer(112),list.get(1));

    }


    private List getNewList() {
        return new CustomArrayList();
    }

    private List getNewList(int capacity) {
        return new CustomArrayList(capacity);
    }


}

