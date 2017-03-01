package com.example.mahesh.smartebilling;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
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
 * Created by MANDEEP on 2/27/2017.
 */
public class FindLocation extends Activity {

    private static final String URL="http://10.0.3.2/ebilling/productsLocationSearch.php";
    WebView searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locate);

        searchResults = (WebView) findViewById(R.id.search);
        searchResults.getSettings().setJavaScriptEnabled(true);
        searchResults.loadUrl(URL);

    }
}
