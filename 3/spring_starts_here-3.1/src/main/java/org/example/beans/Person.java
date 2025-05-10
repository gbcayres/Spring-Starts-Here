package org.example.beans;

import org.example.beans.Parrot;
import org.springframework.beans.factory.annotation.Autowired;

public class Person {
    private String name = "Ella";

    private Parrot parrot;

    public Person() {
        System.out.println("Person created.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }

    public void setParrot(Parrot parrot) {
        this.parrot = parrot;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", parrot=" + parrot +
                '}';
    }
}
