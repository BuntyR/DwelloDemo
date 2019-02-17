package in.buntyrupela.dwellodemo.data.local.models.hot;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Preview {

    @SerializedName("images")
    private List<ImagesItem> images;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("reddit_video_preview")
    private RedditVideoPreview redditVideoPreview;

    public void setImages(List<ImagesItem> images) {
        this.images = images;
    }

    public List<ImagesItem> getImages() {
        return images;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setRedditVideoPreview(RedditVideoPreview redditVideoPreview) {
        this.redditVideoPreview = redditVideoPreview;
    }

    public RedditVideoPreview getRedditVideoPreview() {
        return redditVideoPreview;
    }

    @Override
    public String toString() {
        return
                "Preview{" +
                        "images = '" + images + '\'' +
                        ",enabled = '" + enabled + '\'' +
                        ",reddit_video_preview = '" + redditVideoPreview + '\'' +
                        "}";
    }
}