package com.princekr.android.flickerbrowser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FlickerRecyclerViewAdapter extends RecyclerView.Adapter<FlickerRecyclerViewAdapter.FlickerImageViewHolder> {
    private static final String TAG = "FlickerRecyclerViewAdap";
    private List<Photo> mPhotoList;
    private Context mContext;

    public FlickerRecyclerViewAdapter( Context context, List<Photo> photoList) {
        mContext = context;
        mPhotoList = photoList;
    }

    @NonNull
    @Override
    public FlickerImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new FlickerImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickerImageViewHolder holder, int position) {
        Photo photoItem = mPhotoList.get(position);
        Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + " ---> " + position);
        Picasso.with(mContext).load(photoItem.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);
        holder.title.setText(photoItem.getTitle());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called");
        return ((mPhotoList != null) && (mPhotoList.size() != 0) ? mPhotoList.size() : 0);
    }

    void loadNewData(List<Photo> newPhotos) {
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return ((mPhotoList != null) && (mPhotoList.size() != 0) ? mPhotoList.get(position) : null);
    }

    static class FlickerImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "FlickerImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public FlickerImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "FlickerImageViewHolder: starts");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);


        }
    }
}
