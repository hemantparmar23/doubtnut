package com.example.doubnut.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.doubnut.data.db.entity.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class DoubnutDatabase extends RoomDatabase {
    public abstract NewsListDao newsListDao();
}
