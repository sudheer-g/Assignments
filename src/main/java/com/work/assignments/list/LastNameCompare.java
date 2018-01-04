package com.work.assignments.list;

import java.util.Comparator;

public class LastNameCompare implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
