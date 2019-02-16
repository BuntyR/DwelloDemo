package in.buntyrupela.dwellodemo.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public static Intent newInstance(Context context) {
        return new Intent(context, HomeActivity.class);
    }
}
