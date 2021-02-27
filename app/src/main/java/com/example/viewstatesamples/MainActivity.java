package com.example.viewstatesamples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static com.example.viewstatesamples.SamplesActivity.EXTRA_KEY_LAYOUT_RES;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSampleActivity(View view) {
        Intent intent = new Intent(this, SamplesActivity.class);
        int id = view.getId();
        if (id == R.id.button_view_state_sample) {
            intent.putExtra(EXTRA_KEY_LAYOUT_RES, R.layout.view_state_sample);
        } else if (id == R.id.button_compound_view_state_sample) {
            intent.putExtra(EXTRA_KEY_LAYOUT_RES, R.layout.compound_view_state_sample);
        }
        startActivity(intent);
    }
}