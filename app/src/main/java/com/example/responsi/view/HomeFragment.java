package com.example.responsi.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.responsi.R;
import com.example.responsi.adapter.Adapter;
import com.example.responsi.model.ArticlesItem;
import com.example.responsi.presenter.MainView;
import com.example.responsi.view.viewmodel.NewsViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements MainView {

    private Adapter adapter;
    RecyclerView recyclerView;
    private NewsViewModel newsViewModel;
    private String api_key = "d2890b74ad774387bbf8258f8996075d";
    private String country = "US";
    private String year = "2020";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new Adapter(getContext());
        adapter.notifyDataSetChanged();

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.setNewsDiscover(api_key, country, year);
        newsViewModel.getNewsDiscover().observe(this, getListNews);
    }

    private Observer<ArrayList<ArticlesItem>> getListNews = new Observer<ArrayList<ArticlesItem>>() {
        @Override
        public void onChanged(ArrayList<ArticlesItem> articlesItems) {
            if (articlesItems != null) {
                adapter.setData(articlesItems);
                recyclerView.setAdapter(adapter);
            }
        }
    };

    @Override
    public void onSucces(ArrayList<ArticlesItem> articlesItems) {

    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String failureMessage) {
        Toast.makeText(getContext(), failureMessage, Toast.LENGTH_SHORT).show();
    }

}
