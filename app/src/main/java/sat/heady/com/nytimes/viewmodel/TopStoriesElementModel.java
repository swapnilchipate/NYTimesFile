package sat.heady.com.nytimes.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sat.heady.com.nytimes.Interface.NotifyRecyclerview;
import sat.heady.com.nytimes.model.Result;
import sat.heady.com.nytimes.model.TopStoriesArticles;
import sat.heady.com.nytimes.servicehelper.ApiService;
import sat.heady.com.nytimes.servicehelper.RetroHelper;

/**
 * Created by Admin: Yang on 26-09-2018.
 */

public class TopStoriesElementModel extends ViewModel {

    public String status;
    public String copyright;
    public String section;
    public String lastUpdated;
    public Integer numResults;
    public List<Result> results = null;


    ApiService apiService;


    public TopStoriesElementModel() {
    }

    public TopStoriesElementModel(TopStoriesArticles topStoriesArticles) {
        this.status = topStoriesArticles.status;
        this.copyright = topStoriesArticles.copyright;
        this.section = topStoriesArticles.section;
        this.lastUpdated = topStoriesArticles.lastUpdated;
        this.numResults = topStoriesArticles.numResults;
        this.results = topStoriesArticles.results;
    }




    public void getTopStories(final NotifyRecyclerview notifyRecyclerview){

        apiService= RetroHelper.getRetofitClient().create(ApiService.class);
        Call<TopStoriesArticles> arrayListCall=apiService.getTopStories();
        arrayListCall.enqueue(new Callback<TopStoriesArticles>() {
            @Override
            public void onResponse(Call<TopStoriesArticles> call, Response<TopStoriesArticles> response) {
                notifyRecyclerview.updateRecyler(response.body());
            }

            @Override
            public void onFailure(Call<TopStoriesArticles> call, Throwable t) {

            }
        });
    }
}
