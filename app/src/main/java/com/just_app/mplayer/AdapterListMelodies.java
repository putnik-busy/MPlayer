package com.just_app.mplayer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

public class AdapterListMelodies extends BaseAdapter {

    private Context mContext;
    private ArrayList<Model_Melodies.Melodies> items;
    private DisplayImageOptions options;

    public AdapterListMelodies(Context context, ArrayList<Model_Melodies.Melodies> items) {
        mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.listitems, parent, false);
            holder= new ViewHolder();

            holder.nameArtist= (TextView)v.findViewById(R.id.nameArtist);
            holder.nameSounds = (TextView)v.findViewById(R.id.nameSounds);
            holder.cover = (ImageView)v.findViewById(R.id.imageView1);
            v.setTag(holder);
        }else{
            holder = (ViewHolder)v.getTag();
        }
        Model_Melodies.Melodies modelMelodiesMelodies = items.get(position);
        holder.nameArtist.setText(modelMelodiesMelodies.getArtist());
        holder.nameSounds.setText(modelMelodiesMelodies.getTitle());

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        imageLoader.displayImage(modelMelodiesMelodies.getPicUrl(), holder.cover, options);

        return v;
    }

    private static class ViewHolder {
        public ImageView cover;
        public TextView nameArtist;
        public TextView nameSounds;
    }
}

