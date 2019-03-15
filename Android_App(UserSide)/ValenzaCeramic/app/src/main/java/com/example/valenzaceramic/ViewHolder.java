package com.example.valenzaceramic;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

    }

    public void setDetails(Context context,String name,String type,String image)
    {
        TextView mNameView =mView.findViewById(R.id.rName);
        TextView mTypeView =mView.findViewById(R.id.rType);
        ImageView mImageView = mView.findViewById(R.id.rImage);

        mNameView.setText(name);
        mTypeView.setText(type);
        Picasso.get().load(image).into(mImageView);


    }
}
