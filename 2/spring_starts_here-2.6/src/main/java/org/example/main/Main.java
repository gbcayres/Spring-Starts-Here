package org.example.main;

import org.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot x = new Parrot();
        x.setName("Bibi");

        Supplier<Parrot> parrotSupplier = () -> x;

        context.registerBean("bibi",
                Parrot.class,
                parrotSupplier,
                bc -> bc.setPrimary(true));

        Parrot p = context.getBean("bibi", Parrot.class);
        System.out.println(p.getName());
    }
}