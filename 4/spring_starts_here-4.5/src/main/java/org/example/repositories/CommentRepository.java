package org.example.repositories;

import org.example.models.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
