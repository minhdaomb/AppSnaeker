package com.example.da1_shoppingcart.LoaiGiay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.da1_shoppingcart.CartActivity;
import com.example.da1_shoppingcart.Home.HomeActivity;
import com.example.da1_shoppingcart.MacHangActivity3;
import com.example.da1_shoppingcart.R;
import com.example.da1_shoppingcart.TheLoaiMacHangActivity;
import com.example.da1_shoppingcart.adapter.MacHangViewAdapter;
import com.example.da1_shoppingcart.adapter.MacHangViewAdapter2;
import com.example.da1_shoppingcart.model.MacHang2;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

public class ConverseActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mData;
    private FirebaseStorage mStorage;
    private DatabaseReference mDataNike;

    RecyclerView recyclerView;
    MacHangViewAdapter macHang2Adapter;
    ArrayList<MacHang2> macHangg2List;
    MacHangViewAdapter2 macHangViewAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_converse);
        androidx.appcompat.widget.Toolbar toolbar=findViewById (R.id.my_toolbar);
        drawer=findViewById(R.id.drawer_layout_Converse);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar (toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle
                (this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView=findViewById (R.id.recyclerView_MacHang_Converse);
        mDatabase=FirebaseDatabase.getInstance ();
        mStorage=FirebaseStorage.getInstance ();
        mData=mDatabase.getReference ().child ("MacHang").child ("Converse");
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
        macHangg2List= new ArrayList<MacHang2> ();
        macHangViewAdapter2= new MacHangViewAdapter2 (ConverseActivity.this,macHangg2List);
        recyclerView.setAdapter (macHangViewAdapter2);

        mData.addChildEventListener (new ChildEventListener () {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {
                MacHang2 macHang2 =snapshot.getValue (MacHang2.class);
                macHangg2List.add (macHang2);
                macHangViewAdapter2.notifyDataSetChanged ();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()){
                    case R.id.nav_trangchu:
                        Intent z = new Intent (ConverseActivity.this,HomeActivity.class);
                        startActivity (z);
                        break;
                    case R.id.nav_nike:
                        Intent i = new Intent (ConverseActivity.this,NikeActivity.class);
                        startActivity (i);
                        break;
                    case R.id.nav_converse:
                        Intent a = new Intent (ConverseActivity.this,NikeActivity.class);
                        startActivity (a);
                        break;
                    case R.id.nav_adidas:
                        Intent s = new Intent (ConverseActivity.this,NikeActivity.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater ();
        inflater.inflate (R.menu.them,menu);
        return super.onCreateOptionsMenu (menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt_1:
                Intent i = new Intent (ConverseActivity.this,TheLoaiMacHangActivity.class);
                startActivity (i);
                break;
            case  R.id.otp_2:
                Intent a = new Intent (ConverseActivity.this,MacHangActivity3.class);
                startActivity (a);
                break;
            case R.id.opt_3:
                Intent m = new Intent (ConverseActivity.this,CartActivity.class);
                startActivity (m);
                break;
        }
        return super.onOptionsItemSelected (item);
    }

}