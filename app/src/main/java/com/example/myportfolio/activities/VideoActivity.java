package com.example.myportfolio.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.myportfolio.R;
import com.example.myportfolio.adapters.YoutubeVideoAdapter;
import com.example.myportfolio.frag.VideoNewsFragment;
import com.example.myportfolio.listener.Constants;
import com.example.myportfolio.listener.RecyclerTouchListener;
import com.example.myportfolio.models.YoutubeVideoModel;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class VideoActivity extends YouTubeBaseActivity {
    private static final String TAG = VideoActivity.class.getSimpleName();
    private String videoID;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);



        //get the video id
        videoID = getIntent().getStringExtra("video_id");
        Log.i("idm", videoID);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYoutubePlayer();
    }

    /**
     * initialize the youtube player
     */
    private void initializeYoutubePlayer() {
        youTubePlayerView.initialize(Constants.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                boolean wasRestored) {

                if (!wasRestored) {
                    //set the player style here: like CHROMELESS, MINIMAL, DEFAULT
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    //load the video
//                    youTubePlayer.loadVideo(videoID);

                    //OR

                    //cue the video
                    youTubePlayer.cueVideo(videoID);

                    //if you want when activity start it should be in full screen uncomment below comment
//                    youTubePlayer.setFullscreen(true);

                    //If you want the video should play automatically then uncomment below comment
                    youTubePlayer.play();

                    //If you want to control the full screen event you can uncomment the below code
                    //Tell the player you want to control the fullscreen change
                   /*player.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
                    //Tell the player how to control the change
                    player.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean arg0) {
                            // do full screen stuff here, or don't.
                            Log.e(TAG,"Full screen mode");
                        }
                    });*/

                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                //print or show error if initialization failed
                Log.e(TAG, "Youtube Player View initialization failed");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("video_back",1);
        startActivity(intent);
        finish();

    }
}