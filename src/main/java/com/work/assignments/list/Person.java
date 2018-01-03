package com.work.assignments.list;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable{
    public Person()
    {
        firstName = "Sudheer";
        lastName = "Gotety";
    }
    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof Person)){
            return false;
        }

        Person object = (Person) obj;
        return Objects.equals(this.firstName,object.firstName) && Objects.equals(this.lastName,object.lastName);
    }

    @Override
    public int hashCode() {
        return this.firstName.hashCode() + this.lastName.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Person))
            return -1;

        if(this.equals(o))
            return 0;

        Person object = (Person) o;

        if(this.firstName.compareTo(object.firstName) > 0){
            return 1;
        }
        else if(this.firstName.compareTo(object.firstName) < 0) {
            return -1;
        }

        if(this.firstName.compareTo(object.firstName) == 0){
            if(this.lastName.compareTo(object.lastName) > 0) {
                return 1;
            }

            else {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.firstName + this.lastName;
    }
}
