package org.example.main;

import org.example.beans.Parrot;
import org.example.beans.Person;
import org.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        var parrot = context.getBean(Parrot.class);
        var person = context.getBean(Person.class);

        System.out.println("Person's name: " + person.getName());
        System.out.println("Parrot's name: " + parrot.getName());

        System.out.println("Person's parrot: " + person.getParrot());
    }
}