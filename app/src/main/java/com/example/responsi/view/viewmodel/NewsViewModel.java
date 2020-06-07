package com.example.responsi.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.responsi.model.ArticlesItem;
import com.example.responsi.model.NewsResponse;
import com.example.responsi.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsViewModel extends ViewModel {
    private ApiMain apiMain;

    private MutableLiveData<ArrayList<ArticlesItem>> listMutableLiveData = new MutableLiveData<ArrayList<ArticlesItem>>();

    public void setNewsDiscover(String api_key, String country, String year) {
        if (this.apiMain == null) {
            apiMain = new ApiMain();
        }

        apiMain.getApi().getDiscover(api_key, country, year).enqueue(new Callback<NewsResponse>() {

            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse newsResponse = response.body();
                if (newsResponse != null && newsResponse.getArticles() != null) {
                    ArrayList<ArticlesItem> newsArticles = newsResponse.getArticles();
                    listMutableLiveData.postValue(newsArticles);

                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<ArticlesItem>> getNewsDiscover(){
        return listMutableLiveData;
    }
}