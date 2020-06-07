package com.example.responsi.presenter;

import com.example.responsi.model.ArticlesItem;

import java.util.ArrayList;

public interface MainView {
    void onSucces(ArrayList<ArticlesItem> holidaysItems);

    void onError(String errormessage);

    void onFailure(String failuremessage);
}
