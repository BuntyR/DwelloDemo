package in.buntyrupela.dwellodemo.data.local.models.hot;


import com.google.gson.annotations.SerializedName;


public class HotRedditResponse {

    @SerializedName("data")
    private Data data;

    @SerializedName("kind")
    private String kind;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return
                "HotRedditResponse{" +
                        "data = '" + data + '\'' +
                        ",kind = '" + kind + '\'' +
                        "}";
    }
}