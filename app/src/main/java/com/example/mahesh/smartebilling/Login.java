package com.example.mahesh.smartebilling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    private RequestQueue requestQueue;
    private static final String URL="http://"+Globals.IP+"/ebilling/login.php";

    private StringRequest request;

    final  String LOG = "Login";
    Button login, sbutton;
    EditText uname,pass;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestQueue= Volley.newRequestQueue(this);

        login=(Button)findViewById(R.id.login);
        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (Integer.parseInt(jsonObject.getString("success")) != -1) {
                                Globals.username = uname.getText().toString();
                                Globals.userid = Integer.parseInt(jsonObject.getString("success"));
                                Globals.purchase_amt = Float.parseFloat(jsonObject.getString("total"));
                                Globals.visits = Integer.parseInt(jsonObject.getString("visits"));

                                JSONArray jsonArray = jsonObject.getJSONArray("rareProductIds");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Globals.rareAndPrevious.add(jsonArray.get(i));
                                }
                                Log.d("yo",Globals.rareAndPrevious.toString());
                                Intent i=new Intent(Login.this,Happyshopping.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "error: "+jsonObject.getString("error"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error_response: " + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashmap = new HashMap<String, String>();
                        hashmap.put("username", uname.getText().toString());
                        hashmap.put("password", pass.getText().toString());
                        return hashmap;
                    }
                };
                requestQueue.add(request);
            }
        });//on click listener for login ends

        //signup button
        sbutton=(Button)findViewById(R.id.sbutton);
        sbutton.setOnClickListener(this);
        sbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            Intent i = new Intent(Login.this, Registration.class);

            startActivity(i);}
        });
   }

    @Override
    public void processFinish(String s) {

    }

    @Override
    public void onClick(View view) {

    }
}