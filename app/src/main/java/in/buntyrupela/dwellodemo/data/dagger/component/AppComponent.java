package in.buntyrupela.dwellodemo.data.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import in.buntyrupela.dwellodemo.base.DwelloApp;
import in.buntyrupela.dwellodemo.data.dagger.module.AppModule;
import in.buntyrupela.dwellodemo.data.dagger.module.DataModule;
import in.buntyrupela.dwellodemo.data.remote.AppRemoteDataStore;
import in.buntyrupela.dwellodemo.features.home.HomeActivity;
import in.buntyrupela.dwellodemo.features.home.comments.RedditCommentFragment;
import in.buntyrupela.dwellodemo.features.home.hotreddits.HotRedditFragment;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    void inject(DwelloApp app);

    void inject(HomeActivity homeActivity);

    void inject(AppRemoteDataStore appRemoteDataStore);

    void inject(HotRedditFragment hotRedditFragment);

    void inject(RedditCommentFragment redditCommentFragment);
}
