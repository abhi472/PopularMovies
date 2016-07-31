package com.example.babayaga.popularmovies;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by mits on 7/24/16.
 */

public class ThumbBehaviour extends CoordinatorLayout.Behavior<ImageView> {

    public ThumbBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        float translationY = Math.min(0, (dependency.getTranslationY() - dependency.getHeight()));
        child.setTranslationY(translationY);
        return true;    }
}
