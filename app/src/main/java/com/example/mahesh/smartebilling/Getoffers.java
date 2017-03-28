package com.example.mahesh.smartebilling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;

public class Getoffers extends AppCompatActivity {
    Button discount;
    WebView adminOffers;
    private static final String URL="http://"+Globals.IP+"/ebilling/adminOffers.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getoffers);

        adminOffers = (WebView) findViewById(R.id.offers);
        adminOffers.getSettings().setJavaScriptEnabled(true);
        adminOffers.loadUrl(URL);

        discount=(Button)findViewById(R.id.discount);
        discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Getoffers.this,Getdiscount.class);
                startActivity(i);
            }
        });
    }
}
