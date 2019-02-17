package in.buntyrupela.dwellodemo.data.utility;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import in.buntyrupela.dwellodemo.BuildConfig;
import in.buntyrupela.dwellodemo.R;
import in.buntyrupela.dwellodemo.data.local.SharedPreferencesManager;

public class ImageLoaderGlide {

    private final RequestManager requestManager;
    private final Drawable squarePlaceHolder;
    private final SharedPreferencesManager mPrefs;

    @Inject
    public ImageLoaderGlide(Context context, SharedPreferencesManager mPrefs) {

        requestManager = new GlideBuilder().setLogLevel(BuildConfig.DEBUG ? Log.VERBOSE : Log.ERROR)
                .setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                .build(context)
                .getRequestManagerRetriever()
                .get(context);
        this.mPrefs = mPrefs;
        squarePlaceHolder =
                AppCompatResources.getDrawable(context, R.drawable.logo);
    }

    public void loadImageWithSquarePlaceHolder(String url, ImageView imageView, boolean fit) {
        if (fit) {
            requestManager.load(url)
                    .apply(new RequestOptions().placeholder(squarePlaceHolder)
                            .error(squarePlaceHolder).fitCenter())
                    .into(imageView);
        } else {
            requestManager.load(url)
                    .apply(new RequestOptions().placeholder(squarePlaceHolder)
                            .error(squarePlaceHolder))
                    .into(imageView);
        }
    }


}
