// Generated code from Butter Knife. Do not modify!
package com.example.babayaga.popularmovies.activities;

import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.example.babayaga.popularmovies.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MovieList_ViewBinding<T extends MovieList> implements Unbinder {
  protected T target;

  public MovieList_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.frag_container = finder.findRequiredViewAsType(source, R.id.fragment_container, "field 'frag_container'", RelativeLayout.class);
    target.mFragment = finder.findOptionalViewAsType(source, R.id.detail_container, "field 'mFragment'", RelativeLayout.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.frag_container = null;
    target.mFragment = null;

    this.target = null;
  }
}
