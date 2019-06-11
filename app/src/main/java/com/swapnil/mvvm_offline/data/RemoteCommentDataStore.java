package com.swapnil.mvvm_offline.data;

import com.swapnil.mvvm_offline.domain.RemoteCommentRepository;
import com.swapnil.mvvm_offline.domain.services.jobs.JobManagerFactory;
import com.swapnil.mvvm_offline.domain.services.jobs.SyncCommentJob;
import com.swapnil.mvvm_offline.model.Comment;

import io.reactivex.Completable;

public class RemoteCommentDataStore implements RemoteCommentRepository {

    @Override
    public Completable sync(Comment comment) {
        return Completable.fromAction(() ->
                JobManagerFactory.getJobManager().addJobInBackground(new SyncCommentJob(comment)));
    }
}
