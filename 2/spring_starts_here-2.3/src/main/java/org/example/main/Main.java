package org.example.main;

import org.example.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Parrot miki = context.getBean(Parrot.class);
        Parrot koko = context.getBean("parrot1", Parrot.class);
        Parrot riki = context.getBean("riki", Parrot.class);

        System.out.println(miki.getName());
        System.out.println(koko.getName());
        System.out.println(riki.getName());
    }
}