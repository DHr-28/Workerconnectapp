package com.example.spider.loginscreen;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spider.loginscreen.Adapter.WorkerCategoryAdapter;
import com.example.spider.loginscreen.Model.WorkerCategory;
import com.example.spider.loginscreen.Model.WorkerTask;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkerCategory_Fragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    //adapter
    private WorkerCategoryAdapter mAdapter;

    private List<WorkerCategory> lstofworkcategory = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_worker_category_, container, false);
        mRecyclerView = (RecyclerView) view. findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        WorkerCategoryList();

        return view;
    }


    private void WorkerCategoryList() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String REGISTER_URL = helper.sericeUrl +"/WorkerCategorySelectAll";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("lstdata");
                            for(int i = 0 ; i<jsonArray.length();i++){
                                JSONObject object1 = jsonArray.getJSONObject(i);
                                WorkerCategory objWorkerCategory = new WorkerCategory(
                                        ((String)object1.get("Worker_Category")),
                                        (Integer) object1.get("Id"),
                                        ((String)object1.get("Worker_Description")),
                                        ((String)object1.get("Worker_Image")));

                                lstofworkcategory.add(objWorkerCategory);
                            }
                            mAdapter = new WorkerCategoryAdapter(getActivity(),lstofworkcategory);
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
