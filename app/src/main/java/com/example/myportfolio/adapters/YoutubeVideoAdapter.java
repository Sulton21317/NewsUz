package com.example.myportfolio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myportfolio.R;
import com.example.myportfolio.models.YoutubeVideoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList;


    public YoutubeVideoAdapter(Context context, ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList) {
        this.context = context;
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;
    }

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.youtube_video_custom_layout, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, final int position) {

        final YoutubeVideoModel youtubeVideoModel = youtubeVideoModelArrayList.get(position);

        holder.videoTitle.setText(youtubeVideoModel.getTitle());
        holder.videoDuration.setText(youtubeVideoModel.getDuration());


        /*  initialize the thumbnail image view , we need to pass Developer Key */
        String url = "https://img.youtube.com/vi/" + youtubeVideoModel.getVideoId() + "/mqdefault.jpg";

        Picasso.get().load(url).placeholder(R.drawable.download_icon).into(holder.videoThumbnailImageView);

    }

    @Override
    public int getItemCount() {
        return youtubeVideoModelArrayList != null ? youtubeVideoModelArrayList.size() : 0;
    }
}

class YoutubeViewHolder extends RecyclerView.ViewHolder {

    public ImageView videoThumbnailImageView;
    public TextView videoTitle, videoDuration;

    public YoutubeViewHolder(View itemView) {
        super(itemView);
        videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
        videoTitle = itemView.findViewById(R.id.video_title_label);
        videoDuration = itemView.findViewById(R.id.video_duration_label);

    }
}