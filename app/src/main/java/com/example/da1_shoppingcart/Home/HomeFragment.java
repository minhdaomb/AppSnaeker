package com.example.da1_shoppingcart.Home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_shoppingcart.MacHangActivity2;
import com.example.da1_shoppingcart.R;
import com.example.da1_shoppingcart.adapter.MacHang2Adapter;
import com.example.da1_shoppingcart.model.MacHang2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mData;
    private FirebaseStorage mStorage;
    private RecyclerView recyclerView;
    ArrayList<MacHang2> macHangg2List;
    MacHang2Adapter macHang2Adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mDatabase=FirebaseDatabase.getInstance ();
        mStorage=FirebaseStorage.getInstance ();
        mData=mDatabase.getReference ().child ("MacHang2");
        recyclerView=view.findViewById (R.id.recyclerView_MacHang_frm_home);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()));
        macHangg2List= new ArrayList<MacHang2> ();
//        macHang2Adapter= new MacHang2Adapter (HomeFragment.this,macHangg2List);
        recyclerView.setAdapter (macHang2Adapter);


        // STATUS BAR COLOR
        Activity activity = (Activity) view.getContext();
        Window window = activity.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(view.getContext(),R.color.white_color));

        return view;
    }
}