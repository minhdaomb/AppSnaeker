package com.example.da1_shoppingcart.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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

import com.example.da1_shoppingcart.R;
import com.example.da1_shoppingcart.model.GioHang;
import com.example.da1_shoppingcart.model.MacHang2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHodel> {
    Context context;
    public static ArrayList<GioHang> gioHangArrayList;
    Dialog myDialog;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private FirebaseDatabase database;

    public CartAdapter(Context context,ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
        mDatabase=FirebaseDatabase.getInstance ();
        mStorage=FirebaseStorage.getInstance ();
        mRef = mDatabase.getReference ();
        database= FirebaseDatabase.getInstance ();
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View v;
        v=LayoutInflater.from (context).inflate (R.layout.cardview_cart,parent,false);
            final ViewHodel vHowder=new ViewHodel (v);
            return vHowder;

    }
//    private void dialogXem(MacHang2 mh){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = ((Activity)context).getLayoutInflater ();
//        View view = inflater.inflate(R.layout.dialog_xem,null);
//        builder.setView(view);
//        final ImageView imgHinh= view.findViewById (R.id.image_xem);
//        final TextView tvTenSp=view.findViewById (R.id.TenSp_xem);
//        final TextView tvGiaSP=view.findViewById (R.id.GiaSP_xem);
//        final TextView tvMaSP=view.findViewById (R.id.MaSP_xem);
//        final ImageView imgCart=view.findViewById (R.id.imgcart);
//        final ImageView imgCancel=view.findViewById (R.id.imgCancel);
//        String imageUri=null;
//        imageUri = mh.getImage ();
//        tvTenSp.setText (mh.getTenMacHang ());
//        tvGiaSP.setText (mh.getGiaBan ()+" VNĐ");
//        tvMaSP.setText ("Mã giày: "+mh.getMaSanPham ());
//        Picasso.with (context).load (imageUri).into (imgHinh);
//        final Dialog dialog= builder.create();
//        dialog.show();
//        imgCart.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText (context,"đặt hàng thành công",Toast.LENGTH_LONG).show ();
//                String TenSP=tvTenSp.getText ().toString ().trim ();
//                String GiaSP=tvGiaSP.getText ().toString ().trim ();
//                String MaSP=tvMaSP.getText ().toString ().trim ();
//                insertData(tvTenSp.getText ().toString (),GiaSP = tvGiaSP.getText ().toString (),MaSP = tvMaSP.getText ().toString ());
//
//            }
//        });
//        imgCancel.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel ();
//            }
//        });
//    }
//    public void insertData(String TenSP, String GiaSP,String MaSp){
//        String key = TenSP;
//        DatabaseReference reference= database.getReference ().child ("Cart").child (key);
//        GioHang gioHang= new GioHang (TenSP,GiaSP,MaSp);
//        reference.setValue (gioHang);
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder,final int position) {
        GioHang gioHang= gioHangArrayList.get (position);
        holder.tvTenSp.setText (gioHang.getTenSanPham ());
        holder.tvMaSp.setText ("Mã: "+gioHang.getMaSanPham ());
        holder.tvGiaSp.setText (gioHang.getGiaSanPham ()+" VNĐ");
//        String imageUri=null;
//        imageUri = macHang2.getImage ();
//        Picasso.with (context).load (imageUri).into (holder.imageView);
//        holder.cardView.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                dialogXem (gioHangArrayList.get (position));
////                Toast.makeText (context,"Test click",Toast.LENGTH_LONG).show ();
////                myDialog.show ();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return gioHangArrayList.size ();
    }

    public static class  ViewHodel extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;
        TextView tvTenSp, tvMaSp,tvGiaSp;
        public ViewHodel(@NonNull View itemView){
            super(itemView);

            tvTenSp=itemView.findViewById (R.id.txtTenSanPhamcardView_cart);
            tvMaSp=itemView.findViewById (R.id.txtMaMacHangcardView_cart);
            tvGiaSp=itemView.findViewById (R.id.txtGiaSanPhamcardView_cart);
            cardView=itemView.findViewById (R.id.item_cart);

        }
    }
}
