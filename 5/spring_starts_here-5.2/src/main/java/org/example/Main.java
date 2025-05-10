package org.example;

import org.example.config.ProjectConfiguration;
import org.example.services.CommentService;
import org.example.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var s1 = context.getBean(CommentService.class);
        var s2 = context.getBean(UserService.class);

        boolean sameInstance = s1.getCommentRepository() == s2.getCommentRepository();
        System.out.println("same repository instance? " + sameInstance);
        System.out.println("due to spring prototype scope.");
    }
}