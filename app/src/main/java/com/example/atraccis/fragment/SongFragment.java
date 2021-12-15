package com.example.atraccis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.atraccis.R;
import com.example.atraccis.databinding.FragmentSongBinding;


public class SongFragment extends Fragment {
    public FragmentSongBinding binding;
    private View mView;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //onBackPressed();
    }
    public void onBackPressed() {
       // getActivity().moveTaskToBack(true);
        getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (mView == null) {
            mView=(inflater.inflate(R.layout.fragment_song, container, false));
            FragmentSongBinding var4 =  FragmentSongBinding.bind(mView);
            this.binding = var4;
        }
        return binding.getRoot();
    }


    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.init();
    }


    private final void init() {
        String url="https://drive.google.com/file/d/14hpoRnFK1mv2OV0jxCWYMd84PkXI0ZKK/view?usp=drivesdk";
        binding.webview.setWebViewClient(new MyBrowser());
        binding.webview.getSettings().setLoadsImagesAutomatically(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        binding.webview.loadUrl(url);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}

