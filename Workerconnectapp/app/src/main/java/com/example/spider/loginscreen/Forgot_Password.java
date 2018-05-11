package com.example.spider.loginscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.example.spider.loginscreen.common.helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by spider on 3/7/2017.
 */
public class Forgot_Password extends AppCompatActivity implements View.OnClickListener {

    EditText edtfgtEmail;
    Button btnfgtsubmit;
    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);

        edtfgtEmail = (EditText) findViewById(R.id.edtfgtEmail);
        btnfgtsubmit = (Button) findViewById(R.id.btnfgtsubmit);
        btnfgtsubmit.setOnClickListener(this);
    }

    private void getData() {
        strEmail = edtfgtEmail.getText().toString();
    }


    @Override
    public void onClick(View v) {
        getData();
        forgotpwd();

    }
    private void forgotpwd() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String REGISTER_URL = helper.sericeUrl + "/ForgotPassword";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Toast.makeText(Forgot_Password.this,object.getString("status"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", "" + error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("UserName", strEmail);
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
