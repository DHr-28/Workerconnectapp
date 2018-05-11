package com.example.spider.loginscreen.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spider.loginscreen.R;

/**
 * Created by spider on 12/10/2016.
 */
public class WorkCategoryRowHolder extends RecyclerView.ViewHolder {

    public ImageView imgworkerCategory;
    public TextView txtWorkerCategoryName;
    public TextView txtDescription;


    public WorkCategoryRowHolder(View itemView) {
        super(itemView);
        imgworkerCategory = (ImageView) itemView.findViewById(R.id.imgworkerCategory);
        txtWorkerCategoryName = (TextView)itemView.findViewById(R.id.txtWorkerCategoryName);
        txtDescription = (TextView)itemView.findViewById(R.id.txtDescription);

    }
}
