package sat.heady.com.nytimes.component;

import dagger.Component;
import retrofit2.Retrofit;
import sat.heady.com.nytimes.model.TopStoriesArticles;
import sat.heady.com.nytimes.module.ApplicationModule;
import sat.heady.com.nytimes.module.ContextModule;
import sat.heady.com.nytimes.module.TopStoriesElementModule;

/**
 * Created by Admin: Yang on 26-09-2018.
 */
@Component(modules = {ApplicationModule.class,ContextModule.class,TopStoriesElementModule.class})
public interface TopStoriesApplicationComponent {
    Retrofit getRetrofitService();
    TopStoriesArticles getTopStoriesElement();
}
