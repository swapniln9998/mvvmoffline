// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.swapnil.mvvm_offline.di;

import com.swapnil.mvvm_offline.domain.RemoteCommentRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AppModule_ProvideRemoteCommentRepositoryFactory
    implements Factory<RemoteCommentRepository> {
  private final AppModule module;

  public AppModule_ProvideRemoteCommentRepositoryFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public RemoteCommentRepository get() {
    return Preconditions.checkNotNull(
        module.provideRemoteCommentRepository(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<RemoteCommentRepository> create(AppModule module) {
    return new AppModule_ProvideRemoteCommentRepositoryFactory(module);
  }

  /** Proxies {@link AppModule#provideRemoteCommentRepository()}. */
  public static RemoteCommentRepository proxyProvideRemoteCommentRepository(AppModule instance) {
    return instance.provideRemoteCommentRepository();
  }
}
