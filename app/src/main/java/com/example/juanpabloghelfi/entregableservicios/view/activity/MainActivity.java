package com.example.juanpabloghelfi.entregableservicios.view.activity;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.dao.ObrasDAO;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;
import com.example.juanpabloghelfi.entregableservicios.view.fragment.DetalleFragment;
import com.example.juanpabloghelfi.entregableservicios.view.fragment.MainFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

import static com.example.juanpabloghelfi.entregableservicios.view.fragment.DetalleFragment.OBRA_DTO;


public class MainActivity extends AppCompatActivity implements MainFragment.Result {

    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        getFragmentManager().beginTransaction().replace(R.id.mainFragment, new MainFragment()).commit();


        NavigationView navigationView = findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        menuItem.setChecked(true);
                        if(menuItem.getItemId() == R.id.login){
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);

                        }else if(menuItem.getItemId() == R.id.home){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });

    }

    @Override
    public void result(ObrasDTO obrasDTO) {
        Intent intent = new Intent(getApplicationContext(), DetalleActivity.class);
        intent.putExtra( OBRA_DTO, obrasDTO);
        startActivity(intent);
    }
}
