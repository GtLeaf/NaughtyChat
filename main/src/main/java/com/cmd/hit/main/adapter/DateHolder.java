package com.cmd.hit.main.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cmd.hit.main.R;
import com.cmd.hit.main.model.bean.DateBean;


public class DateHolder extends RecyclerView.ViewHolder {
    private TextView date;
    private TextView week;
    public DateHolder(View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.news_date);
        week = itemView.findViewById(R.id.news_week);
    }
    public void bindHolder(DateBean bean){
        date.setText(bean.getDate());
        week.setText(bean.getWeek());
    }
}
