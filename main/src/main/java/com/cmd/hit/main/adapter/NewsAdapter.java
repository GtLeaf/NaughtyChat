package com.cmd.hit.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmd.hit.main.R;
import com.cmd.hit.main.base.EventCenter;
import com.cmd.hit.main.model.bean.DateBean;
import com.cmd.hit.main.model.bean.NewsBean;
import com.cmd.hit.main.model.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter {

    private List<TypeBean> newsList;

    private EventCenter eventCenter;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TypeBean.NEWS:
                NewsHolder newsHolder = new NewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_news_list_tem,parent,false));
                newsHolder.setEventCenter(eventCenter);
                return newsHolder;
            case TypeBean.DATE:
                return new DateHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_news_list_date_item,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TypeBean.NEWS:
                ((NewsHolder)holder).bindHolder((NewsBean) (newsList.get(position)));
                break;
            case TypeBean.DATE:
                ((DateHolder)holder).bindHolder((DateBean) (newsList.get(position)));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public NewsAdapter() {
        this.newsList = new ArrayList<>();
    }

    public void addNews(TypeBean news) {
        if (news.getType() == TypeBean.DATE) {
            news = (DateBean) news;
        } else if (news.getType() == TypeBean.NEWS) {
            news = (NewsBean) news;
        }
        this.newsList.add(news);
        notifyItemInserted(this.newsList.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return newsList.get(position).getType();
    }

    public EventCenter getEventCenter() {
        return eventCenter;
    }

    public void setEventCenter(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }
}
