package com.example.juanpabloghelfi.entregableservicios.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.juanpabloghelfi.entregableservicios.dto.ArtistaDTO;
import com.example.juanpabloghelfi.entregableservicios.dto.ContenedorObras;
import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.ServiceObras;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class ObrasDAO {

    private Retrofit retrofit;
    private StorageReference mStorageRef;

    public ObrasDAO(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void getObras(final ResultListener<List<ObrasDTO>> listenerController) {
        ServiceObras serviceObras = retrofit.create(ServiceObras.class);
        Call<ContenedorObras> call = serviceObras.getProductos();
        call.enqueue(new Callback<ContenedorObras>() {
            @Override
            public void onResponse(Call<ContenedorObras> call, Response<ContenedorObras> response) {
                listenerController.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ContenedorObras> call, Throwable t) {

            }
        });
    }

    public void getObrasStorage() throws IOException {

        File localFile = File.createTempFile("image", "jpg");
        mStorageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });
    }

    public void getObrasDB(final ResultListener<List<ObrasDTO>> escuchador) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("dbPaints").child("paints");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            List<ObrasDTO> obrasDTOList = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ObrasDTO obra = snapshot.getValue(ObrasDTO.class);
                    obrasDTOList.add(obra);
                }
                escuchador.finish(obrasDTOList);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void getArtistasDB( final ResultListener<List<ArtistaDTO>> escuchador){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("dbArtistas").child("artists");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            List<ArtistaDTO> artistaDTOList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ArtistaDTO artista = snapshot.getValue(ArtistaDTO.class);
                    artistaDTOList.add(artista);
                }
                escuchador.finish(artistaDTOList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
