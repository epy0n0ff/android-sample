package com.epy0n0ff.staticlayout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StaticLayoutMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_static_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.static_layout_main, menu);
        return true;
    }
    
}
