package com.example.mahesh.smartebilling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    final  String LOG = "Registration";
    EditText name,phno,email,pass,uname, cpass;
    Button regsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regsubmit=(Button)findViewById(R.id.regsubmit);
        regsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=(EditText) findViewById(R.id.name);
                phno=(EditText) findViewById(R.id.phno);
                email=(EditText) findViewById(R.id.email);
                pass=(EditText) findViewById(R.id.pass);
                cpass=(EditText) findViewById(R.id.cpass);
                uname=(EditText) findViewById(R.id.uname);

                HashMap postData=new HashMap();

                //postData.put("login","login");
                postData.put("mobile","android");

                postData.put("name", name.getText().toString());
                postData.put("phno", phno.getText().toString());
                postData.put("email", email.getText().toString());
                postData.put("pass", pass.getText().toString());
                postData.put("cpass", cpass.getText().toString());
                postData.put("uname", uname.getText().toString());

                PostResponseAsyncTask task=new PostResponseAsyncTask(Registration.this, postData, new AsyncResponse() {

                    @Override
                    public void processFinish(String result) {

                        Log.d(LOG,result);

                        if(result.contains("success")){
                            Toast.makeText(Registration.this,"Successfully Registered", Toast.LENGTH_LONG).show();
                            Intent i=new Intent(Registration.this,Login.class);
                            startActivity(i);

                        }
                        else{
                            Toast.makeText(Registration.this,"Enter password correctly", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                task.execute("http://"+Globals.IP+"/ebilling/Registration.php") ;

                  }
        });


    }
}
