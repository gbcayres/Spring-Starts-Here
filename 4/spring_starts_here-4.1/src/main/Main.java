package main;

import main.models.Comment;
import main.proxies.EmailCommentNotificationProxy;
import main.repositories.CommentRepository;
import main.repositories.DBCommentRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        var commentRepository = new DBCommentRepository();
        var commentNotificationProxy = new EmailCommentNotificationProxy();

        var commentService = new CommentService(commentRepository, commentNotificationProxy);

        var comment = new Comment("John", "Demo comment.");
        commentService.publishComment(comment);
    }
}