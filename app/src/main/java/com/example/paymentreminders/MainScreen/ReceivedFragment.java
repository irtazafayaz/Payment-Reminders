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

public class ReceivedFragment extends BaseFragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<Requests> histories;
    MyAdapter adapter;


    public ReceivedFragment() {
        // Required empty public constructor
    }

    @Override
    public void inOnCreateView(View view) {

        recyclerView = view.findViewById(R.id.received_rec_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        databaseReference = FirebaseDatabase.getInstance().getReference("requests");
        Query query = FirebaseDatabase.getInstance().getReference("requests")
                .orderByChild("borrower_num")
                .equalTo(LoggedUser.currentItem.getPhoneNo());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                histories = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Requests requests = dataSnapshot1.getValue(Requests.class);

                    if(!(requests.getStatus().equalsIgnoreCase("ignored") || requests.getStatus().equalsIgnoreCase("cancelled")))
                        histories.add(requests);

//                    Toast.makeText(context, requests.getBorrower_name(), Toast.LENGTH_SHORT).show();
                }
                adapter = new MyAdapter(getActivity(), histories, picasso, MyAdapter.MY_ADAPTER_TYPE.RECEIVED);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//
//
//        List<Requests> histories = new ArrayList<>();
//
//        histories.add(new Requests("A", "Irtaza", "0310", "Laweezah", "0310", "310", "02 Aug 2018", "7:30 PM", "aaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Yesterday Lunch", "requested"));
//        histories.add(new Requests("a", "Irtaza", "0310", "Laweezah", "0310", "310", "02 Aug 2018", "7:30 PM", "aaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Yesterday Lunch", "pending"));
//        histories.add(new Requests("a", "Irtaza", "0310", "Laweezah", "0310", "310", "02 Aug 2018", "7:30 PM", "aaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Yesterday Lunch", "paid"));
//        histories.add(new Requests("a", "Irtaza", "0310", "Laweezah", "0310", "310", "02 Aug 2018", "7:30 PM", "aaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Yesterday Lunch", "requested"));
//        histories.add(new Requests("a", "Irtaza", "0310", "Laweezah", "0310", "310", "02 Aug 2018", "7:30 PM", "aaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Yesterday Lunch", "pending"));
//        histories.add(new Requests("a", "Irtaza", "0310", "Laweezah", "0310", "310", "02 Aug 2018", "7:30 PM", "aaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Yesterday Lunch", "paid"));
//
//
//        //        histories.add(new TransactionHistory("Today", "Irtaza", "Yesterday Lunch", "02 Aug 2018", "7:30 PM", "aa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 3, 20));
////        histories.add(new TransactionHistory("Today", "Irtaza", "Yesterday Lunch", "02 Aug 2018", "7:30 PM", "aa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 3, 20));
////        histories.add(new TransactionHistory("Today", "Irtaza", "Yesterday Lunch", "02 Aug 2018", "7:30 PM", "aa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 3, 20));
//
//        MyAdapter adapter = new MyAdapter(getActivity(), histories, picasso, MyAdapter.MY_ADAPTER_TYPE.RECEIVED);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_received;
    }
}


//                Query query = FirebaseDatabase.getInstance().getReference("users")
//                        .orderByChild("username")
//                        .equalTo("irtazafayaz");
//                query.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                            Users users = dataSnapshot1.getValue(Users.class);
//
//                            Toast.makeText(LoginActivity.this, users.getEmail(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
