package com.aezphel.android.twoappassessment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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
    private Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        single = Singleton.getInstance();
        viewPager = findViewById(R.id.viewPager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        addBtn = findViewById(R.id.addBtn);
        delBtn = findViewById(R.id.deleteBtn);
        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);
        validateButtons();
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Add button clicked");
                single.addPage(viewPager.getCurrentItem()+1);
                adapter.notifyDataSetChanged();
                Log.d(TAG,"position: " + viewPager.getCurrentItem());
                if(single.getPageArray().size() == 1) {
                    viewPager.setCurrentItem(0);
                }
                else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                validateButtons();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Delete button clicked");
                if(single.getPageArray().size() > 0){
                    single.removePage(viewPager.getCurrentItem());
                    adapter.notifyDataSetChanged();
                    validateButtons();
                    if(single.getPageArray().size() > 0)
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                    else
                        viewPager.setCurrentItem(0);
                } else
                    Toast.makeText(getApplicationContext(),R.string.del_feedback, Toast.LENGTH_SHORT).show();

            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Previous button clicked");
                if(viewPager.getCurrentItem() == 0 || single.getPageArray().size() == 0){
                    validateButtons();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    validateButtons();
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Next button clicked");
                if(viewPager.getCurrentItem() == single.getPageArray().size()-1 || single.getPageArray().size() == 0){
                    validateButtons();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    validateButtons();
                }
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

    private void validateButtons(){
        if(single.getPageArray().size() == 0 || single.getPageArray().size() == 1){
            prevBtn.setEnabled(false);
            prevBtn.setAlpha(0.5f);
            nextBtn.setEnabled(false);
            nextBtn.setAlpha(0.5f);
        } else if(single.getPageArray().size() > 1){
            if(viewPager.getCurrentItem() == single.getPageArray().size() - 1){
                nextBtn.setEnabled(false);
                nextBtn.setAlpha(0.5f);
                prevBtn.setEnabled(true);
                prevBtn.setAlpha(1.0f);
            } else if(viewPager.getCurrentItem() < 1){
                prevBtn.setEnabled(false);
                prevBtn.setAlpha(0.5f);
                nextBtn.setEnabled(true);
                nextBtn.setAlpha(1.0f);
            } else {
                prevBtn.setEnabled(true);
                prevBtn.setAlpha(1.0f);
                nextBtn.setEnabled(true);
                nextBtn.setAlpha(1.0f);
            }
        }
    }
}
