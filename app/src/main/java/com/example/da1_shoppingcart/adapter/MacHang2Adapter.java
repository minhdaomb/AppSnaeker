package com.example.da1_shoppingcart.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_shoppingcart.R;
import com.example.da1_shoppingcart.model.MacHang2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MacHang2Adapter extends RecyclerView.Adapter<MacHang2Adapter.ViewHodel> {
    Context context;
    ArrayList<MacHang2> macHang2List;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;

    public MacHang2Adapter(Context context,ArrayList<MacHang2> macHang2List) {
        this.context = context;
        this.macHang2List = macHang2List;
        mDatabase=FirebaseDatabase.getInstance ();
        mStorage=FirebaseStorage.getInstance ();
        mRef = mDatabase.getReference ();
    }

    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
//        View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.cardview_them_machang,parent,false);
//            LayoutInflater inflater =((Activity)context).getLayoutInflater ();
//            View v= inflater.inflate (R.layout.cardview_them_machang,parent,false);
        View v;
        v=LayoutInflater.from (context).inflate (R.layout.cardview_them_machang_admin,parent,false);
            final ViewHodel vHowder=new ViewHodel (v);
//            myDialog = new Dialog (context);
//            myDialog.setContentView (R.layout.dialog_xem);
//
//            vHowder.cardView.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View view) {
//                    TextView dialog_tensp=myDialog.findViewById (R.id.TenSp_xem);
//                    ImageView dialog_hinh=myDialog.findViewById (R.id.image_xem);
//                    dialog_tensp.setText (macHang2List. get (vHowder.getAdapterPosition ()).getTenMacHang ());
//                    dialog_hinh.setImageResource (Integer.parseInt (macHang2List.get (vHowder.getAdapterPosition ()).getImage ()));
//                    Toast.makeText (context,"test click"+String.valueOf (vHowder.getAdapterPosition ()),Toast.LENGTH_LONG).show ();
//                    myDialog.show ();
//                }
//            });
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
//        imgCart.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText (context,"đặt hàng thành công",Toast.LENGTH_LONG).show ();
//                String TenSP=tvTenSp.getText ().toString ().trim ();
//                StorageReference filepath = mStorage.getReference ().child ("imagePost").child (imageUri.getLastPathSegment ());
//            }
//        });
        imgCancel.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                dialog.cancel ();
            }
        });

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
//                Toast.makeText (context,"Test click",Toast.LENGTH_LONG).show ();
//                myDialog.show ();
            }
        });
//        holder.imgedit.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                DialogPlus dialog = DialogPlus.newDialog(context)
//                        .setContentHolder(new ViewHolder (R.layout.content))
//                        .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
//                        .create();
//            }
//        });
//        holder.imgdelete.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View view) {
//                mRef.child ("MacHang").child ()
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return macHang2List.size ();
    }

    public static class  ViewHodel extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView,imgedit,imgdelete;
        TextView tvTenSp, tvMaSp,tvGiaSp;
        public ViewHodel(@NonNull View itemView){
            super(itemView);
            imageView=itemView.findViewById (R.id.imgHinhSanPhamcardView);
            imgedit=imageView.findViewById (R.id.img_edit);
            imgdelete=imageView.findViewById (R.id.img_delete);
            tvTenSp=itemView.findViewById (R.id.txtTenSanPhamcardView);
            tvMaSp=itemView.findViewById (R.id.txtMaMacHangcardView);
            tvGiaSp=itemView.findViewById (R.id.txtGiaSanPhamcardView);
            cardView=itemView.findViewById (R.id.item);

        }
    }
}
