package com.example.spider.loginscreen.Adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spider.loginscreen.Dashboard;
import com.example.spider.loginscreen.Model.WorkerDetail;
import com.example.spider.loginscreen.Model.WorkerTask;
import com.example.spider.loginscreen.R;
import com.example.spider.loginscreen.TaskAssignDesc;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spider on 2/19/2017.
 */
public class WorkerTaskAdapter extends RecyclerView.Adapter<WorkerTaskAdapter.MyViewHolder>{
    private List<WorkerTask> myDataset;
    private Context mContext;


    public WorkerTaskAdapter(Context context, List<WorkerTask> myDataset) {
        this.myDataset = myDataset;
        this.mContext = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.worker_task, parent, false);

        return new MyViewHolder(itemView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final WorkerTask objWorkerTask = myDataset.get(position);

        // holder.workerid.setText(objWorkerTask.WorkerId);
        // holder.Customerid.setText(objWorkerTask.Customer_id);
        holder.description.setText(objWorkerTask.Description);
        holder.status.setText(objWorkerTask.Status);
        holder.Customer_Name.setText(objWorkerTask.Customer_Name);
        holder.Ph_No.setText(objWorkerTask.Ph_No);
        holder.Date.setText(objWorkerTask.Date);

        holder.btndecline.setEnabled(false);
        holder.btnapprove.setEnabled(false);
        if (objWorkerTask.Status.equals("Pending")) {
            holder.btndecline.setEnabled(true);
            holder.btnapprove.setEnabled(true);
        }
        else  if (objWorkerTask.Status .equals("Approved")) {
            holder.btnapprove.setText("Already Approved");
            holder.btndecline.setVisibility(View.GONE);
        }
        else  if (objWorkerTask.Status .equals("Declined")) {
            holder.btndecline.setText("Already Declined");
            holder.btnapprove.setVisibility(View.GONE);
        }
        else{}


            holder.btndecline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Worker_Statusupdate("Declined", objWorkerTask.Worker_Task_Id);
                    holder.btndecline.setVisibility(View.GONE);
                   // holder.btndecline.setEnabled(false);
                }

            });

            holder.btnapprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Worker_Statusupdate("Approved", objWorkerTask.Worker_Task_Id);
                    holder.btnapprove.setVisibility(View.GONE);
                   // holder.btndecline.setEnabled(false);
                }
            });

    }
    @Override
    public int getItemCount() {
        return (null != myDataset ? myDataset.size() : 0);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView workerid, Customerid,description,status,Customer_Name,Ph_No,Date;
        public Button btnapprove,btndecline;
       // public int worktaskid;
        public MyViewHolder(View view) {
            super(view);
            //workerid = (TextView) view.findViewById(R.id.workerid);
            //Customerid = (TextView) view.findViewById(R.id.Customerid);
            description = (TextView) view.findViewById(R.id.description);
            status = (TextView) view.findViewById(R.id.status);
            Customer_Name = (TextView) view.findViewById(R.id.Customer_Name);
            Ph_No = (TextView) view.findViewById(R.id.Ph_No);
            Date = (TextView) view.findViewById(R.id.Date);
            btnapprove = (Button) view.findViewById(R.id.btnapprove);
            btndecline = (Button) view.findViewById(R.id.btndecline);

          //  btndecline.setOnClickListener(this);
        }





        }


    public void Worker_Statusupdate(String statusmsg,int  Worker_Task_Id) {

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        String REGISTER_URL = helper.sericeUrl +"/Worker_Task_Status?Worker_Task_Id="+Worker_Task_Id+"&Statusmsg="+statusmsg;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            Toast.makeText(mContext,object.getString("status"), Toast.LENGTH_LONG).show();
                           // finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error", "" + error);
                    }
                }) {

        };

        requestQueue.add(stringRequest);
    }


}

