// Generated code from Butter Knife. Do not modify!
package com.swapnil.mvvm_offline.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.swapnil.mvvm_offline.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommentsActivity_ViewBinding implements Unbinder {
  private CommentsActivity target;

  private View view2131165211;

  @UiThread
  public CommentsActivity_ViewBinding(CommentsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CommentsActivity_ViewBinding(final CommentsActivity target, View source) {
    this.target = target;

    View view;
    target.addCommentEditText = Utils.findRequiredViewAsType(source, R.id.add_comment_edittext, "field 'addCommentEditText'", EditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.comments_recycler_view, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.add_comment_button, "method 'onAddCommentButtonClicked'");
    view2131165211 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddCommentButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CommentsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addCommentEditText = null;
    target.recyclerView = null;

    view2131165211.setOnClickListener(null);
    view2131165211 = null;
  }
}
