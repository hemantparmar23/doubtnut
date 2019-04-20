package com.example.doubnut.ui.newsdetails;

import android.arch.lifecycle.ViewModel;

import com.example.doubnut.data.repository.NewsRepository;

import javax.inject.Inject;

public class DetailsViewModel extends ViewModel {

    private final NewsRepository repoRepository;

    @Inject
    public DetailsViewModel(NewsRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

}
