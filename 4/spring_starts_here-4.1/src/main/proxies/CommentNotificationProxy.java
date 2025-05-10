package main.proxies;

import main.models.Comment;

public interface CommentNotificationProxy {
    void sendComment(Comment comment);
}
