package org.telegram.digitalresistance.psiphon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.telegram.digitalresistance.ui.LaunchActivity;

public class SplashActivity extends Activity {

    private final Handler uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presentLaunchActivity();
            }
        }, 2500);
    }

    @Override
    protected void onDestroy() {
        uiHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private void presentLaunchActivity() {
        startActivity(new Intent(this, LaunchActivity.class));
        finish();
    }
}
