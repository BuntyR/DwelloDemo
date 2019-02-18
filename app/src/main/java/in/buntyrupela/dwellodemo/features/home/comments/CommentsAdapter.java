package in.buntyrupela.dwellodemo.features.home.comments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.data.local.models.dwello.CommentsWithType;

import static in.buntyrupela.dwellodemo.data.utility.DwelloUtility.MORE;
import static in.buntyrupela.dwellodemo.data.utility.DwelloUtility.T1;

public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommentsWithType> mCommentsWithTypeList;

    public CommentsAdapter(List<CommentsWithType> mCommentsWithTypeList) {
        this.mCommentsWithTypeList = mCommentsWithTypeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_comment, viewGroup, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CommentsViewHolder commentsViewHolder = (CommentsViewHolder) viewHolder;
        commentsViewHolder.bindTo(mCommentsWithTypeList.get(i));
    }

    @Override
    public int getItemCount() {
        return mCommentsWithTypeList != null ? mCommentsWithTypeList.size() : 0;
    }

    static class CommentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvAuthor) TextView tvAuthor;
        @BindView(R.id.tvbody) TextView tvbody;
        @BindView(R.id.tvLike) TextView tvLike;
        @BindView(R.id.mcvComment) MaterialCardView mcvComment;
        @BindDimen(R.dimen._10sdp) int space;
        @BindColor(R.color.colorAccent) int colorAccent;
        @BindString(R.string.by_author) String byAuthor;
        CommentsWithType commentsWithType;
        Context context;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindTo(CommentsWithType commentsWithType) {
            this.commentsWithType = commentsWithType;

            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(commentsWithType.getDepth() * space, 0, 0, 0);
            mcvComment.setLayoutParams(params);


            if (commentsWithType.getCommentType().equalsIgnoreCase(T1)) {
                if (commentsWithType.getAuthor() != null && !commentsWithType.getAuthor().isEmpty()) {
                    tvAuthor.setVisibility(View.VISIBLE);
                    tvAuthor.setText(String.format(byAuthor, commentsWithType.getAuthor()));
                } else {
                    tvAuthor.setVisibility(View.GONE);
                }

                if (commentsWithType.getBody() != null && !commentsWithType.getBody().isEmpty()) {
                    tvbody.setVisibility(View.VISIBLE);
                    tvbody.setText(commentsWithType.getBody());
                } else {
                    tvbody.setVisibility(View.GONE);
                }

                if (commentsWithType.getUps() != 0) {
                    tvLike.setVisibility(View.VISIBLE);
                    tvLike.setText(String.valueOf(commentsWithType.getUps()));
                } else {
                    tvLike.setVisibility(View.GONE);
                }

            } else if (commentsWithType.getCommentType().equalsIgnoreCase(MORE)) {
                tvbody.setVisibility(View.VISIBLE);
                tvAuthor.setVisibility(View.GONE);
                tvLike.setVisibility(View.GONE);

                tvbody.setText(context.getResources().getQuantityString(R.plurals.more_comments,
                        commentsWithType.getCount(),
                        commentsWithType.getCount()));
            }

        }
    }
}
