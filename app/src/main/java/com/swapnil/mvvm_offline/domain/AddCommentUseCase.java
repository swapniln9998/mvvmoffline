package com.swapnil.mvvm_offline.domain;

import com.swapnil.mvvm_offline.model.ModelConstants;

import io.reactivex.Completable;

public class AddCommentUseCase {
    private final LocalCommentRepository localCommentRepository;
    private final SyncCommentUseCase syncCommentUseCase;

    public AddCommentUseCase(LocalCommentRepository localCommentRepository, SyncCommentUseCase syncCommentUseCase) {
        this.localCommentRepository = localCommentRepository;
        this.syncCommentUseCase = syncCommentUseCase;
    }

    public Completable addComment(String commentText) {
        return localCommentRepository.add(ModelConstants.DUMMY_PHOTO_ID, commentText)
                .flatMapCompletable(syncCommentUseCase::syncComment);
    }
}
