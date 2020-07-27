package com.example.paymentreminders.MainScreen;


import android.app.Fragment;
import android.view.View;

import com.example.paymentreminders.BaseFragment;
import com.example.paymentreminders.Interface.LoggedUser;
import com.example.paymentreminders.Model.Requests;
import com.example.paymentreminders.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends BaseFragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<Requests> histories;
    MyAdapter adapter;


    public RequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void inOnCreateView(View view) {
        recyclerView = view.findViewById(R.id.request_rec_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        databaseReference = FirebaseDatabase.getInstance().getReference("requests");
        Query query = FirebaseDatabase.getInstance().getReference("requests")
                .orderByChild("giver_num")
                .equalTo(LoggedUser.currentItem.getPhoneNo());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                histories = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Requests requests = dataSnapshot1.getValue(Requests.class);
//                    Toast.makeText(context, requests.getBorrower_name(), Toast.LENGTH_SHORT).show();
                    if(!(requests.getStatus().equalsIgnoreCase("cancelled")))
                        histories.add(requests);
                }
                adapter = new MyAdapter(getActivity(), histories, picasso, MyAdapter.MY_ADAPTER_TYPE.REQUESTED);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        databaseReference.push().child("giver_num").equalTo("03104189309").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                histories = new ArrayList<>();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    Requests requests = dataSnapshot1.getValue(Requests.class);
//                    Toast.makeText(context, requests.getBorrower_name(), Toast.LENGTH_SHORT).show();
//                    histories.add(requests);
//                }
//                adapter = new MyAdapter(getActivity(), histories, picasso, MyAdapter.MY_ADAPTER_TYPE.REQUESTED);
//                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_request;
    }

}
