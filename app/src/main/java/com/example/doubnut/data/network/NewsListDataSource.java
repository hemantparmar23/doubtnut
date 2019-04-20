package com.example.doubnut.data.network;

import android.arch.lifecycle.LiveData;

import com.example.doubnut.data.db.entity.Article;

import java.util.List;

public interface NewsListDataSource {

    void getCurrentNews(String country);

    LiveData<List<Article>> getCurrentNewsLive();

    LiveData<Boolean> getNewsLoadError();

    LiveData<Boolean> getLoading();
}
