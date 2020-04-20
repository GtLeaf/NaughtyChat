package com.cmd.hit.main.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cmd.hit.main.NewsContentActivity;
import com.cmd.hit.main.R;
import com.cmd.hit.main.model.bean.NewsBean;
import com.cmd.hit.main.view.AdWidgetConstants;

public class NewsHolder extends FeedViewHolder {
    private TextView textView;
    private ImageView imageView;
    private View itemView;
    public NewsHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        textView = (TextView)itemView.findViewById(R.id.news_item_title);
        imageView = (ImageView)itemView.findViewById(R.id.news_item_image);
    }

    public void bindHolder(NewsBean newsBean) {
        textView.setText(newsBean.getTitle());
        Glide.with(itemView.getContext()).load(newsBean.getImage()).into(imageView);
        itemView.setOnClickListener(view -> {
//                Toast.makeText(MainActivity.getInstance(), ""+newsBean.getId(), Toast.LENGTH_SHORT).show();
            getEventCenter().put(AdWidgetConstants.ACTION_LIGHT_WEB_PAGE_SHOW, null);
//            getEventCenter().put(AdWidgetConstants.ACTION_WEB_CARD_SHOW, null);
//            NewsContentActivity.actionStart(newsBean.getId(), itemView.getContext());
        });
    }

}
