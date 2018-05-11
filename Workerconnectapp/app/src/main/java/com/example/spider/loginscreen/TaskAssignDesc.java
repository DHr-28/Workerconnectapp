package com.example.spider.loginscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spider.loginscreen.Adapter.CategoryDropdownAdapter;
import com.example.spider.loginscreen.Model.Category;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TaskAssignDesc extends AppCompatActivity implements View.OnClickListener {

    int workerid,customerid;
    Button btnSubmit;
    EditText taskdesc_content;
    SessionManager objSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assign_desc);
        objSessionManager = new SessionManager(this);
        customerid=objSessionManager.getLoginUSerid();
        Intent myIntent = getIntent(); // gets the previously created intent
        workerid = myIntent.getIntExtra("workerid",0);
        btnSubmit =(Button) findViewById(R.id.btnSubmit);
        taskdesc_content= (EditText) findViewById(R.id.taskdesc_content);
        //taskdesc_content= (EditText) findViewById(R.id.taskdesc_content);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Worker_TaskInsert();
    }

    private void Worker_TaskInsert() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
       ;
        String REGISTER_URL = helper.sericeUrl +"/Worker_TaskInsert";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            Toast.makeText(TaskAssignDesc.this,object.getString("status"), Toast.LENGTH_LONG).show();
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TaskAssignDesc.this, error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error", "" + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Customer_id", String.valueOf(customerid));
                params.put("WorkerId", String.valueOf(workerid));
                params.put("Status", "Pending");
                params.put("Description",taskdesc_content.getText().toString());
                return params;
            }

        };

        requestQueue.add(stringRequest);
    }

}
