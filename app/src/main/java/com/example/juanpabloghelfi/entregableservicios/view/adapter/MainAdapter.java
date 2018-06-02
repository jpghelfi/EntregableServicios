package com.example.juanpabloghelfi.entregableservicios.view.adapter;

import android.animation.Animator;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.controller.ObrasController;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;

//import com.example.juanpabloghelfi.entregableservicios.view.GlideApp;
//import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class MainAdapter extends RecyclerView.Adapter{

    private List<ObrasDTO> obrasDTO;
    private Context context;
    private LottieAnimationView lottieAnimationView;
    private ResultListener<ObrasDTO> resultListener;

    public MainAdapter(List<ObrasDTO> obrasDTO, ResultListener<ObrasDTO> result) {
        this.obrasDTO = obrasDTO;
        this.resultListener = result;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylcer_cell, parent, false);
        ObrasViewHolder obrasViewHolder = new ObrasViewHolder(view);
        view.setMinimumHeight(parent.getMeasuredHeight()/4);
        return obrasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ObrasDTO obra = this.obrasDTO.get(position);
        ObrasViewHolder obrasViewHolder = (ObrasViewHolder) holder;
        obrasViewHolder.setImageView(obra);
    }

    private class ObrasViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public ObrasViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_id);
            textView = itemView.findViewById(R.id.nombre_pintura);
            lottieAnimationView = itemView.findViewById(R.id.lottie_placeholder);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resultListener.finish(obrasDTO.get(getAdapterPosition()));
                }
            });
        }

        public void setImageView(ObrasDTO obra){

            lottieAnimationView.setVisibility(View.VISIBLE);
            Glide.with(context).using(new FirebaseImageLoader())
                    .load(FirebaseStorage.getInstance().getReference().child(obra.getImage()))
                    .listener(new RequestListener<StorageReference, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, StorageReference model, Target<GlideDrawable> target, boolean isFirstResource) {
                            if (lottieAnimationView != null) {
                                lottieAnimationView.setVisibility(View.GONE);
                            }
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, StorageReference model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            lottieAnimationView.setVisibility(View.GONE);

                            return false;
                        }
                    })
                    .into(imageView);

//            GlideApp.with(context).load(FirebaseStorage.getInstance().getReference().child(obra.getImage()))



            textView.setText(obra.getName());
        }
    }

    @Override
    public int getItemCount() {
        return this.obrasDTO.size();
    }
}
