package com.example.juanpabloghelfi.entregableservicios.view.activity;


import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;
import com.example.juanpabloghelfi.entregableservicios.view.fragment.MainFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.mainFragment, new MainFragment()).commit();

    }


}
