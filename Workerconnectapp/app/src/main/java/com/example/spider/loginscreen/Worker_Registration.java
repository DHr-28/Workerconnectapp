package com.example.spider.loginscreen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.spider.loginscreen.Adapter.CityDropDownAdapter;
import com.example.spider.loginscreen.Model.Category;
import com.example.spider.loginscreen.Model.City;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worker_Registration extends AppCompatActivity implements View.OnClickListener{

    String Name,Category_Id,Email,Address,Ph_No,Password,Worker_Exprience,Basic_Rate,Pin_Code,City_id,Category_id;
    Button btnsubmit;

    EditText edtName,edtCategory_Id,edtEmail,edtAddress,edtPh_No,edtPassword,edtexperience,edtbasicrate,edtpincode;
    Spinner spinercity,spinercategory_Id;
    Activity getcuractivity;
    private List<City> lstofcity = new ArrayList<>();
    private List<Category> lstofcategory=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getcuractivity = this;
        setContentView(R.layout.activity_worker__registration);
        mapComponents();
        getcityList();
        getWorkercategory();

    }



    private void mapComponents() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        edtPh_No = (EditText) findViewById(R.id.edtPh_No);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtexperience = (EditText) findViewById(R.id.edtexperience);
        edtbasicrate = (EditText) findViewById(R.id.edtbasicrate);
        edtpincode = (EditText) findViewById(R.id.edtpincode);
        spinercity= (Spinner) findViewById(R.id.spinercity);
        spinercategory_Id= (Spinner) findViewById(R.id.spinercategory_Id);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
    }
    private void getData() {
        Name= edtName.getText().toString();

        Email = edtEmail.getText().toString();
        Address = edtAddress.getText().toString();
        Ph_No = edtPh_No.getText().toString();
        Password = edtPassword.getText().toString();
        Worker_Exprience=edtexperience.getText().toString();
        Basic_Rate=edtbasicrate.getText().toString();
        Pin_Code=edtpincode.getText().toString();
        City_id= String.valueOf(((City)spinercity.getSelectedItem()).CityId);
        Category_id= String.valueOf(((Category)spinercategory_Id.getSelectedItem()).Id);
    }
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnsubmit:
                getData();
                if (validation()) {
                    registerWorker();

                }
        }
    }

        public Boolean validation() {
            String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            if (Name.isEmpty()) {
                edtName.setError("Empty");
                edtName.requestFocus();
                return false;
            }

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

            if (Pin_Code.isEmpty()) {
                edtpincode.setError("Empty");
                edtpincode.requestFocus();
                return false;
            } else {
                return true;
            }

        }
    private void registerWorker() {

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String REGISTER_URL = helper.sericeUrl +"/WorkerRegistration";


            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Worker_Registration.this, response, Toast.LENGTH_LONG).show();
                            Log.e("response", "" + response);
                            Intent intent = new Intent(Worker_Registration.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Worker_Registration.this, error.toString(), Toast.LENGTH_LONG).show();
                            Log.e("error", "" + error);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Name", Name);
                    params.put("Email", Email);
                    params.put("Address", Address);
                    params.put("Ph_No", Ph_No);
                    params.put("Password", Password);
                    params.put("Worker_Exprience", Worker_Exprience);
                    params.put("Basic_Rate", Basic_Rate);
                    params.put("Worker_Pinncode", Pin_Code);
                    params.put("city_id",City_id);
                    params.put("Category_id",Category_id);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    public void getcityList() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String REGISTER_URL = helper.sericeUrl +"/CitySelectAll";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
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
                                com.example.spider.loginscreen.Model.City objcity = new
                                        City((Integer) object1.get("Id"),
                                        ((String)object1.get("Worker_Category"))
                                );
                                lstofcity.add(objcity);
                            }

                            CityDropDownAdapter adapter = new CityDropDownAdapter(LayoutInflater.from(getcuractivity),
                                    getcuractivity , lstofcity);
                            spinercity.setAdapter(adapter);
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
    public void getWorkercategory() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String REGISTER_URL = helper.sericeUrl +"/WorkerCategorySelectAll";

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
                                com.example.spider.loginscreen.Model.Category objcategory = new
                                        Category((Integer) object1.get("Id"),
                                        ((String)object1.get("Worker_Category"))
                                );
                                lstofcategory.add(objcategory);
                            }

                            CategoryDropdownAdapter adapter = new CategoryDropdownAdapter(LayoutInflater.from(getcuractivity),
                                    getcuractivity , lstofcategory);
                            spinercategory_Id.setAdapter(adapter);
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
    }


