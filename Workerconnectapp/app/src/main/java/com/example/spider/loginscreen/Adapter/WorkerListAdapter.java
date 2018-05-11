package com.example.spider.loginscreen.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spider.loginscreen.Dashboard;
import com.example.spider.loginscreen.Model.WorkerCategory;
import com.example.spider.loginscreen.Model.WorkerDetail;
import com.example.spider.loginscreen.R;
import com.example.spider.loginscreen.common.helper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by spider on 12/10/2016.
 */
public class WorkerListAdapter extends RecyclerView.Adapter<WorkerListAdapter.MyViewHolder> {
    private List<WorkerDetail> myDataset;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView workername, workerContact,workerpinncode,workerExprience,workerBasicRate;
        public Button btnHireme;
        public MyViewHolder(View view) {
            super(view);
            workername = (TextView) view.findViewById(R.id.workername);
            workerContact = (TextView) view.findViewById(R.id.workerContact);
            workerpinncode = (TextView) view.findViewById(R.id.workerpinncode);
            workerExprience = (TextView) view.findViewById(R.id.workerExprience);
            workerBasicRate = (TextView) view.findViewById(R.id.workerBasicRate);
            btnHireme = (Button) view.findViewById(R.id.btnHireme);

        }
    }

    public WorkerListAdapter(Context context, List<WorkerDetail> myDataset) {
        this.myDataset = myDataset;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workerlist_block, parent, false);

        return new MyViewHolder(itemView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final WorkerDetail objWorkerDetail = myDataset.get(position);
        holder.workername.setText(objWorkerDetail.Worker_Name);
        holder.workerContact.setText(objWorkerDetail.Worker_Ph_No);
        holder.workerpinncode.setText(objWorkerDetail.Worker_Pinncode);
        holder.workerExprience.setText(objWorkerDetail.Worker_Exprience);
        holder.workerBasicRate.setText(objWorkerDetail.Basic_Rate);
        holder.btnHireme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Dashboard)mContext).gotoWorkerTaskAssign(objWorkerDetail.Worker_id);
            }
        });

    }
    @Override
    public int getItemCount() {
        return (null != myDataset ? myDataset.size() : 0);
    }
}
