package com.example.babayaga.popularmovies.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babayaga.popularmovies.R;
import com.example.babayaga.popularmovies.adapters.FavAdapter;
import com.example.babayaga.popularmovies.data.MoviesContract;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fav_fargment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final int MOVIES_LOADER = 0;


    int grid = 2;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    FavAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie,container,false);
        ButterKnife.bind(this,view);
        adapter = new FavAdapter(null,getContext());
        if(recyclerView.getTag().equals("simple_land"))
        {
            grid = 3;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),grid));
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String sortOrder = MoviesContract.TITLE + " ASC";
        Uri movieContractUri = MoviesContract.CONTENT_URI;

        return new CursorLoader(getActivity(),
                movieContractUri,
                null,
                null,
                null,
                sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
      adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(MOVIES_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }
}
