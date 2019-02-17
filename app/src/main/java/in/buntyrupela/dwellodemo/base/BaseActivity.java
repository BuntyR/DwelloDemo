package in.buntyrupela.dwellodemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import in.buntyrupela.dwellodemo.R;

public class BaseActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
    }

    public void replaceFragment(@Nullable Fragment fragment) {
        replaceFragment(fragment, true);
    }

    public void replaceFragment(@Nullable Fragment fragment,
                                boolean addToBackStack /*default true*/) {
        if (fragment != null) {
            String backstackName = fragment.getClass().getName();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (currentFragment == null) {
                ft.add(R.id.fragmentContainer, fragment);
            } else {
                ft.replace(R.id.fragmentContainer, fragment);
            }

            if (addToBackStack) ft.addToBackStack(backstackName);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            finish();
        }
    }
}
