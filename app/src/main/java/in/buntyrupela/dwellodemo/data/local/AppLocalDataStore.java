package in.buntyrupela.dwellodemo.data.local;

import com.google.gson.Gson;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.data.AppDataStore;

public class AppLocalDataStore implements AppDataStore {

    private final SharedPreferencesManager mPrefs;
    private final Gson gson;

    @Inject
    public AppLocalDataStore(SharedPreferencesManager mPrefs, Gson gson) {
        this.mPrefs = mPrefs;
        this.gson = gson;
    }
}
