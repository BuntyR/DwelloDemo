package in.buntyrupela.dwellodemo.features.home.hotreddits;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.data.local.models.hot.ChildrenItem;
import in.buntyrupela.dwellodemo.data.utility.ImageLoaderGlide;

public class HotRedditAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChildrenItem> mChildrenItemsList;
    private OnRedditClickListener mListener;
    private ImageLoaderGlide mImageLoader;

    public HotRedditAdapter(List<ChildrenItem> mChildrenItemsList, ImageLoaderGlide mImageLoader,
                            OnRedditClickListener mListener) {
        this.mChildrenItemsList = mChildrenItemsList;
        this.mImageLoader = mImageLoader;
        this.mListener = mListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_hot_reddit, viewGroup, false);
        return new HotRedditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HotRedditViewHolder hotRedditViewHolder = (HotRedditViewHolder) viewHolder;
        hotRedditViewHolder.bindTo(mChildrenItemsList.get(i));
    }

    @Override
    public int getItemCount() {
        return mChildrenItemsList != null ? mChildrenItemsList.size() : 0;
    }

    class HotRedditViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvComments) TextView tvComments;
        @BindView(R.id.ivThumbnail) ImageView ivThumbnail;
        @BindView(R.id.mcvItem) MaterialCardView mcvItem;
        ChildrenItem childrenItem;
        Context context;

        public HotRedditViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindTo(ChildrenItem childrenItem) {
            this.childrenItem = childrenItem;
            tvTitle.setText(childrenItem.getData().getTitle());
            if (childrenItem.getData().getThumbnail() != null && !childrenItem.getData().getThumbnail().isEmpty() &&
                    (URLUtil.isHttpsUrl(childrenItem.getData().getThumbnail()) || URLUtil.isHttpUrl(childrenItem.getData().getThumbnail()))) {
                ivThumbnail.setVisibility(View.VISIBLE);
                mImageLoader.loadImageWithSquarePlaceHolder(childrenItem.getData().getThumbnail()
                        , ivThumbnail, true);
            } else {
                ivThumbnail.setVisibility(View.GONE);
            }

            if (childrenItem.getData().getNumComments() > 0) {
                if (context != null) {
                    tvComments.setVisibility(View.VISIBLE);
                    tvComments.setText(context.getResources().getQuantityString(R.plurals.comments,
                            childrenItem.getData().getNumComments(),
                            childrenItem.getData().getNumComments()));
                }
            } else {
                tvComments.setVisibility(View.GONE);
            }
        }

        @OnClick({R.id.mcvItem})
        public void onClick() {
            if (mListener != null) {
                mListener.onRedditClick(childrenItem.getData().getId());
            }
        }
    }

    public interface OnRedditClickListener {
        void onRedditClick(String redditId);
    }

}
