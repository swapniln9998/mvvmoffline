package com.swapnil.mvvm_offline.domain;

import com.swapnil.mvvm_offline.model.Comment;

import io.reactivex.Completable;

public interface RemoteCommentRepository {
    Completable sync(Comment comment);
}
