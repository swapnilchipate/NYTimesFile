package sat.heady.com.nytimes.servicehelper;

import retrofit2.Retrofit;
import sat.heady.com.nytimes.component.DaggerTopStoriesApplicationComponent;
import sat.heady.com.nytimes.component.TopStoriesApplicationComponent;

/**
 * Created by Admin: Yang on 26-09-2018.
 */

public class RetroHelper {



    public static Retrofit getRetofitClient(){
        TopStoriesApplicationComponent topStoriesApplicationComponent= DaggerTopStoriesApplicationComponent.create();
        return  topStoriesApplicationComponent.getRetrofitService();
    }
}
