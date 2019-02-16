package in.buntyrupela.dwellodemo.data.remote;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.base.DwelloApp;
import in.buntyrupela.dwellodemo.data.AppDataStore;
import in.buntyrupela.dwellodemo.data.local.AppLocalDataStore;

public class AppRemoteDataStore implements AppDataStore {

    @Inject AppLocalDataStore appLocalDataStore;

    public AppRemoteDataStore() {
        DwelloApp.getAppComponent().inject(this);
    }
}
