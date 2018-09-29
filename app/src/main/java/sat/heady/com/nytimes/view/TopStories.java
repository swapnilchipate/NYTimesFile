package sat.heady.com.nytimes.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import sat.heady.com.nytimes.Interface.NotifyRecyclerview;
import sat.heady.com.nytimes.R;
import sat.heady.com.nytimes.adapter.RecylerviewAdapter;
import sat.heady.com.nytimes.model.TopStoriesArticles;
import sat.heady.com.nytimes.viewmodel.TopStoriesElementModel;

public class TopStories extends AppCompatActivity implements NotifyRecyclerview{

    @Bind(R.id.top_story_recylerview)
    RecyclerView topStoryRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecylerviewAdapter recylerviewAdapter;
    NotifyRecyclerview notifyRecyclerview;
    Context context;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);
        ButterKnife.bind(this);
        context=this;
        notifyRecyclerview=(NotifyRecyclerview)this;

        TopStoriesElementModel topStoriesElementModel=new TopStoriesElementModel();
        topStoriesElementModel.getTopStories(notifyRecyclerview);
    }

    @Override
    public void updateRecyler(TopStoriesArticles topStoriesArticles) {
        topStoryRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        TopStoriesElementModel topStoriesElementModel=new TopStoriesElementModel(topStoriesArticles);
        recylerviewAdapter=new RecylerviewAdapter(context,topStoriesElementModel);
        linearLayoutManager=new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(topStoryRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        topStoryRecyclerView.addItemDecoration(dividerItemDecoration);
        topStoryRecyclerView.setAdapter(recylerviewAdapter);
    }
}
