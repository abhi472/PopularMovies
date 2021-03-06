package com.example.babayaga.popularmovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.babayaga.popularmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mits on 7/28/16.
 */

public class ErrorFragment extends Fragment {

    Bundle bundle ;
    @BindView(R.id.text)
    TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.error_layout,container,false);
        ButterKnife.bind(this,view);
        bundle = getArguments();
        if(bundle !=null)
            text.setText(bundle.getString("title"));

      return view;
    }
}
