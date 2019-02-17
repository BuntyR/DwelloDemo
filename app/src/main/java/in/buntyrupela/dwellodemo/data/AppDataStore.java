package in.buntyrupela.dwellodemo.data;

import in.buntyrupela.dwellodemo.data.local.models.hot.HotRedditResponse;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;

public interface AppDataStore {

    Single<HotRedditResponse> getHotRedditListing(int limit);

    Single<HotRedditResponse> getMoreHotRedditListing(int limit, String after);

    Single<ResponseBody> getComments(String redditId, int limit);
}
