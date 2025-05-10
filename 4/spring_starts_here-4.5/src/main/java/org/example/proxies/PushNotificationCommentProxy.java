package org.example.proxies;

import org.example.models.Comment;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PushNotificationCommentProxy implements CommentNotificationProxy {

    @Override
    public void sendComment(Comment comment) {
        System.out.println("Notifying " + comment.getAuthor() + " by push notification.");
    }
}
