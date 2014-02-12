package com.epy0n0ff.staticlayout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class StaticLayoutMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_static_layout);

        final TextPaint textPaint = new TextPaint(  );
        textPaint.setColor( Color.RED );
        textPaint.setTextSize( 200 );
        final StaticLayout staticLayout = new StaticLayout( "StaticLayout", textPaint, 1000, Layout.Alignment.ALIGN_NORMAL, 10.0f, 10.0f, true);


        View view = new View(this) {
            @Override
            protected void onDraw( Canvas canvas ) {
                super.onDraw( canvas );

                staticLayout.draw( canvas );
                canvas.restore();
            }
        };

        setContentView( view );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.static_layout_main, menu);
        return true;
    }
    
}
