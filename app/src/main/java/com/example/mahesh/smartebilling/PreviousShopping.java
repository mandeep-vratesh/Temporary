package com.example.mahesh.smartebilling;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by MANDEEP on 2/27/2017.
 */
public class PreviousShopping extends Activity{

    WebView previous;
    private static final String URL="http://"+Globals.IP+"/ebilling/previousShopping.php?id="+Globals.userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);

        previous = (WebView) findViewById(R.id.previous);
        previous.getSettings().setJavaScriptEnabled(true);
        previous.loadUrl(URL);
    }
}
