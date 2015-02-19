package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import ru.studiomobile.tools.gif.Encoder;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        int[] list = new int[] {
                R.drawable.target1,
                R.drawable.target2,
                R.drawable.target3,
                R.drawable.target4,
                R.drawable.target5
        };

        Encoder encoder = new Encoder();
        encoder.encode(this, list, 500, "/sdcard/sample2.gif");

    }
}
