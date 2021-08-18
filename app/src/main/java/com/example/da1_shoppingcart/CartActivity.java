package com.example.da1_shoppingcart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.da1_shoppingcart.Home.HomeActivity;
import com.example.da1_shoppingcart.LoaiGiay.AdidasActivity;
import com.example.da1_shoppingcart.LoaiGiay.ConverseActivity;
import com.example.da1_shoppingcart.LoaiGiay.NikeActivity;
import com.example.da1_shoppingcart.adapter.CartAdapter;
import com.example.da1_shoppingcart.adapter.GioHangAdapter;
import com.example.da1_shoppingcart.adapter.MacHangAdidasAdapter;
import com.example.da1_shoppingcart.adapter.MacHangViewAdapter;
import com.example.da1_shoppingcart.model.GioHang;
import com.example.da1_shoppingcart.model.MacHang2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GioHangAdapter adapter;
    TextView tvTongTien;
    DrawerLayout drawer;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_cart);
        androidx.appcompat.widget.Toolbar toolbar=findViewById (R.id.my_toolbar);
        drawer=findViewById(R.id.drawer_layout_Cart);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar (toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle
                (this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView=findViewById (R.id.recyclerView_Cart);
        tvTongTien=findViewById (R.id.tvTongTien);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));

        FirebaseRecyclerOptions<GioHang> options2 = new FirebaseRecyclerOptions.Builder<GioHang> ()
                .setQuery (FirebaseDatabase.getInstance ().getReference ().child ("Cart"),GioHang.class).build ();
        adapter= new GioHangAdapter (options2);
        recyclerView.setAdapter (adapter);

        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()){
                    case R.id.nav_trangchu:
                        Intent z = new Intent (CartActivity.this,HomeActivity.class);
                        startActivity (z);
                        break;
                    case R.id.nav_nike:
                        Intent i = new Intent (CartActivity.this,NikeActivity.class);
                        startActivity (i);
                        break;
                    case R.id.nav_converse:
                        Intent a = new Intent (CartActivity.this,ConverseActivity.class);
                        startActivity (a);
                        break;
                    case R.id.nav_adidas:
                        Intent s = new Intent (CartActivity.this,AdidasActivity.class);
                        startActivity (s);
                        break;

                }
                navigationView.setCheckedItem (item.getItemId ());
                drawer.closeDrawer (GravityCompat.START);
                return false;
            }
        });
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
    private void EvenUltil(){
//        int tongtien=0;
//        for (int i =0;i<=GioHangList.size ();i++){
//            tongtien+= Integer.parseInt (GioHangList.get (i).getGiaSanPham ());
//        }
//        DecimalFormat decimalFormat = new DecimalFormat ("###,###,###");
//        tvTongTien.setText (decimalFormat.format (tongtien)+ " VNĐ");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater ();
        inflater.inflate (R.menu.them,menu);
        return super.onCreateOptionsMenu (menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt_1:
                Intent i = new Intent (CartActivity.this,TheLoaiMacHangActivity.class);
                startActivity (i);
                break;
            case  R.id.otp_2:
                Intent a = new Intent (CartActivity.this,MacHangActivity3.class);
                startActivity (a);
                break;
            case R.id.opt_3:
                Intent m = new Intent (CartActivity.this,CartActivity.class);
                startActivity (m);
                break;
        }
        return super.onOptionsItemSelected (item);
    }
}