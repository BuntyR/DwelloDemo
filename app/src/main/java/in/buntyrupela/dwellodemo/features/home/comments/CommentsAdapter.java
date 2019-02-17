package in.buntyrupela.dwellodemo.features.home.comments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.data.local.models.dwello.CommentsWithType;

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
        CommentsWithType commentsWithType;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(CommentsWithType commentsWithType) {
            this.commentsWithType = commentsWithType;
            tvAuthor.setText(commentsWithType.getAuthor());
            tvbody.setText(commentsWithType.getBody());
        }
    }
}
