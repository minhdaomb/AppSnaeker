package com.example.da1_shoppingcart;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_shoppingcart.adapter.MacHang2Adapter;
import com.example.da1_shoppingcart.adapter.MacHangAdapter;
import com.example.da1_shoppingcart.adapter.MacHangAdidasAdapter;
import com.example.da1_shoppingcart.model.MacHang2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MacHangAdidasAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MacHangAdidasAdapter adapter,adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_mac_hang2);
        androidx.appcompat.widget.Toolbar toolbar=findViewById (R.id.my_toolbar);
        setSupportActionBar (toolbar);
        toolbar.setNavigationIcon (R.drawable.ic_keyboard);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setDisplayShowCustomEnabled (true);
        recyclerView=findViewById (R.id.recyclerView_MacHang);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));

        FirebaseRecyclerOptions<MacHang2> options2 = new FirebaseRecyclerOptions.Builder<MacHang2> ()
                .setQuery (FirebaseDatabase.getInstance ().getReference ().child ("MacHang").child ("Adidas"),MacHang2.class).build ();
        adapter=new MacHangAdidasAdapter (options2);
        recyclerView.setAdapter (adapter);

    }
    @Override
    protected void onStart(){
        super.onStart ();
        adapter.startListening ();
    }

    @Override
    protected void onStop(){
        super.onStop ();
        adapter.stopListening ();
    }



}