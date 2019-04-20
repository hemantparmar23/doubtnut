package com.example.doubnut.ui.newlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.doubnut.data.db.entity.Article;
import com.example.doubnut.data.repository.NewsRepository;
import com.example.doubnut.utils.ConnectionSniffer;

import java.util.List;

import javax.inject.Inject;

public class ListViewModel extends ViewModel {

    private final NewsRepository newsRepository;
    private ConnectionSniffer connectionSniffer;

    private final MutableLiveData<List<Article>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ListViewModel(NewsRepository newsRepository, ConnectionSniffer connectionSniffer) {
        this.newsRepository = newsRepository;
        this.connectionSniffer = connectionSniffer;
    }

    LiveData<List<Article>> getNewsList() {
        return newsRepository.getNewsFromLocalDB();
    }

    LiveData<Boolean> getError() {
        return newsRepository.getNewsLoadError();
    }

    LiveData<Boolean> getLoading() {
        return newsRepository.getLoading();
    }


    void saveNewsToDB(String country) {
        newsRepository.persistNewsToDB(country);
    }
}