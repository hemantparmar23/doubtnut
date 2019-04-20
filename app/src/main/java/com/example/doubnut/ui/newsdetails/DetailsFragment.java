package com.example.doubnut.ui.newsdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doubnut.R;
import com.example.doubnut.base.BaseFragment;
import com.example.doubnut.data.db.entity.Article;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.newsTitleTextView)
    TextView newsTitleTextView;
    @BindView(R.id.newsDescTextView)
    TextView newsDescTextView;
    @BindView(R.id.newsImageView)
    ImageView newsImageView;

    @Override
    protected int layoutRes() {
        return R.layout.new_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Article article = getArguments().getParcelable("article");
        Picasso.with(view.getContext()).load(article.getUrlToImage()).into(newsImageView);
        newsTitleTextView.setText(article.getTitle());
        newsDescTextView.setText(article.getDescription());

    }
}
