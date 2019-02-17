package in.buntyrupela.dwellodemo.data.local.models.hot;

import com.google.gson.annotations.SerializedName;


public class LinkFlairRichtextItem {

    @SerializedName("t")
    private String T;

    @SerializedName("e")
    private String E;

    public void setT(String T) {
        this.T = T;
    }

    public String getT() {
        return T;
    }

    public void setE(String E) {
        this.E = E;
    }

    public String getE() {
        return E;
    }

    @Override
    public String toString() {
        return
                "LinkFlairRichtextItem{" +
                        "t = '" + T + '\'' +
                        ",e = '" + E + '\'' +
                        "}";
    }
}