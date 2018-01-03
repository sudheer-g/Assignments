package com.work.assignments.list;

import java.util.Objects;

public class Person{
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
}
