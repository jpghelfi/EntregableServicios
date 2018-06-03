package com.example.juanpabloghelfi.entregableservicios.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {

    public static final String OBRA_DTO = "OBRA_DTO" ;
    ObrasDTO obrasDTO;
    TextView nombre;
    TextView nombreArtista;
    ImageView image;
    LottieAnimationView lottieAnimationView;


    public DetalleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        this.nombre = view.findViewById(R.id.nombre_obra);
        this.nombreArtista = view.findViewById(R.id.nombre_artista);
        this.image = view.findViewById(R.id.image_id);
        this.lottieAnimationView = view.findViewById(R.id.lottie);

        lottieAnimationView.playAnimation();

        obrasDTO = (ObrasDTO) getArguments().getSerializable(OBRA_DTO);

        this.nombre.setText(obrasDTO.getName());
        this.nombreArtista.setText(obrasDTO.getArtista().getName());
        Glide.with(getContext()).using(new FirebaseImageLoader())
                .load(FirebaseStorage.getInstance().getReference().child(obrasDTO.getImage()))
                .listener(new RequestListener<StorageReference, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, StorageReference model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, StorageReference model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        lottieAnimationView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(this.image);
        return view;
    }

}
