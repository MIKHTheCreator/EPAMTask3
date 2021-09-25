package com.epam.jwd.repository.entity;

import java.util.Objects;

public class Person<T> {

    private T personId;
    private String personName;

    public Person(T id, String name) {
        this.personId = id;
        this.personName = name;
    }

    public T getPersonId() {
        return personId;
    }

    public void setPersonId(T personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String name) {
        this.personName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person<?> person = (Person<?>) o;
        return personId.equals(person.personId)
                && personName.equals(person.personName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, personName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", name='" + personName + '\'' +
                '}';
    }
}
