package com.example.doubnut.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.doubnut.data.db.entity.Article;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NewsListDao {

    @Query("SELECT * FROM news_list")
    LiveData<List<Article>> getAll();

    @Insert
    List<Long> insertAll(List<Article> topHeadlines);

    @Query("DELETE FROM news_list")
    void deleteAllUsers();

}
