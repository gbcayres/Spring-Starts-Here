package org.example.proxies;

import org.example.models.Comment;
import org.springframework.stereotype.Component;

@Component("EMAIL")
public class EmailCommentNotificationProxy implements CommentNotificationProxy {
    @Override
    public void sendComment(Comment comment) {
        System.out.println("Notifying " + comment.getAuthor() + " by e-mail.");
    }
}
