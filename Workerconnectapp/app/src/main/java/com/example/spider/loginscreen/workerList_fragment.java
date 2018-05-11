package com.example.spider.loginscreen;


import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spider.loginscreen.Adapter.WorkerCategoryAdapter;
import com.example.spider.loginscreen.Adapter.WorkerListAdapter;
import com.example.spider.loginscreen.Model.WorkerCategory;
import com.example.spider.loginscreen.Model.WorkerDetail;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class workerList_fragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    //adapter
    private WorkerListAdapter mAdapter;
    private int workercategoryid=0;
    private static Button btnHireme;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    private List<WorkerDetail> lstofWorkerDetail = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("workcategoryid"))
        {
            workercategoryid = Integer.valueOf(getArguments().getInt("workcategoryid"));
        }


        View view = inflater.inflate(R.layout.fragment_worker_category_, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        mRecyclerView = (RecyclerView) view. findViewById(R.id.recyclerView);
        btnHireme = (Button) view.findViewById(R.id.btnHireme);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        fetchWorkerby_CategoryId();
      /*  shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            btnHireme.setTextColor(csl);
        } catch (Exception e) {
        }*/
        return view;
    }


    private void fetchWorkerby_CategoryId() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String REGISTER_URL = helper.sericeUrl +"/WorkerDetailSelectAll?categoryid=" + workercategoryid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("lstdata");
                            for(int i = 0 ; i<jsonArray.length();i++){
                                JSONObject object1 = jsonArray.getJSONObject(i);
                                WorkerDetail objWorkerCategory = new WorkerDetail(
                                        (Integer) object1.get("Worker_id"),
                                        ((String)object1.get("Worker_Name")),
                                        (String) object1.get("Worker_Address"),
                                        ((String)object1.get("Worker_City")),workercategoryid,
                                        ((String)object1.get("Worker_Ph_No")),
                                        ((String)object1.get("Worker_Exprience")),
                                        ((String)object1.get("Worker_Pinncode")),
                                        ((String)object1.get("Basic_Rate")));
                                lstofWorkerDetail.add(objWorkerCategory);
                            }
                            mAdapter = new WorkerListAdapter(getActivity(),lstofWorkerDetail);
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
   /* public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHireme:

              *//*  // *//**//*Replace forgot password fragment with animation
              //  fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new workerTaskList_Fragment(),
                                utils.workerTaskList_Fragment).commit();
               // break;
        }*//*

    }*/

}
