package com.example.doubnut.ui.newlist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.doubnut.R;
import com.example.doubnut.base.BaseFragment;
import com.example.doubnut.data.db.entity.Article;
import com.example.doubnut.ui.newsdetails.DetailsFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class ListFragment extends BaseFragment implements NewsSelectedListener {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private ListViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.news_list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new NewsListAdapter(viewModel, this, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observableViewModel();
        saveNewsToDB();

    }

    private void saveNewsToDB() {
        viewModel.saveNewsToDB("in");
    }

    @Override
    public void onArticleSelected(Article article) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("article", article);
        Fragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.screenContainer, fragment)
                .addToBackStack(null).commit();
    }

    private void observableViewModel() {
        viewModel.getNewsList().observe(this, articles -> {
            if (articles != null) listView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                listView.setVisibility(View.GONE);
            } else {
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }

}