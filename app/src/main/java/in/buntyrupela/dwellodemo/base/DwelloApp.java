package in.buntyrupela.dwellodemo.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import in.buntyrupela.dwellodemo.BuildConfig;
import in.buntyrupela.dwellodemo.data.dagger.component.AppComponent;
import in.buntyrupela.dwellodemo.data.dagger.component.DaggerAppComponent;
import in.buntyrupela.dwellodemo.data.dagger.module.AppModule;
import in.buntyrupela.dwellodemo.data.dagger.module.DataModule;

public class DwelloApp extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildDependencyModules();
        mAppComponent.inject(this);
        initializeLogger();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    private void buildDependencyModules() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();
    }

    private void initializeLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default
                // true
                .methodCount(6)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset.
                // Default 5
                .tag("DwelloDemoRB")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {

            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
