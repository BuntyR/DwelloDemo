package in.buntyrupela.dwellodemo.features.home.hotreddits;

import in.buntyrupela.dwellodemo.base.BasePresenter;
import in.buntyrupela.dwellodemo.base.BaseView;
import in.buntyrupela.dwellodemo.data.local.models.hot.HotRedditResponse;

public class HotRedditContract {
    interface Presenter extends BasePresenter {

        void loadhotReddits(int count);

        void loadMorehotReddits(int count, String after);

    }

    interface View extends BaseView<Presenter> {
        void hotRedditsLoaded(HotRedditResponse hotRedditResponse);

        void hotRedditsFailed();
    }
}
