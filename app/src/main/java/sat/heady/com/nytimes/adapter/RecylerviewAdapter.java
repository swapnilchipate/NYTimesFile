package sat.heady.com.nytimes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import sat.heady.com.nytimes.R;
import sat.heady.com.nytimes.Utils.Utils;
import sat.heady.com.nytimes.view.ActivityWebview;
import sat.heady.com.nytimes.viewmodel.TopStoriesElementModel;

/**
 * Created by Admin: Yang on 26-09-2018.
 */

public class RecylerviewAdapter extends RecyclerView.Adapter<RecylerviewAdapter.ViewHolder> {

    public TopStoriesElementModel topStoriesElementModel;
    Context context;
    private String relativeTime;


    public RecylerviewAdapter(Context context,TopStoriesElementModel topStoriesElementModel){
        this.context=context;
    this.topStoriesElementModel=topStoriesElementModel;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_stories_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        relativeTime= Utils.covertToRelativeTime(topStoriesElementModel.results.get(position).getUpdatedDate());
        holder.article_by.setText(topStoriesElementModel.results.get(position).getByline());
        holder.title.setText(topStoriesElementModel.results.get(position).getTitle());
        holder.description.setText(topStoriesElementModel.results.get(position).getAbstract());
        holder.time.setText(relativeTime);
        if(topStoriesElementModel.results.get(position).getMultimedia().size()!=0) {
            holder.image.setVisibility(View.VISIBLE);
            Glide.with(context).load(topStoriesElementModel.results.get(position).getMultimedia().get(3).getUrl()).into(holder.image);
        }else {
            holder.image.setVisibility(View.GONE);
        }

        holder.newItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ActivityWebview.class);
                intent.putExtra("url",topStoriesElementModel.results.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topStoriesElementModel.numResults;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView article_by, title, description,time;
        ImageView image;
        LinearLayout newItemLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            newItemLayout=(LinearLayout)itemView.findViewById(R.id.news_item_layout);
            article_by=(TextView)itemView.findViewById(R.id.article_byline);
            title = (TextView) itemView.findViewById(R.id.article_title);
            description = (TextView) itemView.findViewById(R.id.article_description);
            time = (TextView) itemView.findViewById(R.id.article_time);
            image = (ImageView) itemView.findViewById(R.id.article_image);
        }
    }
}
