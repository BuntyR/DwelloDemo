package in.buntyrupela.dwellodemo.features.home.hotreddits;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.base.BaseFragment;
import in.buntyrupela.dwellodemo.base.DwelloApp;
import in.buntyrupela.dwellodemo.data.AppRepository;
import in.buntyrupela.dwellodemo.data.local.models.hot.ChildrenItem;
import in.buntyrupela.dwellodemo.data.local.models.hot.HotRedditResponse;
import in.buntyrupela.dwellodemo.data.utility.Constants;
import in.buntyrupela.dwellodemo.data.utility.ImageLoaderGlide;
import in.buntyrupela.dwellodemo.features.home.comments.RedditCommentFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotRedditFragment extends BaseFragment implements HotRedditContract.View,
        HotRedditAdapter.OnRedditClickListener {

    @BindView(R.id.rvHotReddits) RecyclerView rvHotReddits;
    @Inject AppRepository mAppRepository;
    @Inject ImageLoaderGlide mImageLoader;
    private HotRedditContract.Presenter mPresenter;
    private HotRedditAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<ChildrenItem> mChildrenItemsList;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private String after;

    public HotRedditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HotRedditPresenter(mAppRepository, this);
        initiateHotReddits();
    }

    private void initiateHotReddits() {
        mChildrenItemsList = new ArrayList<>();
        mAdapter = new HotRedditAdapter(mChildrenItemsList, mImageLoader, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_reddit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvHotReddits.setLayoutManager(mLinearLayoutManager);
        if (rvHotReddits.getAdapter() == null) {
            rvHotReddits.setAdapter(mAdapter);
        }
        manageScroll();
        mPresenter.loadhotReddits(Constants.REDDIT_LIMIT_COUNT);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DwelloApp.getAppComponent().inject(this);
    }

    @Override
    public void hotRedditsLoaded(HotRedditResponse hotRedditResponse) {
        after = hotRedditResponse.getData().getAfter();
        mChildrenItemsList.addAll(hotRedditResponse.getData().getChildren());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void hotRedditsFailed() {

    }

    @Override
    public void setPresenter(HotRedditContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }


    private void manageScroll() {
        RecyclerView.OnScrollListener recyclerViewOnScrollListener =
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView,
                                                     int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                        int firstVisibleItemPosition =
                                mLinearLayoutManager.findFirstVisibleItemPosition();

                        if (!isLoading && !isLastPage) {
                            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                    && firstVisibleItemPosition >= 0
                                    && totalItemCount >= Constants.REDDIT_LIMIT_COUNT) {
                                if (mChildrenItemsList != null && !mChildrenItemsList.isEmpty()) {
                                    mPresenter.loadMorehotReddits(Constants.REDDIT_LIMIT_COUNT,
                                            after);
                                }
                            }
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                };
        rvHotReddits.addOnScrollListener(recyclerViewOnScrollListener);
    }

    public static HotRedditFragment newInstance() {
        return new HotRedditFragment();
    }

    @Override
    public void onRedditClick(String redditId) {
        replaceFragment(RedditCommentFragment.newInstance(redditId), true);
    }

    @Override
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }
}
