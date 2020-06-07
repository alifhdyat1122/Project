package com.example.responsi.presenter;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;


import com.example.responsi.model.ArticlesItem;
import com.example.responsi.model.NewsResponse;
import com.example.responsi.service.ApiMain;
import com.example.responsi.view.viewmodel.NewsViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    private ApiMain apiMain;
    private MutableLiveData<ArrayList<ArticlesItem>> arrayListMutableLiveData = new MutableLiveData<ArrayList<ArticlesItem>>();

    private Context context;
    private MainView mainView;

    public MainPresenter(Context context, MainView mainView){
        this.context = context;
        this.mainView = mainView;
    }
    public void loadInstitusi(String api_key, String country, String year){
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApi().getDiscover(api_key, country, year).enqueue(new Callback<NewsResponse>() {

            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse = response.body();
                if (newsResponse != null && newsResponse.getArticles() != null) {
                    ArrayList<ArticlesItem> articlesItems = newsResponse.getArticles();
                    arrayListMutableLiveData.postValue(articlesItems);
                }
                else {
                    mainView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                mainView.onFailure(t.getMessage());
            }
        });
    }
}

