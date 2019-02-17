package in.buntyrupela.dwellodemo.features.home.comments;

import java.util.List;

import in.buntyrupela.dwellodemo.base.BasePresenter;
import in.buntyrupela.dwellodemo.base.BaseView;
import in.buntyrupela.dwellodemo.data.local.models.comments.RedditCommentsResponse;
import in.buntyrupela.dwellodemo.data.local.models.dwello.CommentsWithType;

public class RedditCommentContract {

    interface Presenter extends BasePresenter {

        void loadComments(String redditId, int limit);

    }

    interface View extends BaseView<Presenter> {

        void redditCommentsLoaded(List<CommentsWithType> commentsWithTypes);

        void redditCommentsFailed();
    }
}
