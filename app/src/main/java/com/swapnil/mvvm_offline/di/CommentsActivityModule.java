package com.swapnil.mvvm_offline.di;

import com.swapnil.mvvm_offline.domain.DeleteCommentUseCase;
import com.swapnil.mvvm_offline.domain.GetCommentsUseCase;
import com.swapnil.mvvm_offline.domain.LocalCommentRepository;
import com.swapnil.mvvm_offline.domain.RemoteCommentRepository;
import com.swapnil.mvvm_offline.domain.services.SyncCommentLifecycleObserver;
import com.swapnil.mvvm_offline.domain.AddCommentUseCase;
import com.swapnil.mvvm_offline.domain.SyncCommentUseCase;
import com.swapnil.mvvm_offline.domain.UpdateCommentUseCase;
import com.swapnil.mvvm_offline.presentation.CommentsViewModelFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Define CommentsActivity-specific dependencies here.
 */
@Module
public class CommentsActivityModule {
    @Provides
    CommentsViewModelFactory provideCommentsViewModelFactory(GetCommentsUseCase getCommentsUseCase,
                                                             AddCommentUseCase addCommentUseCase) {
        return new CommentsViewModelFactory(getCommentsUseCase, addCommentUseCase);
    }

    @Provides
    SyncCommentLifecycleObserver provideSyncCommentLifecycleObserver(UpdateCommentUseCase updateCommentUseCase,
                                                                     DeleteCommentUseCase deleteCommentUseCase) {
        return new SyncCommentLifecycleObserver(updateCommentUseCase, deleteCommentUseCase);
    }

    @Provides
    AddCommentUseCase provideAddCommentUseCase(LocalCommentRepository localCommentRepository, SyncCommentUseCase syncCommentUseCase) {
        return new AddCommentUseCase(localCommentRepository, syncCommentUseCase);
    }

    @Provides
    GetCommentsUseCase provideGetCommentsUseCase(LocalCommentRepository localCommentRepository) {
        return new GetCommentsUseCase(localCommentRepository);
    }

    @Provides
    UpdateCommentUseCase provideUpdateCommentUseCase(LocalCommentRepository localCommentRepository) {
        return new UpdateCommentUseCase(localCommentRepository);
    }

    @Provides
    DeleteCommentUseCase provideDeleteCommentUseCase(LocalCommentRepository localCommentRepository) {
        return new DeleteCommentUseCase(localCommentRepository);
    }

    @Provides
    SyncCommentUseCase provideSyncCommentUseCase(RemoteCommentRepository remoteCommentRepository) {
        return new SyncCommentUseCase(remoteCommentRepository);
    }
}
