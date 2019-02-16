package in.buntyrupela.dwellodemo.data;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.data.local.AppLocalDataStore;
import in.buntyrupela.dwellodemo.data.remote.AppRemoteDataStore;

public class AppRepository implements AppDataStore {

    private final AppLocalDataStore mAppLocalDataStore;
    private final AppRemoteDataStore mAppRemoteDataStore;

    @Inject
    public AppRepository(AppLocalDataStore mAppLocalDataStore,
                         AppRemoteDataStore mAppRemoteDataStore) {
        this.mAppLocalDataStore = mAppLocalDataStore;
        this.mAppRemoteDataStore = mAppRemoteDataStore;
    }
}
