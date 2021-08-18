package com.example.da1_shoppingcart.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_shoppingcart.CartActivity;
import com.example.da1_shoppingcart.R;
import com.example.da1_shoppingcart.model.GioHang;
import com.example.da1_shoppingcart.model.MacHang2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MacHangViewAdapter2 extends RecyclerView.Adapter<MacHangViewAdapter2.ViewHodel> {
    Context context;
    ArrayList<MacHang2> macHang2List;
    Dialog myDialog;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private FirebaseDatabase database;
    private DatabaseReference mData;

    public MacHangViewAdapter2(Context context,ArrayList<MacHang2> macHang2List) {
        this.context = context;
        this.macHang2List = macHang2List;
        mDatabase=FirebaseDatabase.getInstance ();
        mStorage=FirebaseStorage.getInstance ();
        mRef = mDatabase.getReference ();

        database= FirebaseDatabase.getInstance ();
        mData=mDatabase.getReference ().child ("MacHang");
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View v;
        v=LayoutInflater.from (context).inflate (R.layout.cardview_them_machang,parent,false);
            final ViewHodel vHowder=new ViewHodel (v);
            return vHowder;

    }
    private void dialogXem(MacHang2 mh){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater ();
        View view = inflater.inflate(R.layout.dialog_xem,null);
        builder.setView(view);
        final ImageView imgHinh= view.findViewById (R.id.image_xem);
        final TextView tvTenSp=view.findViewById (R.id.TenSp_xem);
        final TextView tvGiaSP=view.findViewById (R.id.GiaSP_xem);
        final TextView tvMaSP=view.findViewById (R.id.MaSP_xem);
        final ImageView imgCart=view.findViewById (R.id.imgcart);
        final ImageView imgCancel=view.findViewById (R.id.imgCancel);
        String imageUri=null;
        imageUri = mh.getImage ();
        tvTenSp.setText (mh.getTenMacHang ());
        tvGiaSP.setText (mh.getGiaBan ());
        tvMaSP.setText (mh.getMaSanPham ());
        Picasso.with (context).load (imageUri).into (imgHinh);
        final Dialog dialog= builder.create();
        dialog.show();
        imgCart.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Toast.makeText (context,"đặt hàng thành công",Toast.LENGTH_LONG).show ();
                String TenSP=tvTenSp.getText ().toString ().trim ();
                String GiaSP=tvGiaSP.getText ().toString ().trim ();
                String MaSP=tvMaSP.getText ().toString ().trim ();
                insertData(tvTenSp.getText ().toString (),GiaSP = tvGiaSP.getText ().toString (),MaSP = tvMaSP.getText ().toString ());
                dialog.cancel ();
                Intent i = new Intent (context,CartActivity.class);
                context.startActivity (i);

            }
        });
        imgCancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                dialog.cancel ();
            }
        });
    }
    public void insertData(String TenSP, String GiaSP,String MaSp){
        String key = TenSP;
        DatabaseReference reference= database.getReference ().child ("Cart").child (key);
        GioHang gioHang= new GioHang (TenSP,GiaSP,MaSp);
        reference.setValue (gioHang);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder,final int position) {
        MacHang2 macHang2= macHang2List.get (position);
        holder.tvTenSp.setText (macHang2.getTenMacHang ());
        holder.tvMaSp.setText ("Mã: "+macHang2.getMaSanPham ());
        holder.tvGiaSp.setText (macHang2.getGiaBan ()+" VNĐ");
        String imageUri=null;
        imageUri = macHang2.getImage ();
        Picasso.with (context).load (imageUri).into (holder.imageView);
        holder.cardView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                dialogXem (macHang2List.get (position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return macHang2List.size ();
    }

    public static class  ViewHodel extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;
        TextView tvTenSp, tvMaSp,tvGiaSp;
        public ViewHodel(@NonNull View itemView){
            super(itemView);
            imageView=itemView.findViewById (R.id.imgHinhSanPhamcardView);
            tvTenSp=itemView.findViewById (R.id.txtTenSanPhamcardView);
            tvMaSp=itemView.findViewById (R.id.txtMaMacHangcardView);
            tvGiaSp=itemView.findViewById (R.id.txtGiaSanPhamcardView);
            cardView=itemView.findViewById (R.id.item);

        }
    }
}

