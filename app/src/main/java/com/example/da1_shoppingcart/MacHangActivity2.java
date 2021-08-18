package com.example.da1_shoppingcart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.da1_shoppingcart.adapter.MacHang2Adapter;
import com.example.da1_shoppingcart.adapter.MacHangAdapter;
import com.example.da1_shoppingcart.model.MacHang2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class MacHangActivity2 extends AppCompatActivity {
    private  FirebaseDatabase mDatabase;
    private DatabaseReference mData;
    private FirebaseStorage mStorage;
    private DatabaseReference mDataNike;
    private DatabaseReference mRef;
    private ImageButton imageButton;
    private EditText edtTenSP,edtMaSp,edtGiaban,edtSoLuong;
    private Button btnInsert;
    private Spinner spnMacHang;
    private static final int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;
    private ArrayList<String> arrayList=new ArrayList<> ();
    RecyclerView recyclerView;
    MacHang2Adapter macHang2Adapter;
    ArrayList<MacHang2> macHangg2List;
    MacHangAdapter adapter,adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_mac_hang2);

//        imageButton= findViewById (R.id.imgButton);
//        edtTenSP=findViewById (R.id.edtTenSP);
//        edtMaSp=findViewById (R.id.edtMaSP);
//        edtGiaban=findViewById (R.id.edtGia);
//        edtSoLuong=findViewById (R.id.edtSL);
//        btnInsert=findViewById (R.id.btnInsert);
//        spnMacHang=findViewById (R.id.spnMaHang);
        recyclerView=findViewById (R.id.recyclerView_MacHang);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
//        showspinner ();

//        progressDialog = new ProgressDialog (this);
//        mDatabase=FirebaseDatabase.getInstance ();
//        mStorage=FirebaseStorage.getInstance ();
//        mData=mDatabase.getReference ().child ("MacHang2");
//        mDataNike=mDatabase.getReference ().child ("MacHang");
//
//        mRef=mDatabase.getReference ();

        FirebaseRecyclerOptions<MacHang2> options = new FirebaseRecyclerOptions.Builder<MacHang2> ()
                .setQuery (FirebaseDatabase.getInstance ().getReference ().child ("MacHang").child ("Nike"),MacHang2.class).build ();
//        adapter=new MacHangAdapter (options);
        recyclerView.setAdapter (adapter);

//        FirebaseRecyclerOptions<MacHang2> options3 = new FirebaseRecyclerOptions.Builder<MacHang2> ()
//                .setQuery (FirebaseDatabase.getInstance ().getReference ().child ("MacHang").child ("Adidas"),MacHang2.class).build ();
//        adapter=new MacHangAdapter (options3);
//        recyclerView.setAdapter (adapter);




//        recyclerView.setHasFixedSize (true);
//        recyclerView.setLayoutManager (new LinearLayoutManager (this));
//        macHangg2List= new ArrayList<MacHang2> ();
//        macHang2Adapter= new MacHang2Adapter (MacHangActivity2.this,macHangg2List);
//        recyclerView.setAdapter (macHang2Adapter);

//        imageButton.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent (Intent.ACTION_GET_CONTENT);
//                intent.setType ("image/*");
//                startActivityForResult (intent,Gallery_Code);
//            }
//        });

//        mDataNike.addChildEventListener (new ChildEventListener () {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {
//                MacHang2 macHang2 =snapshot.getValue (MacHang2.class);
//                macHangg2List.add (macHang2);
//                macHang2Adapter.notifyDataSetChanged ();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        mData.addChildEventListener (new ChildEventListener () {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {
//                MacHang2 macHang2 =snapshot.getValue (MacHang2.class);
//                macHangg2List.add (macHang2);
//                macHang2Adapter.notifyDataSetChanged ();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot,@Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//


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


//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
//        super.onActivityResult (requestCode,resultCode,data);
//        if (requestCode==Gallery_Code && resultCode== RESULT_OK){
//            imageUrl=data.getData ();
//            imageButton.setImageURI (imageUrl);
//        }
//        btnInsert.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//
//                final String TenSp= edtTenSP.getText ().toString ().trim ();
//                final String MaSP= edtMaSp.getText ().toString ().trim ();
//                final String GiaBan=edtGiaban.getText ().toString ().trim ();
//                final String SoLuong = edtSoLuong.getText ().toString ().trim ();
//                final String MaMacHang= spnMacHang.getSelectedItem ().toString ().trim ();
//                final String key=TenSp;
//                if (!(TenSp.isEmpty ()&& MaSP.isEmpty () && imageUrl != null)) //here is the mistake
//                {
//                    progressDialog.setTitle ("UpLoadd...");
//                    progressDialog.show ();
//
//                    StorageReference filepath = mStorage.getReference ().child ("imagePost").child (imageUrl.getLastPathSegment ());
//                    filepath.putFile (imageUrl).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Task<Uri> dowloadUrl=taskSnapshot.getStorage ().getDownloadUrl ().addOnCompleteListener (new OnCompleteListener<Uri> () {
//                                @Override
//                                public void onComplete(@NonNull Task<Uri> task) {
//                                    String t = task.getResult ().toString ();
//                                    DatabaseReference newPost= mRef.child ("MacHang").child (key);
//                                    newPost.child ("TenMacHang").setValue (TenSp);
//                                    newPost.child ("MaSanPham").setValue (MaSP);
//                                    newPost.child ("image").setValue (task.getResult ().toString ());
//                                    newPost.child ("GiaBan").setValue (GiaBan);
//                                    newPost.child ("SoLuong").setValue (SoLuong);
//                                    newPost.child ("MaMacHang").setValue (MaMacHang);
//                                    progressDialog.dismiss ();
//                                    edtTenSP.setText ("");
//                                    edtMaSp.setText ("");
//                                    edtGiaban.setText ("");
//                                    edtSoLuong.setText ("");
//                                    imageButton.setImageResource (R.drawable.cammera);
//                                    Toast.makeText (MacHangActivity2.this,"Thêm thành công!!!",Toast.LENGTH_LONG).show ();
//                                }
//                            });
//                        }
//                    });
//                }
//            }
//        });
//    }
//    private void showspinner(){
//         mRef.child ("TheLoaiMacHang").addValueEventListener (new ValueEventListener () {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                arrayList.clear ();
//                for (DataSnapshot item : snapshot.getChildren ()){
//                    arrayList.add (item.child ("maMatHang").getValue (String.class));
//                }
//                ArrayAdapter<String> arrayAdapter= new ArrayAdapter<> (MacHangActivity2.this,R.layout.style_spinner2,arrayList);
//                spnMacHang.setAdapter (arrayAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }


}