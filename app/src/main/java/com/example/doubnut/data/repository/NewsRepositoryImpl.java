package com.example.doubnut.data.repository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.doubnut.data.db.NewsListDao;
import com.example.doubnut.data.db.entity.Article;
import com.example.doubnut.data.network.NewsListDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class NewsRepositoryImpl implements NewsRepository {

    private NewsListDao newsListDao;
    private NewsListDataSource newsListDataSource;

    public NewsRepositoryImpl(NewsListDao newsListDao, NewsListDataSource newsListDataSource) {
        this.newsListDao = newsListDao;
        this.newsListDataSource = newsListDataSource;

        syncRemoteWithLocalDB();
    }

    @Override
    public void persistNewsToDB(String country) {
        newsListDataSource.getCurrentNews(country);
    }

    public void syncRemoteWithLocalDB() {
        newsListDataSource.getCurrentNewsLive().observeForever(this::saveNewsToDB);
    }

    private void saveNewsToDB(List<Article> articles) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                persistNewsToDB(articles);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("dispose", "dispose");
            }

            @Override
            public void onComplete() {
                Log.d("onComplete", "onComplerte");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("error", "error");
            }
        });
    }

    private void persistNewsToDB(List<Article> articleList) {
        newsListDao.deleteAllUsers();
        newsListDao.insertAll(articleList);
    }

    public LiveData<List<Article>> getNewsFromLocalDB() {
        return newsListDao.getAll();
    }

    @Override
    public LiveData<Boolean> getNewsLoadError() {
        return newsListDataSource.getNewsLoadError();
    }

    @Override
    public LiveData<Boolean> getLoading() {
        return newsListDataSource.getLoading();
    }

}
