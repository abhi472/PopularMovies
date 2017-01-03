// Generated code from Butter Knife. Do not modify!
package com.example.babayaga.popularmovies.fragments;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.example.babayaga.popularmovies.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class DetailFragment_ViewBinding<T extends DetailFragment> implements Unbinder {
  protected T target;

  public DetailFragment_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.toolbar = finder.findOptionalViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.rlv = finder.findOptionalViewAsType(source, R.id.rlv, "field 'rlv'", RelativeLayout.class);
    target.rlv2 = finder.findOptionalViewAsType(source, R.id.card, "field 'rlv2'", CardView.class);
    target.progressBar = finder.findRequiredViewAsType(source, R.id.progressBar3, "field 'progressBar'", ProgressBar.class);
    target.reviewList = finder.findRequiredViewAsType(source, R.id.reviews, "field 'reviewList'", RecyclerView.class);
    target.play = finder.findOptionalViewAsType(source, R.id.play, "field 'play'", ImageView.class);
    target.scrim = finder.findOptionalViewAsType(source, R.id.scrim, "field 'scrim'", ImageView.class);
    target.thumb = finder.findRequiredViewAsType(source, R.id.thumb, "field 'thumb'", ImageView.class);
    target.back = finder.findOptionalViewAsType(source, R.id.image, "field 'back'", ImageView.class);
    target.app = finder.findOptionalViewAsType(source, R.id.app_bar_layout, "field 'app'", AppBarLayout.class);
    target.title = finder.findRequiredViewAsType(source, R.id.title_movie, "field 'title'", TextView.class);
    target.release = finder.findRequiredViewAsType(source, R.id.release, "field 'release'", TextView.class);
    target.ratings = finder.findRequiredViewAsType(source, R.id.ratings, "field 'ratings'", TextView.class);
    target.overview = finder.findRequiredViewAsType(source, R.id.synopsis, "field 'overview'", TextView.class);
    target.pback = finder.findOptionalViewAsType(source, R.id.progressBar, "field 'pback'", ProgressBar.class);
    target.pthumb = finder.findRequiredViewAsType(source, R.id.progressBar2, "field 'pthumb'", ProgressBar.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;
    target.rlv = null;
    target.rlv2 = null;
    target.progressBar = null;
    target.reviewList = null;
    target.play = null;
    target.scrim = null;
    target.thumb = null;
    target.back = null;
    target.app = null;
    target.title = null;
    target.release = null;
    target.ratings = null;
    target.overview = null;
    target.pback = null;
    target.pthumb = null;

    this.target = null;
  }
}
