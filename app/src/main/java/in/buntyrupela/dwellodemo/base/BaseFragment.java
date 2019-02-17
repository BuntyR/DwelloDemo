package in.buntyrupela.dwellodemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ProgressBar;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.buntyrupela.dwellodemo.R;

public class BaseFragment extends Fragment {
    private Unbinder unbinder;
    private FragmentManager fragmentManager;
    ProgressBar progressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        progressBar = view.findViewById(R.id.progress_view);
    }

    protected void showProgressDialog() {
        if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgressDialog() {
        if (progressBar != null) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public void replaceFragment(@Nullable Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {
            String backstackName = fragment.getClass().getName();
            Logger.d("Fragment BackStack Name : " + backstackName);
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
            Logger.d("Fragment Current Name : " + currentFragment);

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
}
