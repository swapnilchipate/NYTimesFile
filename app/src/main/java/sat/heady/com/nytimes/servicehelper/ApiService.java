package sat.heady.com.nytimes.servicehelper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import sat.heady.com.nytimes.model.TopStoriesArticles;

/**
 * Created by Admin: Yang on 26-09-2018.
 */

public interface ApiService {

    @Headers("Accept:application/json")
    @GET("svc/topstories/v2/home.json")
    public Call<TopStoriesArticles> getTopStories();

}
