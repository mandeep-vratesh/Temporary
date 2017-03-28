package com.example.mahesh.smartebilling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Getdiscount extends AppCompatActivity implements AsyncResponse, View.OnClickListener  {

    private RequestQueue requestQueue;
    private static final String URL_get_visit_and_purchase="http://"+Globals.IP+"/ebilling/getTotalPurchaseAndVisits.php";
    private StringRequest request;

    WebView thisMonth;
    private static final String URL="http://"+Globals.IP+"/ebilling/previousMonthShopping.php?id="+Globals.userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdiscount);
        requestQueue= Volley.newRequestQueue(this);

        final TextView totalPurchase = (TextView) findViewById(R.id.totalPurchase);
        final TextView totalVisit = (TextView) findViewById(R.id.visits);

        thisMonth = (WebView) findViewById(R.id.previousMonth);
        thisMonth.getSettings().setJavaScriptEnabled(true);
        thisMonth.loadUrl(URL);

        //===========================================

        request = new StringRequest(Request.Method.POST, URL_get_visit_and_purchase, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    totalPurchase.setText(jsonObject.getString("total"));
                    totalVisit.setText(jsonObject.getString("visits"));
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
                HashMap<String, String> hashmap = new HashMap<>();
                hashmap.put("id", Integer.toString(Globals.userid));
                return hashmap;
            }
        };
        requestQueue.add(request);

        //===========================================
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void processFinish(String s) {

    }
}