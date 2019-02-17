package in.buntyrupela.dwellodemo.data.local.models.hot;

import com.google.gson.annotations.SerializedName;


public class Media {

    @SerializedName("reddit_video")
    private RedditVideo redditVideo;

    @SerializedName("oembed")
    private Oembed oembed;

    @SerializedName("type")
    private String type;

    public void setRedditVideo(RedditVideo redditVideo) {
        this.redditVideo = redditVideo;
    }

    public RedditVideo getRedditVideo() {
        return redditVideo;
    }

    public void setOembed(Oembed oembed) {
        this.oembed = oembed;
    }

    public Oembed getOembed() {
        return oembed;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return
                "Media{" +
                        "reddit_video = '" + redditVideo + '\'' +
                        ",oembed = '" + oembed + '\'' +
                        ",type = '" + type + '\'' +
                        "}";
    }
}