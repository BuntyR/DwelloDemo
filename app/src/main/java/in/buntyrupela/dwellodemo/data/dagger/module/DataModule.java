package in.buntyrupela.dwellodemo.data.dagger.module;

import android.app.Application;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.buntyrupela.dwellodemo.BuildConfig;
import in.buntyrupela.dwellodemo.data.local.AppLocalDataStore;
import in.buntyrupela.dwellodemo.data.local.SharedPreferencesManager;
import in.buntyrupela.dwellodemo.data.remote.AppRemoteDataStore;
import in.buntyrupela.dwellodemo.data.utility.ImageLoaderGlide;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    private static final String CHUCK_INTERCEPTOR = "chuck_interceptor";
    private static final String DWELLO_INTERCEPTOR = "dwello_interceptor";

    public DataModule() {
    }


    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 2 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    SharedPreferencesManager providesSharedPreferences(Application application, Gson gson) {
        return new SharedPreferencesManager(application, gson);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClientProductConsumer(Cache cache,
                                                    HttpLoggingInterceptor loggingInterceptor,
                                                    @Nullable @Named(DWELLO_INTERCEPTOR) Interceptor authInterceptor,
                                                    @Nullable @Named(CHUCK_INTERCEPTOR) Interceptor chuckInterceptor,
                                                    Application context) {
        OkHttpClient.Builder client = new OkHttpClient().newBuilder();
        client.connectTimeout(30, TimeUnit.SECONDS);
        client.readTimeout(30, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);
        client.cache(cache);
        if (authInterceptor != null) {
            client.addInterceptor(authInterceptor);
        }
        if (chuckInterceptor != null) {
            client.addInterceptor(chuckInterceptor);
        }
        client.addInterceptor(loggingInterceptor);
        return client.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(
                BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY :
                        HttpLoggingInterceptor.Level.NONE);

        return loggingInterceptor;
    }

    @Provides
    @Nullable
    @Named(DWELLO_INTERCEPTOR)
    @Singleton
    Interceptor provideAuthorizationInterceptor(
            final SharedPreferencesManager mPrefs) {
        return null;
    }


    @Provides
    @Nullable
    @Named(CHUCK_INTERCEPTOR)
    @Singleton
    Interceptor provideChuckInterceptor(Application application) {
        return new ChuckInterceptor(application);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitNodeProduct(
            Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    AppLocalDataStore provideAppLocalDataStore(SharedPreferencesManager prefs,
                                               Gson gson) {
        return new AppLocalDataStore(prefs, gson);
    }

    @Provides
    @Singleton
    AppRemoteDataStore provideAppRemoteDataStore() {
        return new AppRemoteDataStore();
    }

    @Provides
    @Singleton
    ImageLoaderGlide provideImageLoaderGlide(Application application,
                                             SharedPreferencesManager mPrefs) {
        return new ImageLoaderGlide(application, mPrefs);
    }
}
