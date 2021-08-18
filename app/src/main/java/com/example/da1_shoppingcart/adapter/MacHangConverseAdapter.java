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

public class MacHangConverseAdapter extends FirebaseRecyclerAdapter<MacHang2, MacHangConverseAdapter.MacHangViewHolder> {
    Context context;
    ArrayList<MacHang2> macHang2List;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;


    public MacHangConverseAdapter(@NonNull FirebaseRecyclerOptions<MacHang2> options) {
        super (options);

    }

    @NonNull
    @Override
    public MacHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.cardview_them_machang_admin,parent,false);
        return new MacHangViewHolder (view);

    }

    @Override
    protected void onBindViewHolder(@NonNull final MacHangViewHolder holder,final int position,@NonNull final MacHang2 model) {
        holder.tvTenSp.setText (model.getTenMacHang ());
        holder.tvMaSp.setText ("Mã: "+model.getMaSanPham ());
        holder.tvGiaSp.setText (model.getGiaBan ()+" VNĐ");
        Glide.with (holder.imageView.getContext ()).load (model.getImage ()).into (holder.imageView);

        holder.imgdelete.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(holder.imageView.getContext ());

                builder
                        // Add action buttons
                        .setPositiveButton("Xóa Mặc Hàng", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog,int id) {
                                FirebaseDatabase.getInstance ().getReference ()
                                        .child ("MacHang").child ("Converse")
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
        holder.imgedit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                final DialogPlus dialog = DialogPlus.newDialog (holder.imageView.getContext ())
                        .setContentHolder (new ViewHolder (R.layout.dialog_edit))
                        .setExpanded (false)
                        .create ();
                dialog.show ();
                View hoder=(LinearLayout)dialog.getHolderView ();
                final EditText tensp=hoder.findViewById (R.id.TenSp_edit);
                final EditText masp=hoder.findViewById (R.id.MaSP_edit);
                final EditText giasp=hoder.findViewById (R.id.GiaSP_edit);
                final EditText soluongsp=hoder.findViewById (R.id.SoLuongSP_edit);
                final EditText mamachang=hoder.findViewById (R.id.MaMacHang_edit);
                ImageView img=hoder.findViewById (R.id.image_edit);
                ImageView imgcancel=hoder.findViewById (R.id.imgCancel_edit);
                Button btnedit=hoder.findViewById (R.id.btn_edit);
                imgcancel.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss ();
                    }
                });
                tensp.setText (model.getTenMacHang ());
                masp.setText (model.getMaSanPham ());
                giasp.setText (model.getGiaBan ());
                soluongsp.setText (model.getSoLuong ());
                mamachang.setText (model.getMaMacHang ());
                Glide.with (holder.imageView.getContext ()).load (model.getImage ()).into (img);
                btnedit.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<> ();
                        map.put ("TenMacHang",tensp.getText ().toString ());
                        map.put ("MaSanPham",masp.getText ().toString ());
                        map.put ("MaMacHang",mamachang.getText ().toString ());
                        map.put ("GiaBan",giasp.getText ().toString ());
                        map.put ("SoLuong",soluongsp.getText ().toString ());

                        FirebaseDatabase.getInstance ().getReference ().child ("MacHang").child ("Converse")
                                .child (getRef (position).getKey ()).updateChildren (map)
                                .addOnSuccessListener (new OnSuccessListener<Void> () {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialog.dismiss ();
                                    }
                                })
                                .addOnFailureListener (new OnFailureListener () {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss ();
                                    }
                                });
                    }
                });

            }
        });

    }


    class MacHangViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView,imgedit,imgdelete;
        TextView tvTenSp, tvMaSp,tvGiaSp;
        public MacHangViewHolder(@NonNull View itemView) {
            super (itemView);
            imageView=itemView.findViewById (R.id.imgHinhSanPhamcardView);
            imgedit=itemView.findViewById (R.id.img_edit);
            imgdelete=itemView.findViewById (R.id.img_delete);
            tvTenSp=itemView.findViewById (R.id.txtTenSanPhamcardView);
            tvMaSp=itemView.findViewById (R.id.txtMaMacHangcardView);
            tvGiaSp=itemView.findViewById (R.id.txtGiaSanPhamcardView);
            cardView=itemView.findViewById (R.id.item);
        }
    }
}
