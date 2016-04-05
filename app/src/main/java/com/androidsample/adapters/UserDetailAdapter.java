package com.androidsample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidsample.R;
import com.androidsample.model.Story;
import com.androidsample.model.UserDetail;
import com.androidsample.startup.GlobalAppContext;
import com.squareup.picasso.Picasso;

/**
 * Created by Aditya on 04-Apr-16.
 */
public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.ViewHolder>{

    private UserDetail mUserDetail;
    public static final int Simple_Card = 0;
    public static final int Checkin_Layout = 1;
    public static final int Photo = 2;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class SimpleCardHolder extends ViewHolder {
        TextView title,description;

        public SimpleCardHolder(View v) {
            super(v);
            this.title = (TextView) v.findViewById(R.id.title);
            this.description = (TextView) v.findViewById(R.id.description);
        }
    }

    public class ChechInCardHolder extends ViewHolder {
        TextView title,webLink;
        ImageView imageView;

        public ChechInCardHolder(View v) {
            super(v);
            this.title = (TextView) v.findViewById(R.id.title);
            this.imageView = (ImageView) v.findViewById(R.id.imageView);
            this.webLink = (TextView) v.findViewById(R.id.weblink);
        }
    }

    public class PhotoCardHolder extends ViewHolder {
        ImageView imageView;

        public PhotoCardHolder(View v) {
            super(v);
            this.imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    public UserDetailAdapter(UserDetail detail){
        AddPhotoItem(detail);
        mUserDetail = detail;
    }

    private void AddPhotoItem(UserDetail detail){
        Story story = new Story();
        story.image_url = detail.photo;
        story.type = "photo";
        detail.our_story.add(0,story);
    }

    @Override
    public UserDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == Simple_Card) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.simple_card_layout, parent, false);

            return new SimpleCardHolder(v);
        }
        else if (viewType == Checkin_Layout) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.checkin_layout, parent, false);

            return new ChechInCardHolder(v);
        }
        else if (viewType == Photo) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.photo_layout, parent, false);

            return new PhotoCardHolder(v);
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(UserDetailAdapter.ViewHolder holder, int position) {
        if (holder.getItemViewType() == Simple_Card) {
            SimpleCardHolder mholder = (SimpleCardHolder) holder;
            ((SimpleCardHolder) holder).title.setText(getObject(position).title);
            ((SimpleCardHolder) holder).description.setText(getObject(position).content);
        }
        else if(holder.getItemViewType() == Checkin_Layout) {
            ChechInCardHolder mholder = (ChechInCardHolder) holder;
            ((ChechInCardHolder) holder).title.setText(getObject(position).title);

            String imageUrl = getObject(position).getImageUrl();
            if(!imageUrl.equals("")) {
                Picasso.with(GlobalAppContext.getContext()).load(imageUrl).
                        placeholder(R.drawable.placeholder).
                        into(((ChechInCardHolder) holder).imageView);
            }
            else
                (((ChechInCardHolder) holder).imageView).setImageResource(R.drawable.placeholder);

            Story item = getObject(position);
            if(item.more_images!=null)
                ((ChechInCardHolder) holder).webLink.setText(item.more_images);
            else
                ((ChechInCardHolder) holder).webLink.setVisibility(View.GONE);
        }
        if (holder.getItemViewType() == Photo) {
            PhotoCardHolder mholder = (PhotoCardHolder) holder;

            String imageUrl = getObject(position).getImageUrl();
            if(!imageUrl.equals("")) {
                Picasso.with(GlobalAppContext.getContext()).load(imageUrl).
                        placeholder(R.drawable.placeholder).
                        into(((PhotoCardHolder) holder).imageView);
            }
            else
                (((PhotoCardHolder) holder).imageView).setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public long getItemId(int position) {
        return (long)mUserDetail.getCount();
    }

    @Override
    public int getItemCount() {
        return mUserDetail.getCount();
    }


    public Story getObject(int postion){
        return mUserDetail.getItem(postion);
    }

    @Override
    public int getItemViewType(int position) {
        return getObject(position).getItemType();
    }
}
