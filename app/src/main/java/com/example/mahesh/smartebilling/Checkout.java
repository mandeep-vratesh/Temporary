package com.example.mahesh.smartebilling;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MANDEEP on 2/27/2017.
 */
public class Checkout extends Activity {

    private RequestQueue requestQueue;
    private static final String URL="http://"+Globals.IP+"/ebilling/addToPurchases.php";
    private StringRequest request;

    Button pay;
    TextView total, discount, payable, billid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        requestQueue= Volley.newRequestQueue(this);
        pay = (Button) findViewById(R.id.pay);

        billid = (TextView) findViewById(R.id.billid);
        String bill = Globals.generateBillId();
        Globals.billid = bill;
        billid.setText(bill);

        total = (TextView) findViewById(R.id.total);
        float total_in_cart = Globals.getTotalPriceOnCart();
        total.setText(Float.toString(total_in_cart));

        discount = (TextView) findViewById(R.id.discount);
        float total_discount = total_in_cart * Globals.getDiscount()/100;
        discount.setText("-"+Float.toString(total_discount));

        payable = (TextView) findViewById(R.id.totalToPay);
        float pay_after_discount = total_in_cart - total_discount;
        payable.setText(Float.toString(pay_after_discount));

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("==============>",response+" "+Arrays.toString(Globals.getProductsID())+" "+Globals.getCounts());

                        if(response.contains("success")){

                            //add to purchase amounts
                            Globals.purchase_amt += Globals.getTotalPriceOnCart();
                            //add to visits
                            Globals.visits += 1;
                            //empty the cart
                            Globals.cart.clear();

                            Intent i=new Intent(Checkout.this,ShopAgain.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(), "error_response: " + response, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error_response: " + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map getParams() throws AuthFailureError {
                        HashMap hashmap = new HashMap();
                        hashmap.put("billid", Globals.billid);
                        hashmap.put("cid", Integer.toString(Globals.userid));
                        hashmap.put("pid", Arrays.toString(Globals.getProductsID()));
                        hashmap.put("counts", Globals.getCounts().toString());
                        return hashmap;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}
