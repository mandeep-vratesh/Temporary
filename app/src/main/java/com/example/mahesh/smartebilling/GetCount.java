package com.example.mahesh.smartebilling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MANDEEP on 3/1/2017.
 */

public class GetCount extends Activity {
    private RequestQueue requestQueue;
    private static final String URL = "http://10.0.3.2/ebilling/test.php";
    private StringRequest request;
    String pinfo;
    EditText count;
    String _count;
    Button plus, minus, go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getcount);
        requestQueue = Volley.newRequestQueue(this);

        count = (EditText) findViewById(R.id.count);
        //get pinfo
        pinfo = getIntent().getStringExtra("id");

        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count.setText(Integer.toString(Integer.parseInt(count.getText().toString())+1));
            }
        });
        minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(count.getText().toString()) > 0){
                    count.setText(Integer.toString(Integer.parseInt(count.getText().toString())-1));
                }
            }
        });

        go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!product_in_cart(pinfo)) {
                    //yaha se
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);


                                HashMap item = new HashMap();
                                item.put("product_id", pinfo);
                                item.put("product_name", jsonObject.getString("product_name"));
                                item.put("product_price", jsonObject.getString("product_price"));
                                item.put("product_weight", jsonObject.getString("product_weight"));
                                item.put("product_count", count.getText().toString());
                                //add this item to the arraylist
                                Globals.cart.add(item);
                                Intent i = new Intent(GetCount.this, Happyshopping.class);
                                startActivity(i);
                            } catch (Exception e) {
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
                            hashmap.put("productid", pinfo);
                            return hashmap;
                        }
                    };
                    requestQueue.add(request);
                }
            }
        });
    }
    private boolean product_in_cart(String id) {
        for (HashMap<String, String> hashMap : Globals.cart)
        {
            if(hashMap.get("product_id").equals(id)){
                hashMap.put("product_count",Integer.toString(Integer.parseInt(hashMap.get("product_count"))+Integer.parseInt(count.getText().toString())));
                Intent i = new Intent(GetCount.this, Happyshopping.class);
                startActivity(i);
                return true;
            }
        }
        return false;
    }
}
