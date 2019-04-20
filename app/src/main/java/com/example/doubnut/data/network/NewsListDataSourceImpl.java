package com.example.doubnut.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.doubnut.data.db.entity.Article;
import com.example.doubnut.data.network.response.NewsResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsListDataSourceImpl implements NewsListDataSource {

    private final CompositeDisposable disposable;
    private NewsService newsService;
    private final MutableLiveData<List<Article>> mDownloadedData;
    private final MutableLiveData<Boolean> newsLoadError;
    private final MutableLiveData<Boolean> loading;

    public NewsListDataSourceImpl(NewsService newsService) {
        this.newsService = newsService;
        this.mDownloadedData = new MutableLiveData<>();
        disposable = new CompositeDisposable();
        newsLoadError = new MutableLiveData<>();
        loading = new MutableLiveData<>();
    }

    @Override
    public void getCurrentNews(String country) {
        disposable.add(newsService.getNewsList(country).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<NewsResponse>() {
                    @Override
                    public void onSuccess(NewsResponse value) {
                        mDownloadedData.setValue(value.getArticles());
                        newsLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        newsLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    public LiveData<List<Article>> getCurrentNewsLive() {
        return mDownloadedData;
    }

    @Override
    public LiveData<Boolean> getNewsLoadError() {
        return newsLoadError;
    }

    @Override
    public LiveData<Boolean> getLoading() {
        return loading;
    }
}
