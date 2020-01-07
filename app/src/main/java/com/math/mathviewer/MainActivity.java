package com.math.mathviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.math.designstring.MathViewer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String tex = "This come from string. You can insert inline formula:" +
                " \\(ax^2 + bx + c = 0\\) " +
                "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$";
        MathViewer mathview= findViewById(R.id.math);
        mathview.setText(tex);
    }
}
