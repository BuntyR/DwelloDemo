package in.buntyrupela.dwellodemo.data.local;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.data.AppDataStore;
import in.buntyrupela.dwellodemo.data.local.models.comments.RedditCommentsResponse;
import in.buntyrupela.dwellodemo.data.local.models.hot.HotRedditResponse;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class AppLocalDataStore implements AppDataStore {

    private final SharedPreferencesManager mPrefs;
    private final Gson gson;

    @Inject
    public AppLocalDataStore(SharedPreferencesManager mPrefs, Gson gson) {
        this.mPrefs = mPrefs;
        this.gson = gson;
    }

    @Override
    public Single<HotRedditResponse> getHotRedditListing(int limit) {
        return null;
    }

    @Override
    public Single<HotRedditResponse> getMoreHotRedditListing(int limit, String after) {
        return null;
    }

    @Override
    public Single<ResponseBody> getComments(String redditId, int limit) {
        return null;
    }
}
