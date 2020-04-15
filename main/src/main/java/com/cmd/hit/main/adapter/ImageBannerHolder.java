package com.cmd.hit.main.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.cmd.hit.main.R;
import com.cmd.hit.main.view.ImageBannerFarmLayout;


/**
 * Created by PC-0775 on 2019/5/7.
 */

public class ImageBannerHolder extends RecyclerView.ViewHolder {

    private ImageBannerFarmLayout ibfl_item;

    public ImageBannerHolder(View itemView) {
        super(itemView);
        ibfl_item = itemView.findViewById(R.id.ibfl_item);
    }
}
