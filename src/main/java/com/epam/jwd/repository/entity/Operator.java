package com.epam.jwd.repository.entity;

import java.util.Objects;

public class Operator extends Person<String>{

    private int age;
    private int workPosition;
    private boolean isAvailable;

    public Operator(String id, String name, int age, int workPosition) {
        super(id, name);
        this.age = age;
        this.workPosition = workPosition;
        this.isAvailable = true;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Operator operator = (Operator) o;
        return age == operator.age
                && workPosition == operator.workPosition
                && isAvailable == operator.isAvailable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, workPosition, isAvailable);
    }

    //ToDo replace with StringBuilder
    @Override
    public String toString() {
        return "Operator{" +
                "name=" + this.getName() +
                ", available=" + isAvailable +
                ", age=" + age +
                ", workPosition=" + workPosition +
                '}';
    }
}
