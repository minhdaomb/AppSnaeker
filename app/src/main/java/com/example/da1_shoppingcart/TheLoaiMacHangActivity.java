package com.example.da1_shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.da1_shoppingcart.model.TheLoaiMacHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TheLoaiMacHangActivity extends AppCompatActivity {
    EditText  edtmaTL,edttenTL,edtmoTa;
    Button btnThem;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance ();
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_the_loai_mac_hang);

        edtmaTL=findViewById (R.id.edtmaTheLoai);
        edttenTL=findViewById (R.id.edttenTheLoai);
        edtmoTa=findViewById (R.id.edtmoTa);
        progressBar=findViewById (R.id.progressBar2);
        btnThem=findViewById (R.id.btnThemTheLoai);
        database= FirebaseDatabase.getInstance ();
        back=findViewById (R.id.backQLTL);
        back.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (TheLoaiMacHangActivity.this,MacHangActivity3.class);
                startActivity (i);
                finish ();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart ();
        btnThem.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String matl = edtmaTL.getText ().toString ().trim ();
                String tentl = edttenTL.getText ().toString ().trim ();
                String mota = edtmoTa.getText ().toString ().trim ();
                insertData(edtmaTL.getText ().toString (),tentl = edttenTL.getText ().toString (),mota = edtmoTa.getText ().toString ());
                Toast.makeText (TheLoaiMacHangActivity.this,"Thêm Thành Công !!",Toast.LENGTH_LONG).show ();
                edtmaTL.setText ("");
                edtmoTa.setText ("");
                edttenTL.setText ("");
            }
        });
    }
    public void insertData(String matl, String tentl,String mota){
        String key = tentl;
        DatabaseReference reference= database.getReference ().child ("TheLoaiMacHang").child (key);
        TheLoaiMacHang theLoaiMacHang= new TheLoaiMacHang (matl,tentl,mota);
        reference.setValue (theLoaiMacHang);
    }
//    private void ThemTheLoai() {
//        String matl = edtmaTL.getText ().toString ().trim ();
//        String tentl = edttenTL.getText ().toString ().trim ();
//        String mota = edtmoTa.getText ().toString ().trim ();
//        progressBar.setVisibility (View.GONE);
//    }

}