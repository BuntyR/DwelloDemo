package in.buntyrupela.dwellodemo.data.remote;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.base.DwelloApp;
import in.buntyrupela.dwellodemo.data.AppDataStore;
import in.buntyrupela.dwellodemo.data.local.AppLocalDataStore;
import in.buntyrupela.dwellodemo.data.local.models.hot.HotRedditResponse;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class AppRemoteDataStore implements AppDataStore {

    @Inject
    Retrofit networkClient;
    @Inject AppLocalDataStore appLocalDataStore;

    private static final String QUERY_PARAM_LIMIT = "limit";
    private static final String QUERY_PARAM_AFTER = "after";
    private static final String QUERY_PARAM_SORT = "sort";
    private static final String PATH_PARAM_REDDIT_IT = "reddit-id";

    public AppRemoteDataStore() {
        DwelloApp.getAppComponent().inject(this);
    }

    @Override
    public Single<HotRedditResponse> getHotRedditListing(int limit) {
        return networkClient.create(RedditClient.class).getHotRedditListing(limit);
    }

    @Override
    public Single<HotRedditResponse> getMoreHotRedditListing(int limit, String after) {
        return networkClient.create(RedditClient.class).getMoreHotRedditListing(limit, after);
    }

    @Override
    public Single<ResponseBody> getComments(String redditId, int limit) {
        return networkClient.create(RedditClient.class).getRedditComments(redditId, limit, "best");
    }


    public interface RedditClient {
        @GET("hot.json")
        Single<HotRedditResponse> getHotRedditListing(@Query(QUERY_PARAM_LIMIT) int limit);

        @GET("hot.json")
        Single<HotRedditResponse> getMoreHotRedditListing(@Query(QUERY_PARAM_LIMIT) int limit,
                                                          @Query(QUERY_PARAM_AFTER) String after);

        @GET("comments/{" + PATH_PARAM_REDDIT_IT + "}.json")
        Single<ResponseBody> getRedditComments(@Path(PATH_PARAM_REDDIT_IT) String redditId,
                                               @Query(QUERY_PARAM_LIMIT) int limit,
                                               @Query(QUERY_PARAM_SORT) String sort);
    }
}
