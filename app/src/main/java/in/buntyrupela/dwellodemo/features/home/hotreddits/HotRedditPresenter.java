package in.buntyrupela.dwellodemo.features.home.hotreddits;

import in.buntyrupela.dwellodemo.data.AppRepository;
import in.buntyrupela.dwellodemo.data.local.models.hot.HotRedditResponse;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HotRedditPresenter implements HotRedditContract.Presenter {

    private final AppRepository mAppRepository;
    private final HotRedditContract.View mView;

    public HotRedditPresenter(AppRepository mAppRepository, HotRedditContract.View mView) {
        this.mAppRepository = mAppRepository;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadhotReddits(int count) {
        mView.showLoading();
        mAppRepository
                .getHotRedditListing(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<HotRedditResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(HotRedditResponse hotRedditResponse) {
                        mView.hotRedditsLoaded(hotRedditResponse);
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hotRedditsFailed();
                        mView.hideLoading();
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void loadMorehotReddits(int count, String after) {
        mView.showLoading();
        mAppRepository
                .getMoreHotRedditListing(count, after)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<HotRedditResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(HotRedditResponse hotRedditResponse) {
                        mView.hotRedditsLoaded(hotRedditResponse);
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hotRedditsFailed();
                        mView.hideLoading();
                        e.printStackTrace();
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
