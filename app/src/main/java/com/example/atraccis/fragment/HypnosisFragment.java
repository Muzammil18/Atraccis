package com.example.atraccis.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.example.atraccis.R;
import com.example.atraccis.databinding.FragmentHypnosisBinding;

public class HypnosisFragment extends Fragment {
    public FragmentHypnosisBinding binding;
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
            mView=(inflater.inflate(R.layout.fragment_hypnosis, container, false));
            FragmentHypnosisBinding var4 =  FragmentHypnosisBinding.bind(mView);
            var4.setLifecycleOwner((LifecycleOwner)this);
            this.binding = var4;
        }
        return binding.getRoot();
    }


    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.init();
    }


    private final void init() {
    }
}

