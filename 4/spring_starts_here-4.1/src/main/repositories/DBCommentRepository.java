package main.repositories;

import main.models.Comment;

public class DBCommentRepository implements CommentRepository {
    @Override
    public void storeComment(Comment comment) {
        System.out.println("Storing comment: '" + comment.getText() + "' from " + comment.getAuthor() + ".");
    }
}
