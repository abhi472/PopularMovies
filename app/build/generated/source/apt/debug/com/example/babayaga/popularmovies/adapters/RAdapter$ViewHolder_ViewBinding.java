// Generated code from Butter Knife. Do not modify!
package com.example.babayaga.popularmovies.adapters;

import android.support.v7.widget.CardView;
import android.widget.TextView;
import android.widget.ToggleButton;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.utils.CustomImageView;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class RAdapter$ViewHolder_ViewBinding<T extends RAdapter.ViewHolder> implements Unbinder {
  protected T target;

  public RAdapter$ViewHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.img = finder.findRequiredViewAsType(source, R.id.img, "field 'img'", CustomImageView.class);
    target.cardView = finder.findRequiredViewAsType(source, R.id.card, "field 'cardView'", CardView.class);
    target.toggle = finder.findRequiredViewAsType(source, R.id.fav, "field 'toggle'", ToggleButton.class);
    target.name = finder.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.img = null;
    target.cardView = null;
    target.toggle = null;
    target.name = null;

    this.target = null;
  }
}
