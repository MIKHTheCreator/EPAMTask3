package com.epam.jwd.repository.entity;

import java.util.Objects;

public class Operator extends Person<String>{

    private int age;
    private int workPosition;

    public Operator(String id, String name, int age, int workPosition) {
        super(id, name);
        this.age = age;
        this.workPosition = workPosition;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(int workPosition) {
        this.workPosition = workPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return age == operator.age
                && workPosition == operator.workPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, workPosition);
    }

    //ToDo replace with StringBuilder
    @Override
    public String toString() {
        return "Operator{" +
                "name=" + this.getName() +
                ", age=" + age +
                ", workPosition=" + workPosition +
                '}';
    }
}
