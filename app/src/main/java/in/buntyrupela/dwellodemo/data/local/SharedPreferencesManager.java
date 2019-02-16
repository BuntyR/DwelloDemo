package in.buntyrupela.dwellodemo.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class SharedPreferencesManager {
    private final SharedPreferences mPrefs;
    private final Gson gson;

    public SharedPreferencesManager(Context context, Gson gson) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = gson;
    }
}
