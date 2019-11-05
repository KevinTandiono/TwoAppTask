package com.aezphel.android.twoappassessment;

import java.util.ArrayList;

public class Singleton {
    private static Singleton instance = null;
    private ArrayList<PageInformation> pageArray;

    private Singleton(){
        pageArray = new ArrayList<PageInformation>();
        for(int i = 0; i < 3; i++){
            pageArray.add(new PageInformation());
        }
    }

    public static Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }

    public ArrayList<PageInformation> getPageArray(){
        return pageArray;
    }

    public void addPage(int position){
        pageArray.add(position, new PageInformation());
    }

    public void removePage(int position){
        pageArray.remove(position);
    }
}
