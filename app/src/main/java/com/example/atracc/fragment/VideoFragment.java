package com.example.atracc.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.atracc.R;
import com.example.atracc.databinding.FragmentVideoBinding;
import com.example.atracc.helpers.Links;
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


public class VideoFragment extends Fragment {
    public FragmentVideoBinding binding;
    private View mView;
    FirebaseDatabase firebaseDatabase;
    SharedPreferences sharedpreferences;
    Links links;
    View view;
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
//        if (mView == null) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        //         mView=(inflater.inflate(R.layout.fragment_video, container, false));
//            FragmentVideoBinding var4 =  FragmentVideoBinding.bind(mView);
//
//            this.binding = var4;
//        }
        return view;
    }


    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpreferences = requireContext().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
       this.init();
    }

    private final void init() {
        links=new Links();
//        Toast.makeText(requireContext(), sharedpreferences.getInt("counter",0)+"thr", Toast.LENGTH_SHORT).show();
        firebaseDatabase = FirebaseDatabase.getInstance();
        if(sharedpreferences.getBoolean("insertvideo",false)){
            insertVideo();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("insertvideo",false);
            editor.commit();

        }
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("url");
        initializeExoplayerView(links.getvideo(sharedpreferences.getInt("counter",0)));  }

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

    void insertVideo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        HashMap<String, Object> map = new HashMap<>();
        map.put("email",sharedpreferences.getString("email",""));
        map.put("playVideo","Played");
        map.put("date",sdf.format(new Date()));
        FirebaseDatabase.getInstance().getReference().child("Videos_Data").push()
                .setValue(map);
    }
    @Override
    public void onPause() {
        super.onPause();
        exoPlayer.stop();
    }
}

