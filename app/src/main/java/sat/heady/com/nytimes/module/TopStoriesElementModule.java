package sat.heady.com.nytimes.module;

import dagger.Module;
import dagger.Provides;
import sat.heady.com.nytimes.model.TopStoriesArticles;

/**
 * Created by Admin: Yang on 26-09-2018.
 */

@Module
public class TopStoriesElementModule {

    @Provides
    public TopStoriesArticles getTopStoriesElement(){
       return new TopStoriesArticles();
    }
}
