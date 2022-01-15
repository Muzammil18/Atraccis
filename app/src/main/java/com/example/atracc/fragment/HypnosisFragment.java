package com.example.atracc.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.example.atracc.R;
import com.example.atracc.databinding.FragmentHypnosisBinding;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class HypnosisFragment extends Fragment {
    public FragmentHypnosisBinding binding;
    private View view;
    FirebaseDatabase firebaseDatabase;
    SharedPreferences sharedpreferences;


    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //onBackPressed();
    }
    public void onBackPressed() {
       // getActivity().moveTaskToBack(true);
        getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            view=(inflater.inflate(R.layout.fragment_hypnosis, container, false));
        return view;
    }


    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpreferences = requireContext().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        this.init();
    }

    private final void init() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        if(sharedpreferences.getBoolean("inserthyponosis",false)){
            insertHyponsis();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("inserthyponosis",false);
            editor.commit();

        }
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("url");
        initializeExoplayerView("https://firebasestorage.googleapis.com/v0/b/atraccis-d69de.appspot.com/o/hipnosis%2Fhipnosis.mp4?alt=media&token=f41e3f9f-e43e-41a9-ad59-3c0da39b5a97");
    }

    private void initializeExoplayerView(String videoURL) {
        try {
            // bandwidthmeter is used for getting default bandwidth
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            // track selector is used to navigate between video using a default seeker.
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

            // we are adding our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

            // we are parsing a video url and
            // parsing its video uri.
            Uri videouri = Uri.parse(videoURL);

            // we are creating a variable for data source
            // factory and setting its user agent as 'exoplayer_view'
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");

            // we are creating a variable for extractor
            // factory and setting it to default extractor factory.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            // we are creating a media source with above variables
            // and passing our event handler as null,
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);

            // inside our exoplayer view
            // we are setting our player
            view.<SimpleExoPlayerView>findViewById(R.id.idExoPlayerView).setPlayer(exoPlayer);

            // we are preparing our exoplayer
            // with media source.
            exoPlayer.prepare(mediaSource);

            // we are setting our exoplayer
            // when it is ready.
            exoPlayer.setPlayWhenReady(true);
        } catch (Exception e) {
            // below line is used for handling our errors.
            Log.e("TAG", "Error : " + e.toString());
        }
    }
    void insertHyponsis(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",sharedpreferences.getString("email",""));
        map.put("playHyponsis","Played");
        map.put("date",sdf.format(new Date()));
        FirebaseDatabase.getInstance().getReference().child("Hyponsis_Data").push()
                .setValue(map);
    }
}

