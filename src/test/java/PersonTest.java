import com.work.assignments.list.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class PersonTest {

    private void populateList(List<Person> list) {
        list.add(new Person("Sudheer", "Bharadwaj"));
        list.add(new Person("Roronoa", "Zoro"));
        list.add(new Person("Aaho", "Chibi"));
        list.add(new Person("Aaho", "Kyojin"));
    }

    @Test
    public void testHashCode(){
        Map<Person, Person> hashMap = new HashMap<Person, Person>();
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person("FirstName", "LastName");
        hashMap.put(person1, person2);
        Assert.assertEquals(person2, hashMap.get(person1));
    }

    @Test
    public void testSort() {
        List<Person> list = new ArrayList<>();
        populateList(list);
        Person person1 = new Person();
        Person person2 = new Person();
        list.add(person1);
        list.add(person2);
        for(Person p :list) {
            System.out.println(p.toString());
        }
        System.out.println("\n");
        Collections.sort(list);
        for(Person p :list) {
            System.out.println(p.toString());
        }
        System.out.println("\n");
        Assert.assertEquals("AahoChibi", list.get(0).toString());
        list.clear();
        populateList(list);
        list.add(person1);
        list.add(person2);
        LastNameCompare lastNameCompare = new LastNameCompare();
        Collections.sort(list, lastNameCompare);
        for(Person p :list) {
            System.out.println(p.toString());
        }

    }
}

