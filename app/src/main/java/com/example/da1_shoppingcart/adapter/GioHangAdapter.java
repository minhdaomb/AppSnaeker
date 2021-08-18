package com.example.da1_shoppingcart.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.da1_shoppingcart.R;
import com.example.da1_shoppingcart.model.GioHang;
import com.example.da1_shoppingcart.model.MacHang2;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GioHangAdapter extends FirebaseRecyclerAdapter<GioHang, GioHangAdapter.MacHangViewHolder> {
    Context context;
    ArrayList<MacHang2> macHang2List;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;


    public GioHangAdapter(@NonNull FirebaseRecyclerOptions<GioHang> options) {
        super (options);

    }

    @NonNull
    @Override
    public MacHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.cardview_cart,parent,false);
        return new MacHangViewHolder (view);

    }

    @Override
    protected void onBindViewHolder(@NonNull final MacHangViewHolder holder,final int position,@NonNull final GioHang model) {
        holder.tvTenSp.setText (model.getTenSanPham ());
        holder.tvMaSp.setText ("Mã: "+model.getMaSanPham ());
        holder.tvGiaSp.setText (model.getGiaSanPham ()+" VNĐ");

        holder.imgdelete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.tvTenSp.getContext ());

                builder
                        // Add action buttons
                        .setPositiveButton("Xóa Giỏ Hàng", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog,int id) {
                                FirebaseDatabase.getInstance ().getReference ()
                                        .child ("Cart")
                                        .child (getRef (position).getKey ())
                                        .removeValue ();


                            }
                        })
                        .setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

            }
        });
    }


    class MacHangViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView,imgdelete;
        TextView tvTenSp, tvMaSp,tvGiaSp;
        public MacHangViewHolder(@NonNull View itemView) {
            super (itemView);
            tvTenSp=itemView.findViewById (R.id.txtTenSanPhamcardView_cart);
            tvMaSp=itemView.findViewById (R.id.txtMaMacHangcardView_cart);
            tvGiaSp=itemView.findViewById (R.id.txtGiaSanPhamcardView_cart);
            cardView=itemView.findViewById (R.id.item_cart);
            imgdelete=itemView.findViewById (R.id.img_delete_cart);
        }
    }
}
