package in.buntyrupela.dwellodemo.data;

import java.util.List;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.data.local.AppLocalDataStore;
import in.buntyrupela.dwellodemo.data.local.models.comments.RedditCommentsResponse;
import in.buntyrupela.dwellodemo.data.local.models.hot.HotRedditResponse;
import in.buntyrupela.dwellodemo.data.remote.AppRemoteDataStore;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class AppRepository implements AppDataStore {

    private final AppLocalDataStore mAppLocalDataStore;
    private final AppRemoteDataStore mAppRemoteDataStore;

    @Inject
    public AppRepository(AppLocalDataStore mAppLocalDataStore,
                         AppRemoteDataStore mAppRemoteDataStore) {
        this.mAppLocalDataStore = mAppLocalDataStore;
        this.mAppRemoteDataStore = mAppRemoteDataStore;
    }

    @Override
    public Single<HotRedditResponse> getHotRedditListing(int limit) {
        return mAppRemoteDataStore.getHotRedditListing(limit);
    }

    @Override
    public Single<HotRedditResponse> getMoreHotRedditListing(int limit, String after) {
        return mAppRemoteDataStore.getMoreHotRedditListing(limit, after);
    }

    @Override
    public Single<ResponseBody> getComments(String redditId, int limit) {
        return mAppRemoteDataStore.getComments(redditId, limit);
    }
}
