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
import com.example.spider.loginscreen.Adapter.CityDropDownAdapter;
import com.example.spider.loginscreen.Model.City;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Custromer_Registration extends AppCompatActivity implements View.OnClickListener{


    String Ph_No,Customer_Name,Email,Password,Customer_Address,city_id;
    Button btnsubmit;


    EditText edtPh_No, edtCustomer_Name, edtEmail, edtPassword,edtCustomer_Address;
    Spinner spinercity;
    Activity getcuractivity;
    private List<com.example.spider.loginscreen.Model.City> lstofcity = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getcuractivity = this;
        setContentView(R.layout.activity_custromer__registration);
        mapComponents();
        getcityList();
    }

    private void mapComponents() {
        edtPh_No = (EditText) findViewById(R.id.edtPh_No);
        edtCustomer_Name = (EditText) findViewById(R.id.edtCustomer_Name);
        edtCustomer_Address = (EditText) findViewById(R.id.edtCustomer_Address);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        spinercity= (Spinner) findViewById(R.id.spinercity);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
    }

        private void getData() {
            Ph_No= edtPh_No.getText().toString();
            Customer_Name = edtCustomer_Name.getText().toString();
            Customer_Address = edtCustomer_Address.getText().toString();
            Email = edtEmail.getText().toString();
            Password = edtPassword.getText().toString();
            city_id= String.valueOf(((City)spinercity.getSelectedItem()).CityId);

        }




    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnsubmit:
                getData();

                if (validation()) {
                    registerUser();
                }
        }
    }

    public Boolean validation() {
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (Ph_No.isEmpty()) {
            edtPh_No.setError("Empty");
            edtPh_No.requestFocus();
            return false;
        }
        if (!Email.matches(emailPattern)) {
            edtEmail.setError("Email id is not valid!");
            edtEmail.requestFocus();
            return false;
        }
        if (Customer_Address.isEmpty()) {
            edtCustomer_Address.setError("Empty");
            edtCustomer_Address.requestFocus();
            return false;
        }
        if (Customer_Name.isEmpty()) {
            edtCustomer_Name.setError("Empty");
            edtCustomer_Name.requestFocus();
            return false;
        }
        if (Password.isEmpty()) {
            edtPassword.setError("Empty");
            edtPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    private void registerUser() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String REGISTER_URL = helper.sericeUrl +"webapi/CustomerRegistration";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Custromer_Registration.this, response, Toast.LENGTH_LONG).show();
                        Log.e("response", "" + response);
                        Intent intent = new Intent(Custromer_Registration.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Custromer_Registration.this, error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error", "" + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Ph_no",Ph_No);
                params.put("Customer_Name",Customer_Name);
                params.put("Customer_Address",Customer_Address);
                params.put("Email",Email);
                params.put("Password",Password);
                params.put("city_id",city_id);

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

}


