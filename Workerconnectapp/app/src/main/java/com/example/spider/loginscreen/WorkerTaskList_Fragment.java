package com.example.spider.loginscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spider.loginscreen.Adapter.WorkerCategoryAdapter;
import com.example.spider.loginscreen.Adapter.WorkerTaskAdapter;
import com.example.spider.loginscreen.Model.WorkerCategory;
import com.example.spider.loginscreen.Model.WorkerTask;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spider on 2/19/2017.
 */
public class WorkerTaskList_Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    //adapter
    private WorkerTaskAdapter mAdapter;

    private List<WorkerTask> lstofWorkerTask = new ArrayList<>();
    SessionManager objSessionManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        objSessionManager = new SessionManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_worker_category_, container, false);
        mRecyclerView = (RecyclerView) view. findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
       // WorkerCategoryList();
         WorkerTaskList();
        return view;
    }


    private void WorkerTaskList() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String REGISTER_URL = helper.sericeUrl +"/Worker_Task_ByWorkerId_ser?WorkerId="+objSessionManager.getLoginUSerid() ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("lstdata");
                            for(int i = 0 ; i<jsonArray.length();i++){
                                JSONObject object1 = jsonArray.getJSONObject(i);
                                WorkerTask objWorkerTask = new WorkerTask(
                                        ((String) object1.get("Description")),
                                        ((Integer) object1.get("WorkerId")),
                                        ((Integer) object1.get("Customer_id")),
                                        ((String)object1.get("Status")),
                                        ((String) object1.get("Customer_Name")),
                                        ((String)object1.get("Ph_No")),
                                         ((String)object1.get("Date")),
                                        ((Integer)object1.get("Worker_Task_Id")));

                                lstofWorkerTask.add(objWorkerTask);
                            }
                            mAdapter = new WorkerTaskAdapter(getActivity(),lstofWorkerTask);
                            mRecyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error", "" + error);
                    }
                }) {

        };

        requestQueue.add(stringRequest);
    }



}
