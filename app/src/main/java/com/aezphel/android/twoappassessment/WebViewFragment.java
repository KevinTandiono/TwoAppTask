package com.aezphel.android.twoappassessment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebViewFragment extends Fragment {
    private char letter;
    private String color;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        letter = getArguments().getChar("letter");
        color = getArguments().getString("color");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web_view, container, false);
        WebView webview = v.findViewById(R.id.webView);
        String html = "<html><head>"
                + "<style type=\"text/css\">body{color:" + color + ";}"
                + "</style></head>"
                + "<body>"
                + letter
                + "</body></html>";
        webview.loadData(html, "text/html", "UTF-8");
        return v;
    }

    public static WebViewFragment newInstance(char c, String col) {
        Bundle args = new Bundle();
        WebViewFragment fragment = new WebViewFragment();
        args.putChar("letter", c);
        args.putString("color", col);
        fragment.setArguments(args);
        return fragment;
    }
}
