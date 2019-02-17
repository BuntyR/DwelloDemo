package in.buntyrupela.dwellodemo.data.local.models.comments;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Preview {

    @SerializedName("images")
    private List<ImagesItem> images;

    @SerializedName("enabled")
    private boolean enabled;

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

    @Override
    public String toString() {
        return
                "Preview{" +
                        "images = '" + images + '\'' +
                        ",enabled = '" + enabled + '\'' +
                        "}";
    }
}