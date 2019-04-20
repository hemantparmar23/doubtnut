package com.example.doubnut.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.doubnut.data.db.DoubnutDatabase;
import com.example.doubnut.data.db.NewsListDao;
import com.example.doubnut.data.network.NewsListDataSource;
import com.example.doubnut.data.network.NewsListDataSourceImpl;
import com.example.doubnut.data.network.NewsService;
import com.example.doubnut.data.repository.NewsRepository;
import com.example.doubnut.data.repository.NewsRepositoryImpl;
import com.example.doubnut.utils.ConnectionSniffer;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class, ApplicationModule.class})
public class DoubnutModule {

    @Provides
    public NewsListDao getNewsListDao(DoubnutDatabase doubnutDatabase) {
        return doubnutDatabase.newsListDao();
    }

    @Provides
    public DoubnutDatabase getDoubtnutDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                DoubnutDatabase.class, "newslist.db")
                .build();
    }

    @Provides
    public NewsRepository getNewsRepo(NewsListDao newsListDao, NewsListDataSource newsListDataSource) {
        return new NewsRepositoryImpl(newsListDao, newsListDataSource);
    }

    @Provides
    public NewsListDataSource getNewsListDataSource(NewsService newsService) {
        return new NewsListDataSourceImpl(newsService);
    }

    public ConnectionSniffer getConnectionSniffer(Context context) {
        return new ConnectionSniffer(context);
    }
}
