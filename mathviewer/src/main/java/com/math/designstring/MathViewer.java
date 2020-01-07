package com.math.designstring;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.math.designstring.mathviewer.R;
import com.x5.template.Chunk;
import com.x5.template.Theme;
import com.x5.template.providers.AndroidTemplates;

public class MathViewer extends WebView {
    private String mText="",mConfig="";

    public MathViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        setBackgroundColor(Color.TRANSPARENT);

        TypedArray mTypeArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MathViewer,
                0,
                0
        );

        try { // the order of execution of setEngine() and setText() matters
            setText(mTypeArray.getString(R.styleable.MathViewer_text));
        } finally {
            mTypeArray.recycle();
        }
    }

    // disable touch event on MathViewer
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    private Chunk getChunk() {
        AndroidTemplates loader = new AndroidTemplates(getContext());
        return new Theme(loader).makeChunk("mathjax");
    }

    public void setText(String text) {
        if(text!=null) {
        }
        mText = text;
        Chunk chunk = getChunk();

        mConfig="MathJax.Hub.Config({\n"+
                "  CommonHTML: { linebreaks: { automatic: true } },\n"+
                "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n"+
                "         SVG: { linebreaks: { automatic: true } }\n"+
                "});";

        String TAG_FORMULA = "formula";
        String TAG_CONFIG = "config";
        chunk.set(TAG_FORMULA, mText);
        chunk.set(TAG_CONFIG, mConfig);
        this.loadDataWithBaseURL(null, chunk.toString(), "text/html", "utf-8", "about:blank");
    }

    public String getText() {
        return mText;
    }



}