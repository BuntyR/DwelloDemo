package in.buntyrupela.dwellodemo.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.base.BaseActivity;
import in.buntyrupela.dwellodemo.base.DwelloApp;
import in.buntyrupela.dwellodemo.data.AppRepository;
import in.buntyrupela.dwellodemo.data.local.SharedPreferencesManager;
import in.buntyrupela.dwellodemo.features.home.hotreddits.HotRedditFragment;

public class HomeActivity extends BaseActivity {

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DwelloApp.getAppComponent().inject(this);
        setContentView(R.layout.activity_home);
        replaceFragment(HotRedditFragment.newInstance());
    }


    public static Intent newInstance(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}
