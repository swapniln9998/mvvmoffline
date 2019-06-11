package com.swapnil.mvvm_offline.model;

public class CommentUtils {
    public static Comment clone(Comment from, boolean syncPending) {
        return new Comment(from.getId(), from.getPhotoId(), from.getCommentText(),
                from.getTimestamp(), syncPending);
    }

    public static Comment clone(Comment from, long id) {
        return new Comment(id, from.getPhotoId(), from.getCommentText(),
                from.getTimestamp(), from.isSyncPending());
    }
}
