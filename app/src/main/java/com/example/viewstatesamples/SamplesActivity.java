package com.example.viewstatesamples;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SamplesActivity extends AppCompatActivity {
    static final String EXTRA_KEY_LAYOUT_RES = "layout_res";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutRes = getIntent().getIntExtra(EXTRA_KEY_LAYOUT_RES, -1);
        if (layoutRes != -1) {
            setContentView(layoutRes);
        } else {
            setContentView(R.layout.activity_samples);
        }
    }
}
