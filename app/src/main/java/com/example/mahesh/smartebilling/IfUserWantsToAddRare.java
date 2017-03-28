package com.example.mahesh.smartebilling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by MANDEEP on 3/27/2017.
 */
public class IfUserWantsToAddRare extends Activity{
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrareitem);

        id = getIntent().getStringExtra("id");

        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GetCount.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Happyshopping.class);
                startActivity(intent);
            }
        });
    }
}
