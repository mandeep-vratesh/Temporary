package com.example.mahesh.smartebilling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Happyshopping extends AppCompatActivity {


    Button sbutton, pbutton, vbutton, obutton, lbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happyshopping);

        //previous shopping
        pbutton = (Button) findViewById(R.id.pbutton);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Happyshopping.this, PreviousShopping.class);
                startActivity(i);
            }
        });
        //location search button
        lbutton = (Button) findViewById(R.id.lbutton);
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Happyshopping.this, FindLocation.class);
                startActivity(i);
            }
        });
        //Viewbill button
        vbutton = (Button) findViewById(R.id.vbutton);
        vbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Happyshopping.this, Viewbill.class);
                startActivity(i);
            }
        });

        //Offers button
        obutton = (Button) findViewById(R.id.obutton);
        obutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Happyshopping.this, Getoffers.class);
                startActivity(i);
            }
        });

        //Scan the product button
        sbutton = (Button) findViewById(R.id.sbutton);
        final Activity activity = this;
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setPrompt("Scan");
                integrator.setBeepEnabled(false);
                integrator.setCameraId(1);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.initiateScan();
            }
        });
    }

    List<String> purchase = new ArrayList<String>();
    // ArrayList<String> purchase = new ArrayList<String>();
    String pinfo;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result.getContents() == null) {
            Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();

        } else {
            pinfo = result.getContents();

            Intent intent = new Intent(getBaseContext(), GetCount.class);
            intent.putExtra("id", pinfo);
            startActivity(intent);
        }
    }
}
