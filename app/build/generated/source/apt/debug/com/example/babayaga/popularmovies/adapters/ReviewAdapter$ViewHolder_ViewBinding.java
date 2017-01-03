// Generated code from Butter Knife. Do not modify!
package com.example.babayaga.popularmovies.adapters;

import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.example.babayaga.popularmovies.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ReviewAdapter$ViewHolder_ViewBinding<T extends ReviewAdapter.ViewHolder> implements Unbinder {
  protected T target;

  public ReviewAdapter$ViewHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.text = finder.findRequiredViewAsType(source, R.id.reviewText, "field 'text'", TextView.class);
    target.author = finder.findRequiredViewAsType(source, R.id.author, "field 'author'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.text = null;
    target.author = null;

    this.target = null;
  }
}
