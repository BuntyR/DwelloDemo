package in.buntyrupela.dwellodemo.features.home.comments;


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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.base.BaseFragment;
import in.buntyrupela.dwellodemo.base.DwelloApp;
import in.buntyrupela.dwellodemo.data.AppRepository;
import in.buntyrupela.dwellodemo.data.local.models.dwello.CommentsWithType;
import in.buntyrupela.dwellodemo.data.utility.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedditCommentFragment extends BaseFragment implements RedditCommentContract.View {

    @Inject AppRepository mAppRepository;
    @Inject Gson gson;
    @BindView(R.id.rvComments) RecyclerView rvComments;

    private RedditCommentContract.Presenter mPresenter;
    private CommentsAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<CommentsWithType> mCommentsWithTypes;
    private static final String REDDIT_ID = "in.br.dd.f.h.c.rcf.reddit_id";

    public RedditCommentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new RedditCommentsPresenter(mAppRepository,gson, this);
        initiateComments();
    }

    private void initiateComments() {
        mCommentsWithTypes = new ArrayList<>();
        mAdapter = new CommentsAdapter(mCommentsWithTypes);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DwelloApp.getAppComponent().inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvComments.setLayoutManager(mLinearLayoutManager);
        rvComments.setAdapter(mAdapter);
        if (getArguments() != null && getArguments().containsKey(REDDIT_ID)) {
            mPresenter.loadComments(getArguments().getString(REDDIT_ID),
                    Constants.REDDIT_COMMENTS_LIMIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reddit_comment, container, false);
    }

    @Override
    public void redditCommentsLoaded(List<CommentsWithType> commentsWithTypes) {
        mCommentsWithTypes.addAll(commentsWithTypes);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void redditCommentsFailed() {
        showSnackbarBase("Couldn't get data, please try after some time");
    }

    @Override
    public void setPresenter(RedditCommentContract.Presenter mPresenter) {
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

    public static RedditCommentFragment newInstance(String redditId) {
        Bundle args = new Bundle();
        RedditCommentFragment fragment = new RedditCommentFragment();
        args.putString(REDDIT_ID, redditId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        mPresenter.unsubscribe();
        super.onDestroyView();
    }
}
