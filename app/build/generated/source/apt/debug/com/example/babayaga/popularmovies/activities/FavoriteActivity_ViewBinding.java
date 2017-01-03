// Generated code from Butter Knife. Do not modify!
package com.example.babayaga.popularmovies.activities;

import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.example.babayaga.popularmovies.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FavoriteActivity_ViewBinding<T extends FavoriteActivity> implements Unbinder {
  protected T target;

  public FavoriteActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.frag_container = finder.findRequiredViewAsType(source, R.id.activity_favorite, "field 'frag_container'", RelativeLayout.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.frag_container = null;

    this.target = null;
  }
}
