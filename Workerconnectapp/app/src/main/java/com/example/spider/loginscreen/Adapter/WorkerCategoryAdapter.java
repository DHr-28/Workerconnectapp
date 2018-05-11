package com.example.spider.loginscreen.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.spider.loginscreen.Dashboard;
import com.example.spider.loginscreen.Model.WorkerCategory;
import com.example.spider.loginscreen.R;
import com.example.spider.loginscreen.common.helper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by spider on 12/10/2016.
 */
public class WorkerCategoryAdapter extends RecyclerView.Adapter<WorkCategoryRowHolder> {
    private List<WorkerCategory> myDataset;
    private Context mContext;

    public WorkerCategoryAdapter(Context context, List<WorkerCategory> myDataset) {
        this.myDataset = myDataset;
        this.mContext = context;
    }

    @Override
    public WorkCategoryRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.worker_category_block, parent, false);

        return new WorkCategoryRowHolder(itemView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(WorkCategoryRowHolder holder, final int position) {
        final WorkerCategory objWorkerCategory = myDataset.get(position);
        String imgurl =  helper.ImageUrl +objWorkerCategory.Worker_Image;
        Picasso.with(mContext).load(imgurl).into(holder.imgworkerCategory);
       // holder.imgMainCategory.setBackground(mainCategoryData.getImageCategory());
        holder.txtWorkerCategoryName.setText(objWorkerCategory.Worker_Category);
        holder.txtDescription.setText(objWorkerCategory.Worker_Description);
        holder.txtDescription.setSelected(true);
        holder.txtWorkerCategoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,""+position,Toast.LENGTH_LONG);
                ((Dashboard)mContext).gotoWorlerList(objWorkerCategory.Id);
               // Intent intent = new Intent(mContext,WorkerListAdapter.class);
                // mContext.startActivity(intent);
            }
        });
        holder.txtDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,""+position,Toast.LENGTH_LONG);
                ((Dashboard)mContext).gotoWorlerList(objWorkerCategory.Id);
                // Intent intent = new Intent(mContext,WorkerListAdapter.class);
                // mContext.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return (null != myDataset ? myDataset.size() : 0);
    }


}
