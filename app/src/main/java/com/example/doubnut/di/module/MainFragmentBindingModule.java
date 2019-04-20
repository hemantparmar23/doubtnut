package com.example.doubnut.di.module;

import com.example.doubnut.ui.newlist.ListFragment;
import com.example.doubnut.ui.newsdetails.DetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}
