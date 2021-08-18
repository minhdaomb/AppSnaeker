package com.example.da1_shoppingcart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_shoppingcart.Home.HomeActivity;
import com.example.da1_shoppingcart.LoaiGiay.AdidasActivity;
import com.example.da1_shoppingcart.LoaiGiay.NikeActivity;
import com.example.da1_shoppingcart.adapter.MacHang2Adapter;
import com.example.da1_shoppingcart.model.MacHang2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class MacHangActivity3 extends AppCompatActivity {
    private  FirebaseDatabase mDatabase;
    DrawerLayout drawer;
    NavigationView navigationView;
    private DatabaseReference mData;
    private FirebaseStorage mStorage;
    private DatabaseReference mDataNike;
    private DatabaseReference mDataAdidas;
    private DatabaseReference mRef;
    private TextView tvShow,tvshowNike,tvShowAdidas;
    private ImageButton imageButton;
    private EditText edtTenSP,edtMaSp,edtGiaban,edtSoLuong;
    private Button btnInsert;
    private Spinner spnMacHang;
    private static final int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;
    private ArrayList<String> arrayList=new ArrayList<> ();
    ArrayList<MacHang2> macHangg2List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_mac_hang3);
        androidx.appcompat.widget.Toolbar toolbar=findViewById (R.id.my_toolbar);
        drawer=findViewById(R.id.drawer_layout_Them);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar (toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle
                (this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        imageButton= findViewById (R.id.imgButton);
        edtTenSP=findViewById (R.id.edtTenSP);
        edtMaSp=findViewById (R.id.edtMaSP);
        edtGiaban=findViewById (R.id.edtGia);
        edtSoLuong=findViewById (R.id.edtSL);
        btnInsert=findViewById (R.id.btnInsert);
        spnMacHang=findViewById (R.id.spnMaHang);
        tvShow=findViewById (R.id.tvShowConverse);
        tvshowNike=findViewById (R.id.tvShowNike);
        tvShowAdidas=findViewById (R.id.tvShowAdidas);
        tvShowAdidas.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent z = new Intent (MacHangActivity3.this,MacHangAdidasAdminActivity.class);
                startActivity (z);
            }
        });
        tvshowNike.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent o = new Intent (MacHangActivity3.this,MacHangNikeAdminActivity.class);
                startActivity (o);
            }
        });
        tvShow.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MacHangActivity3.this,MacHangConverseAdminActivity.class);
                startActivity (i);
            }
        });

        progressDialog = new ProgressDialog (this);
        mDatabase=FirebaseDatabase.getInstance ();
        mStorage=FirebaseStorage.getInstance ();
        mData=mDatabase.getReference ().child ("MacHang").child ("Converse");
        mDataNike=mDatabase.getReference ().child ("MacHang").child ("Nike");
        mDataAdidas=mDatabase.getReference ().child ("MacHang").child ("Adidas");
        mRef=mDatabase.getReference ();
        showspinner ();
        macHangg2List= new ArrayList<MacHang2> ();
        imageButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Intent.ACTION_GET_CONTENT);
                intent.setType ("image/*");
                startActivityForResult (intent,Gallery_Code);
            }
        });

        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId ()){
                    case R.id.nav_trangchu:
                        Intent z = new Intent (MacHangActivity3.this,HomeActivity.class);
                        startActivity (z);
                        break;
                    case R.id.nav_nike:
                        Intent i = new Intent (MacHangActivity3.this,NikeActivity.class);
                        startActivity (i);
                        break;
                    case R.id.nav_converse:
                        Intent a = new Intent (MacHangActivity3.this,NikeActivity.class);
                        startActivity (a);
                        break;
                    case R.id.nav_adidas:
                        Intent s = new Intent (MacHangActivity3.this,NikeActivity.class);
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
                Intent i = new Intent (MacHangActivity3.this,TheLoaiMacHangActivity.class);
                startActivity (i);
                break;
            case  R.id.otp_2:
                Intent a = new Intent (MacHangActivity3.this,MacHangActivity3.class);
                startActivity (a);
                break;
            case R.id.opt_3:
                Intent m = new Intent (MacHangActivity3.this,CartActivity.class);
                startActivity (m);
                break;
        }
        return super.onOptionsItemSelected (item);
    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult (requestCode,resultCode,data);
        if (requestCode==Gallery_Code && resultCode== RESULT_OK){
            imageUrl=data.getData ();
            imageButton.setImageURI (imageUrl);
        }
        btnInsert.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                final String TenSp= edtTenSP.getText ().toString ().trim ();
                final String MaSP= edtMaSp.getText ().toString ().trim ();
                final String GiaBan=edtGiaban.getText ().toString ().trim ();
                final String SoLuong = edtSoLuong.getText ().toString ().trim ();
                final String MaMacHang= spnMacHang.getSelectedItem ().toString ().trim ();
                final String key=TenSp;
                final String key2= MaMacHang;
                if (!(TenSp.isEmpty ()&& MaSP.isEmpty () && imageUrl != null)) //here is the mistake
                {
                    progressDialog.setTitle ("UpLoadd...");
                    progressDialog.show ();

                    StorageReference filepath = mStorage.getReference ().child ("imagePost").child (imageUrl.getLastPathSegment ());
                    filepath.putFile (imageUrl).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> dowloadUrl=taskSnapshot.getStorage ().getDownloadUrl ().addOnCompleteListener (new OnCompleteListener<Uri> () {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult ().toString ();
                                    DatabaseReference newPost= mRef.child ("MacHang").child (key2).child (key);
                                    newPost.child ("TenMacHang").setValue (TenSp);
                                    newPost.child ("MaSanPham").setValue (MaSP);
                                    newPost.child ("image").setValue (task.getResult ().toString ());
                                    newPost.child ("GiaBan").setValue (GiaBan);
                                    newPost.child ("SoLuong").setValue (SoLuong);
                                    newPost.child ("MaMacHang").setValue (MaMacHang);
                                    progressDialog.dismiss ();
                                    edtTenSP.setText ("");
                                    edtMaSp.setText ("");
                                    edtGiaban.setText ("");
                                    edtSoLuong.setText ("");
                                    imageButton.setImageResource (R.drawable.cammera);
                                    Toast.makeText (MacHangActivity3.this,"Thêm thành công!!!",Toast.LENGTH_LONG).show ();
                                }
                            });
                        }
                    });
                }
            }
        });
    }
    private void showspinner(){
         mRef.child ("TheLoaiMacHang").addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear ();
                for (DataSnapshot item : snapshot.getChildren ()){
                    arrayList.add (item.child ("tenMatHang").getValue (String.class));
                }
                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<> (MacHangActivity3.this,R.layout.style_spinner2,arrayList);
                spnMacHang.setAdapter (arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}