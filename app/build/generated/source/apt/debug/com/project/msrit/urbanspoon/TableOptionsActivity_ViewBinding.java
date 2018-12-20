// Generated code from Butter Knife. Do not modify!
package com.project.msrit.urbanspoon;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TableOptionsActivity_ViewBinding implements Unbinder {
  private TableOptionsActivity target;

  private View view2131230925;

  private View view2131230748;

  private View view2131230749;

  @UiThread
  public TableOptionsActivity_ViewBinding(TableOptionsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TableOptionsActivity_ViewBinding(final TableOptionsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.view_all_tables, "method 'onClickViewAllTables'");
    view2131230925 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickViewAllTables();
      }
    });
    view = Utils.findRequiredView(source, R.id.add_new_guest, "method 'onClickAddNewGuest'");
    view2131230748 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickAddNewGuest();
      }
    });
    view = Utils.findRequiredView(source, R.id.add_new_table, "method 'onClickAddNewTAble'");
    view2131230749 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickAddNewTAble();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230925.setOnClickListener(null);
    view2131230925 = null;
    view2131230748.setOnClickListener(null);
    view2131230748 = null;
    view2131230749.setOnClickListener(null);
    view2131230749 = null;
  }
}
