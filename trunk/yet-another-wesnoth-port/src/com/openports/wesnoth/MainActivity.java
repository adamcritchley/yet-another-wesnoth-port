package com.openports.wesnoth;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private MediaPlayer mMainPlayer = null;
    private View mTutorialButton = null;
    private View mCampaignButton = null;
    private View mQuitButton = null;

    private View.OnClickListener sTutorialButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
        	mTutorialButton.setBackgroundResource(R.drawable.button_hi_tutorial);
        	mTutorialButton.invalidate();
            Intent myIntent = new Intent( getBaseContext(), GameCanvas.class );
            startActivity(myIntent); 
        }
    };
    
    private View.OnClickListener sCampaignButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
        	mCampaignButton.setBackgroundResource(R.drawable.button_hi_campaign);
        	mCampaignButton.invalidate();
            Intent myIntent = new Intent( getBaseContext(), GameCanvas.class );
            startActivity(myIntent);
        }
    };

    private View.OnClickListener sQuitButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
        	mQuitButton.setBackgroundResource(R.drawable.button_hi_quit);
        	mQuitButton.invalidate();
            finish();
        }
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTutorialButton = findViewById(R.id.tutorialButton);
        mCampaignButton = findViewById(R.id.campaignButton);
        mQuitButton = findViewById(R.id.quitButton);
        
        if (mTutorialButton != null) {
        	mTutorialButton.setOnClickListener(sTutorialButtonListener);
        }
        
        if (mCampaignButton != null) {
        	mCampaignButton.setOnClickListener(sCampaignButtonListener);
        }

        if (mQuitButton != null) {
        	mQuitButton.setOnClickListener(sQuitButtonListener);
        }
        
        mMainPlayer = MediaPlayer.create(this, R.raw.main_menu);
        mMainPlayer.setLooping(true);
        mMainPlayer.start();
    }
 
    @Override
    public void onDestroy() {
    	mMainPlayer.stop();
    	mMainPlayer.release();
    	mMainPlayer = null;
        super.onDestroy();
    }

    @Override
    public void onResume(){
        super.onResume();
        if( mMainPlayer != null )
        {
        	mMainPlayer.start();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if( mMainPlayer != null )
        {
        	mMainPlayer.pause();
        }
    }
}