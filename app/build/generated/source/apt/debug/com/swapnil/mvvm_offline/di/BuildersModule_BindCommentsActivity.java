package com.swapnil.mvvm_offline.di;

import android.app.Activity;
import com.swapnil.mvvm_offline.view.CommentsActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = BuildersModule_BindCommentsActivity.CommentsActivitySubcomponent.class)
public abstract class BuildersModule_BindCommentsActivity {
  private BuildersModule_BindCommentsActivity() {}

  @Binds
  @IntoMap
  @ActivityKey(CommentsActivity.class)
  abstract AndroidInjector.Factory<? extends Activity> bindAndroidInjectorFactory(
      CommentsActivitySubcomponent.Builder builder);

  @Subcomponent(modules = CommentsActivityModule.class)
  public interface CommentsActivitySubcomponent extends AndroidInjector<CommentsActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CommentsActivity> {}
  }
}
