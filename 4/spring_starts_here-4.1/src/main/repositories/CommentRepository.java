package main.repositories;

import main.models.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
