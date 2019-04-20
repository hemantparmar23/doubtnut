package com.example.doubnut.ui.newlist;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doubnut.R;
import com.example.doubnut.data.db.entity.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private NewsSelectedListener newsSelectedListener;
    private final List<Article> data = new ArrayList<>();

    NewsListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, NewsSelectedListener newsSelectedListener) {
        this.newsSelectedListener = newsSelectedListener;
        viewModel.getNewsList().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext()).load(data.get(position).getUrlToImage()).into(holder.newsImageView);
        holder.newsTitleTextView.setText(data.get(position).getTitle());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    final class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.newsTitleTextView)
        TextView newsTitleTextView;
        @BindView(R.id.newsImageView)
        ImageView newsImageView;

        private Article article;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            newsSelectedListener.onArticleSelected(data.get(getAdapterPosition()));
        }
    }
}
