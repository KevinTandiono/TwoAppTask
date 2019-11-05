package com.aezphel.android.twoappassessment;

import java.util.Random;

public class PageInformation {
    private char letter;
    private String txtColor;

    public PageInformation(){
        Random rand = new Random();
        letter = (char)(rand.nextInt(26)+'A');
        txtColor = String.format("#%06x", rand.nextInt(0xffffff + 1));
    }

    public char getLetter() {
        return letter;
    }

    public String getTxtColor() {
        return txtColor;
    }
}
