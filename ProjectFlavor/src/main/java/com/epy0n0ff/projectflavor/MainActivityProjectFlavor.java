package com.epy0n0ff.projectflavor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivityProjectFlavor extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_flavor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_project_flavor, menu);
        return true;
    }
    
}
