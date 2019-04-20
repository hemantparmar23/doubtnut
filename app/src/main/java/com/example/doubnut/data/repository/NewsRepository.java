package com.example.doubnut.data.repository;

import android.arch.lifecycle.LiveData;

import com.example.doubnut.data.db.entity.Article;

import java.util.List;

public interface NewsRepository {

    void persistNewsToDB(String country);

    LiveData<List<Article>> getNewsFromLocalDB();

    LiveData<Boolean> getNewsLoadError();

    LiveData<Boolean> getLoading();
}
