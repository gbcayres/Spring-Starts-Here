package org.example;

import org.example.config.ProjectConfiguration;
import org.example.models.Comment;
import org.example.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var service = context.getBean(CommentService.class);

        var comment = new Comment("Hidra Thiago", "Ahn, Oruam.");
        var value = service.publishComment(comment);

        System.out.println("Returned value: " + value);
    }
}