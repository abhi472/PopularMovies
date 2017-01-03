// Generated code from Butter Knife. Do not modify!
package com.example.babayaga.popularmovies.fragments;

import android.support.v7.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.example.babayaga.popularmovies.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class Fav_fargment_ViewBinding<T extends Fav_fargment> implements Unbinder {
  protected T target;

  public Fav_fargment_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.recyclerView = finder.findRequiredViewAsType(source, R.id.recycler, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.recyclerView = null;

    this.target = null;
  }
}
