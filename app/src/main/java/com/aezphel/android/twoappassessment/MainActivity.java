package com.aezphel.android.twoappassessment;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private Button addBtn;
    private Button delBtn;
    private ImageButton prevBtn;
    private ImageButton nextBtn;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private ArrayList<WebViewFragment> fragmentList = new ArrayList<>();
    private ArrayList<PageInformation> pages = new ArrayList<>();
    private Singleton single;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_custom);
        View actionView = getSupportActionBar().getCustomView();
        single = Singleton.getInstance();
        viewPager = findViewById(R.id.viewPager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        addBtn = actionView.findViewById(R.id.addBtn);
        delBtn = actionView.findViewById(R.id.deleteBtn);

        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Add button clicked");
                int pos = viewPager.getCurrentItem();
                Log.d(TAG, "ViewPager Pos: " + pos);
                single.addPage(pos+1);
                Log.d(TAG, "ViewPager: Added ");
                adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(pos+1);
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

    private class FragmentAdapter extends FragmentStatePagerAdapter{

        public FragmentAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return WebViewFragment.newInstance(Singleton.getInstance().getPageArray().get(i).getLetter(),
                    Singleton.getInstance().getPageArray().get(i).getTxtColor());
        }

        @Override
        public int getCount() {
            return Singleton.getInstance().getPageArray().size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
            //return super.getItemPosition(object);
        }
    }
}
