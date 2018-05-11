package com.example.spider.loginscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spider.loginscreen.Adapter.CategoryDropdownAdapter;
import com.example.spider.loginscreen.Adapter.WorkerListAdapter;
import com.example.spider.loginscreen.Model.Category;
import com.example.spider.loginscreen.Model.City;
import com.example.spider.loginscreen.Model.UpdateWorker;
import com.example.spider.loginscreen.Model.WorkerDetail;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spider on 3/26/2017.
 */
public class profile_update extends AppCompatActivity implements View.OnClickListener {
    String Name,Category_Id,Email,Address,Ph_No,Password,Worker_Exprience,Basic_Rate,Pin_Code,City_id;
    int customerid;
    Button btnsubmit;
    SessionManager objSessionManager;
    EditText edtName,edtCategory_Id,edtEmail,edtAddress,edtPh_No,edtPassword,edtexperience,edtbasicrate,edtpincode;
    private List<Category> lstofcategory=new ArrayList<>();
    Activity getcuractivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);
        objSessionManager = new SessionManager(this);
        customerid=objSessionManager.getLoginUSerid();
        Intent myIntent = getIntent();
        mapComponents();
        getWorkerProfilecheck();
       // getworkerProfileupdate();

    }
    private void mapComponents() {
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPh_No = (EditText) findViewById(R.id.edtPh_No);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtexperience = (EditText) findViewById(R.id.edtexperience);
        edtbasicrate = (EditText) findViewById(R.id.edtbasicrate);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
    }
    private void getData() {

        Email = edtEmail.getText().toString();
        Address = edtAddress.getText().toString();
        Ph_No = edtPh_No.getText().toString();
        Password = edtPassword.getText().toString();
        Worker_Exprience=edtexperience.getText().toString();
        Basic_Rate=edtbasicrate.getText().toString();

    }
    private void getWorkerProfilecheck(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String REGISTER_URL = helper.sericeUrl +"/WorkerProfileCheck?Worker_id="+customerid ;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = null;
                            try {
                                jsonArray = object.getJSONArray("lstdata");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for(int i = 0 ; i<jsonArray.length();i++){
                                JSONObject object1 = jsonArray.getJSONObject(i);
                               /* com.example.spider.loginscreen.Model.UpdateWorker UpdateWorker = new
                                        UpdateWorker((Integer) object1.get("Worker_id"),
                                        ((String)object1.get("Ph_No")),
                                        ((String)object1.get("Worker_Exprience")),
                                        ((String)object1.get("Email")),
                                        ((String)object1.get("Password")),
                                        ((String)object1.get("Basic_Rate"))
                                );*/
                                edtEmail.setText((String)object1.get("Email"));
                                edtexperience.setText((String)object1.get("Worker_Exprience"));
                                edtPh_No.setText((String)object1.get("Ph_No"));
                                edtPassword.setText((String)object1.get("Password"));
                                edtbasicrate.setText((String)object1.get("Basic_Rate"));
                                edtAddress.setText((String)object1.get("Customer_Address"));



                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

        };
        requestQueue.add(stringRequest);
    }

    private void getworkerProfileupdate(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String REGISTER_URL = helper.sericeUrl +"WorkerProfileupdate";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(profile_update.this, response, Toast.LENGTH_LONG).show();
                        Log.e("response", "" + response);
                        Intent intent = new Intent(profile_update.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(profile_update.this, error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error", "" + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Customer_Address",Address);
                params.put("Worker_Exprience",Worker_Exprience);
                params.put("Ph_No",Ph_No);
                params.put("Email",Email);
                params.put("Password",Password);
                params.put("Basic_Rate",Basic_Rate);
                //
                params.put("Worker_id", String.valueOf(customerid));
                return params;
            }

        };

        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsubmit:
                getData();
                if (validation()) {
                    getworkerProfileupdate();

                }
        }

    }
    public Boolean validation() {
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";



        if (!Email.matches(emailPattern)) {
            edtEmail.setError("Email id is not valid!");
            edtEmail.requestFocus();
            return false;
        }





        if (Address.isEmpty()) {
            edtAddress.setError("Empty");
            edtAddress.requestFocus();
            return false;
        }


        if (Ph_No.isEmpty()) {
            edtPh_No.setError("Empty");
            edtPh_No.requestFocus();
            return false;
        }

       else {
            return true;
        }

    }
}
