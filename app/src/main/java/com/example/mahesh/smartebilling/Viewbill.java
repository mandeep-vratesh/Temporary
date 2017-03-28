package com.example.mahesh.smartebilling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Viewbill extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    TextView totaloncart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewthebill);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        totaloncart = (TextView) findViewById(R.id.totaloncart);
        totaloncart.setText(Integer.toString(Globals.getTotalPriceOnCart()));

        adapter = new RecyclerAdapter(totaloncart);
        recyclerView.setAdapter(adapter);

        TextView logged_in_user = (TextView) findViewById(R.id.customerName);
        logged_in_user.setText(Globals.username);

        Button checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Globals.cart.size() > 0) {
                    Intent i = new Intent(Viewbill.this, Checkout.class);
                    startActivity(i);
                } else{
                    Toast.makeText(getApplicationContext(),"You have nothing on your cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
