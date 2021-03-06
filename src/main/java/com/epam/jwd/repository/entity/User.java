package com.epam.jwd.repository.entity;

import java.util.Objects;

public class User extends Person<String> {

    private int age;
    private Gender gender;
    private String visitAim;
    private boolean recall;

    private static final String CALLING_MESSAGE = "%s(age:%d, visit aim:%s) is calling again, I guess you don't forget about me...\n\n";
    private static final String END_CALL_MESSAGE = "End calling, the queue is too big for me, I'll better go knitting(Maybe I'll recall later)...\n";

    public User(String id, String name, int age, Gender gender, String visitAim, boolean recall) {
        super(id, name);
        this.age = age;
        this.gender = gender;
        this.visitAim = visitAim;
        this.recall = recall;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getVisitAim() {
        return visitAim;
    }

    public void setVisitAim(String visitAim) {
        this.visitAim = visitAim;
    }

    public boolean isRecall() {
        return recall;
    }

    public void setRecall(boolean recall) {
        this.recall = recall;
    }

    public void recall() {
        System.out.printf(CALLING_MESSAGE, getPersonName(), getAge(), getVisitAim());
    }

    public void endCall() {
        System.out.println(END_CALL_MESSAGE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return age == user.age
                && recall == user.recall
                && gender == user.gender
                && visitAim.equals(user.visitAim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age, gender, visitAim, recall);
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + this.getPersonName() +
                ", age=" + age +
                ", gender=" + gender +
                ", visitAim='" + visitAim + '\'' +
                '}';
    }
}
