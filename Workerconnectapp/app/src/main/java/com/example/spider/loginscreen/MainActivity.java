package com.example.spider.loginscreen;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spider.loginscreen.common.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private String USERNAME;
    private String PASSWORD;
    private TextView gettext,textCustomer,textWorker,txtforgotpassword;
    private Button btnsubmit;
    SessionManager objSessionManager;
    private static CheckBox show_hide_password;
    private static EditText loginuserpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objSessionManager = new SessionManager(this);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        textCustomer = (TextView) findViewById(R.id.textCustomer);
        textCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Custromer_Registration.class);
                startActivity(intent);
            }
        });
        txtforgotpassword = (TextView) findViewById(R.id.txtforgotpassword);
        txtforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Forgot_Password.class);
                startActivity(intent);
            }
        });
        textWorker = (TextView) findViewById(R.id.textWorker);
        textWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Worker_Registration.class);
                startActivity(intent);
            }
        });
        btnsubmit.setOnClickListener(this);
            }


    public void onClick(View v) {
        gettext=(TextView) findViewById(R.id.loginuserpassword);
        USERNAME= String.valueOf(((TextView) findViewById(R.id.loginusername)).getText());
       PASSWORD= String.valueOf(gettext.getText());


        loginuserpassword =(EditText) findViewById(R.id.loginuserpassword);

            show_hide_password = (CheckBox) findViewById(R.id.show_hide_password);

            show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // checkbox status is changed from uncheck to checked.
                    if (!isChecked) {
                        // show password
                        loginuserpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        // hide password
                        loginuserpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }

                }
            });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String REGISTER_URL = helper.sericeUrl +"webapi/loginservice";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("lstdata");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object1 = jsonArray.getJSONObject(i);
                                if ((Integer) object1.get("Customer_id") > 0) {

                                    objSessionManager.CreateUserSessionData((Integer) object1.get("Customer_id"),
                                            (Integer) object1.get("UserType"),
                                            (String) object1.get("Email")

                                    );

                                        Intent myIntent = new Intent(MainActivity.this, Dashboard.class);
                                        myIntent.putExtra("email",USERNAME);
                                        startActivity(myIntent);
                                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        // Add new Flag to start new Activity
                                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        finish();

                                } else {
                                    Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_LONG).show();
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error", "" + error);
                       // Intent intent = new Intent(MainActivity.this,Custromer_Registration.class);
                       // startActivity(intent);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Email",USERNAME);
                params.put("Password",PASSWORD);

                return params;
            }

        };
        requestQueue.add(stringRequest);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dashboard_drawer,menu);
        return true;
    }
}
