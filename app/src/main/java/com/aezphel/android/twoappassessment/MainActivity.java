package com.aezphel.android.twoappassessment;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_custom);
        View actionView = getSupportActionBar().getCustomView();
        Button addBtn = actionView.findViewById(R.id.addBtn);
        Button delBtn = actionView.findViewById(R.id.deleteBtn);
        ImageButton prevBtn = (ImageButton)findViewById(R.id.prevBtn);
        ImageButton nextBtn = (ImageButton)findViewById(R.id.nextBtn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Add button clicked");
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Delete button clicked");
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Previous button clicked");
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Next button clicked");
            }
        });
    }
}
