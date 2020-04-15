package com.cmd.hit.main.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cmd.hit.main.NewsContentActivity;
import com.cmd.hit.main.R;
import com.cmd.hit.main.model.bean.NewsBean;

public class NewsHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;
    private View itemView;
    public NewsHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        textView = (TextView)itemView.findViewById(R.id.news_item_title);
        imageView = (ImageView)itemView.findViewById(R.id.news_item_image);
    }
    public void bindHolder(NewsBean newsBean){
        textView.setText(newsBean.getTitle());
        Glide.with(itemView.getContext()).load(newsBean.getImage()).into(imageView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.getInstance(), ""+newsBean.getId(), Toast.LENGTH_SHORT).show();
                NewsContentActivity.actionStart(newsBean.getId(), itemView.getContext());
            }
        });
    }
}
