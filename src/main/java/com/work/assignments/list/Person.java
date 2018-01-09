package com.work.assignments.list;

import java.util.Objects;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;

    public Person() {
        firstName = "Sudheer";
        lastName = "Gotety";
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Person)) {
            return false;
        }

        Person object = (Person) obj;
        return Objects.equals(this.firstName, object.firstName) && Objects.equals(this.lastName, object.lastName);
    }

    @Override
    public int hashCode() {
        return this.firstName.hashCode() + this.lastName.hashCode();
    }

    @Override
    public int compareTo(Person otherPerson) {
        int c = this.firstName.compareTo(otherPerson.firstName);
        if (c == 0) {
            c = this.lastName.compareTo(otherPerson.lastName);
        }
        return c;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
