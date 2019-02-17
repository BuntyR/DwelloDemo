package in.buntyrupela.dwellodemo.features.home.comments;

import com.google.gson.Gson;

import java.util.List;

import in.buntyrupela.dwellodemo.data.AppRepository;
import in.buntyrupela.dwellodemo.data.local.models.dwello.CommentsWithType;
import in.buntyrupela.dwellodemo.data.utility.DwelloUtility;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RedditCommentsPresenter implements RedditCommentContract.Presenter {

    private final AppRepository mAppRepository;
    private final Gson gson;
    private final RedditCommentContract.View mView;

    public RedditCommentsPresenter(AppRepository mAppRepository, Gson gson,
                                   RedditCommentContract.View mView) {
        this.mAppRepository = mAppRepository;
        this.gson = gson;
        this.mView = mView;
    }

    @Override
    public void loadComments(String redditId, int limit) {
        mView.showLoading();
        mAppRepository
                .getComments(redditId, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        List<CommentsWithType> commentsWithTypes =
                                DwelloUtility.convertToOptimizedList(responseBody, gson);
                        mView.redditCommentsLoaded(commentsWithTypes);
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.redditCommentsFailed();
                        e.printStackTrace();
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
