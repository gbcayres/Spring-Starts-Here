package main;

import main.models.Comment;
import main.proxies.CommentNotificationProxy;
import main.repositories.CommentRepository;

public class CommentService {
    private CommentRepository commentRepository;
    private CommentNotificationProxy commentNotificationProxy;

    public CommentService(CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
    }

    public void publishComment(Comment comment) {
        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);
    }
}
