package in.buntyrupela.dwellodemo.features.splash;

import android.os.Bundle;
import android.os.Handler;

import in.buntyrupela.dwellodemo.base.BaseActivity;
import in.buntyrupela.dwellodemo.features.home.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        waitForBranding();
    }

    private void waitForBranding() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(HomeActivity.newInstance(getApplicationContext()));
            }
        }, 2000);
    }
}
