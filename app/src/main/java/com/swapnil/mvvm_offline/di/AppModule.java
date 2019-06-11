package com.swapnil.mvvm_offline.di;

import android.content.Context;

import com.swapnil.mvvm_offline.App;
import com.swapnil.mvvm_offline.data.CommentDao;
import com.swapnil.mvvm_offline.data.CommentDatabase;
import com.swapnil.mvvm_offline.data.LocalCommentDataStore;
import com.swapnil.mvvm_offline.data.RemoteCommentDataStore;
import com.swapnil.mvvm_offline.domain.LocalCommentRepository;
import com.swapnil.mvvm_offline.domain.RemoteCommentRepository;
import com.swapnil.mvvm_offline.domain.services.jobs.GcmJobService;
import com.swapnil.mvvm_offline.domain.services.jobs.SchedulerJobService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    SchedulerJobService provideSchedulerJobService() {
        return new SchedulerJobService();
    }

    @Singleton
    @Provides
    GcmJobService provideGcmJobService() {
        return new GcmJobService();
    }

    @Singleton
    @Provides
    CommentDao provideCommentDao(Context context) {
        return CommentDatabase.getInstance(context).commentDao();
    }

    @Singleton
    @Provides
    LocalCommentRepository provideLocalCommentRepository(CommentDao commentDao) {
        return new LocalCommentDataStore(commentDao);
    }

    @Singleton
    @Provides
    RemoteCommentRepository provideRemoteCommentRepository() {
        return new RemoteCommentDataStore();
    }
}
