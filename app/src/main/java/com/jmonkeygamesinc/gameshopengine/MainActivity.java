package com.jmonkeygamesinc.gameshopengine;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Toast;

import com.jme3.app.LegacyApplication;
import com.jme3.system.AppSettings;
import com.jme3.view.surfaceview.JmeSurfaceView;
import com.jme3.view.surfaceview.OnExceptionThrown;
import com.jme3.view.surfaceview.OnRendererCompleted;
import com.jmonkeygamesinc.gameshopengine.opengl.MyGLSurfaceView;

/**
 * <b>NB: Please Open this example <u>root module</u> using Android Studio; because android build scripts are different from java builds.</b>
 * <br/>
 * An Android Example that demonstrates : How to use a simple game#{@link MyGame}
 * on #{@link com.jme3.view.surfaceview.JmeSurfaceView} inside an #{@link androidx.appcompat.app.AppCompatActivity}.
 * <br>
 * <b>Note : use #{@link AppCompatActivity#setRequestedOrientation(int)} and #{@link ActivityInfo#SCREEN_ORIENTATION_LANDSCAPE} for LandScape mode or specify that under the <activity> activity tag xml.</b>
 *
 * @author Lynden Jay Evans of JMonkeyGames Inc.
 */
public final class MainActivity extends AppCompatActivity  implements OnRendererCompleted, OnExceptionThrown {


    GLSurfaceView mGLView;
   // private InterstitialAd mInterstitialAd;
   // private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

   // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
//        mGLView = new MyGLSurfaceView(this);
//        setContentView(mGLView);

        setContentView(R.layout.activity_main);
        /*define the android view with it's id from xml*/
        final JmeSurfaceView jmeSurfaceView = findViewById(R.id.jmeSurfaceView);
        jmeSurfaceView.setDestructionPolicy(JmeSurfaceView.DestructionPolicy.KEEP_WHEN_FINISH);
        /*set the jme game*/
        jmeSurfaceView.setLegacyApplication(new MyGame(this.getApplicationContext()));
        jmeSurfaceView.setOnExceptionThrown(this);
        jmeSurfaceView.setOnRendererCompleted(this);
        /*start the game*/
        jmeSurfaceView.startRenderer(500);




        /*
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-3941175351589771/1535288966", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.");
            }
        });



        Timer timer1 = new Timer();
        TimerTask mTt1 = new TimerTask() {
            public void run() {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
            }
        };

        timer1.schedule(mTt1, 10000, 60000);

        */
    }

    /**
     * Fired when exception/error/(concretes of #{@link Throwable} class) is thrown.
     *
     * @param e the thrown error or exception
     */
    @Override
    public void onExceptionThrown(Throwable e) {
        Toast.makeText(MainActivity.this, "User's Delay Finished w/ exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    /**
     * Fired when the user delay in ms is up #{@link JmeSurfaceView#startRenderer(int)}.
     *
     * @param application the current jme game instance
     * @param appSettings the current game settings
     */
    @Override
    public void onRenderCompletion(LegacyApplication application, AppSettings appSettings) {
        Toast.makeText(MainActivity.this, "User's Delay Finished w/o errors !" + application.getContext() + " " + appSettings.getFrameRate(), Toast.LENGTH_SHORT).show();
    }

//     @Override
//    protected void onPause() {
//        super.onPause();
//        // The following call pauses the rendering thread.
//        // If your OpenGL application is memory intensive,
//        // you should consider de-allocating objects that
//        // consume significant memory here.
//        mGLView.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // The following call resumes a paused rendering thread.
//        // If you de-allocated graphic objects for onPause()
//        // this is a good place to re-allocate them.
//        mGLView.onResume();
//    }

    /**
     * Fired when the screen has/hasNo touch/mouse focus.
     *
     * @param hasFocus specify whether the current screen has focus or not
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        /*get the view from the current activity*/
        final View decorView = MainActivity.this.getWindow().getDecorView();
        /*hide navigation bar, apply fullscreen, hide status bar, immersive sticky to disable the system bars(nav and status) from showing up when user wipes the screen*/
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }




}