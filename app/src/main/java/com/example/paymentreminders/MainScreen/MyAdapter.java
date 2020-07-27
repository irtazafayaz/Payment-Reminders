package com.example.paymentreminders.MainScreen;


import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paymentreminders.Model.Requests;
import com.example.paymentreminders.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.BaseHolderClass> {


    public enum MY_ADAPTER_TYPE {
        REQUESTED,
        RECEIVED
    }

    private static final int IMAGE_DIMENSION = 70;

    DatabaseReference databaseReference;
    private Picasso picasso;
    private LayoutInflater layoutInflater;
    private List<Requests> requestsList;
    private Context context;
    private MY_ADAPTER_TYPE my_adapter_type;

    MyAdapter(Context context, List<Requests> data, Picasso picasso, MY_ADAPTER_TYPE my_adapter_type) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.picasso = picasso;
        requestsList = data;
        this.my_adapter_type = my_adapter_type;
    }


    @NonNull
    @Override
    public MyAdapter.BaseHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new MySimpleViewHolder(layoutInflater.inflate(R.layout.layout_simple, parent, false), picasso);
            default: {
                if (my_adapter_type == MY_ADAPTER_TYPE.REQUESTED) {
                    return new MyRequestViewHolder(layoutInflater.inflate(R.layout.layout_request, parent, false), picasso);
                } else {
                    return new MyReceivedViewHolder(layoutInflater.inflate(R.layout.layout_received, parent, false), picasso);
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.BaseHolderClass holder, int position) {
        Requests transactionHistory = requestsList.get(position);
        String price = "Rs " + transactionHistory.getAmount();
        String status = "Status : " + transactionHistory.getStatus();
        String details = "Details :  " + transactionHistory.getDescription();
        String title = "Title : " + transactionHistory.getTitle();
        String date = "Date : " + transactionHistory.getDate() + " " + transactionHistory.getTime();
        String dayAndTimeAgo = "TODAY" + " - " + "3" + "Mins Ago";


        if (holder instanceof MyReceivedViewHolder) {

            MyReceivedViewHolder viewHolder0 = (MyReceivedViewHolder) holder;


            viewHolder0.simple_title.setText(title, TextView.BufferType.SPANNABLE);
            viewHolder0.re_day_timeAgo.setText(dayAndTimeAgo);
            viewHolder0.simple_name.setText(transactionHistory.getGiver_name());
            viewHolder0.simple_price_value.setText(price);
            viewHolder0.simple_status.setText(status, TextView.BufferType.SPANNABLE);
            viewHolder0.re_details.setText(details, TextView.BufferType.SPANNABLE);
            viewHolder0.simple_time_date.setText(date, TextView.BufferType.SPANNABLE);

            if (requestsList.get(position).getStatus().equalsIgnoreCase("requested"))
                viewHolder0.cardView.setCardBackgroundColor(getColor(R.color.request_back));
            else
                viewHolder0.cardView.setCardBackgroundColor(getColor(R.color.accept));


            viewHolder0.re_day_timeAgo.setTextColor(getColor(R.color.white));
            viewHolder0.simple_price_value.setTextColor(getColor(R.color.blue_light));


            // title
            viewHolder0.simple_title.setTextColor(getColor(R.color.white));
            setColor(viewHolder0.simple_title, viewHolder0.simple_title.getText().toString(), transactionHistory.getTitle(), getColor(R.color.blue_light));

            //name
            viewHolder0.simple_name.setTextColor(getColor(R.color.white));

            //date
            viewHolder0.simple_time_date.setTextColor(getColor(R.color.white));
            String s = transactionHistory.getDate() + " " + transactionHistory.getTime();
            setColor(viewHolder0.simple_time_date, viewHolder0.simple_time_date.getText().toString(), s, getColor(R.color.blue_light));


            //status
            viewHolder0.simple_status.setTextColor(getColor(R.color.white));

            setColor(viewHolder0.simple_status, viewHolder0.simple_status.getText().toString(), transactionHistory.getStatus(), getColor(R.color.blue_light));


            //details

            viewHolder0.re_details.setTextColor(getColor(R.color.white));
            setColor(viewHolder0.re_details, details, transactionHistory.getDescription(), getColor(R.color.blue_light));


            if (requestsList.get(position).getStatus().equalsIgnoreCase("pending")) {
                viewHolder0.accept.setVisibility(View.GONE);
                viewHolder0.ignore.setVisibility(View.GONE);

            }

            viewHolder0.accept.setOnClickListener(v -> {

                databaseReference = FirebaseDatabase.getInstance().getReference("requests");
                String taskId = transactionHistory.getPush_id();
                databaseReference.child(taskId).child("status").setValue("pending");
            });

            viewHolder0.ignore.setOnClickListener(v -> {

                databaseReference = FirebaseDatabase.getInstance().getReference("requests");
                String taskId = transactionHistory.getPush_id();
                databaseReference.child(taskId).child("status").setValue("ignored");
            });

        } else if (holder instanceof MySimpleViewHolder) {


            MySimpleViewHolder viewHolder0 = (MySimpleViewHolder) holder;

            viewHolder0.simple_title.setText(title, TextView.BufferType.SPANNABLE);
            viewHolder0.simple_name.setText(transactionHistory.getBorrower_name());
            viewHolder0.simple_price_value.setText(price);
            viewHolder0.simple_status.setText(status, TextView.BufferType.SPANNABLE);
            viewHolder0.simple_time_date.setText(date, TextView.BufferType.SPANNABLE);

            viewHolder0.simple_price_value.setTextColor(getColor(R.color.blue_light));

            //name
            viewHolder0.simple_name.setTextColor(getColor(R.color.accept));


            // title
            viewHolder0.simple_title.setTextColor(getColor(R.color.ignore));
            setColor(viewHolder0.simple_title, viewHolder0.simple_title.getText().toString(), transactionHistory.getTitle(), getColor(R.color.blue_dark));


            //date
            viewHolder0.simple_time_date.setTextColor(getColor(R.color.ignore));
            String s = transactionHistory.getDate() + " " + transactionHistory.getTime();
            setColor(viewHolder0.simple_time_date, viewHolder0.simple_time_date.getText().toString(), s, Color.GRAY);


            //status
            viewHolder0.simple_status.setTextColor(getColor(R.color.ignore));

            setColor(viewHolder0.simple_status, viewHolder0.simple_status.getText().toString(), transactionHistory.getStatus(), Color.GRAY);
        } else if (holder instanceof MyRequestViewHolder) {

            MyRequestViewHolder viewHolder0 = (MyRequestViewHolder) holder;


            viewHolder0.simple_title.setText(title, TextView.BufferType.SPANNABLE);
            viewHolder0.re_day_timeAgo.setText(dayAndTimeAgo);
            viewHolder0.simple_name.setText(transactionHistory.getBorrower_name());
            viewHolder0.simple_price_value.setText(price);
            viewHolder0.simple_status.setText(status, TextView.BufferType.SPANNABLE);
            viewHolder0.re_details.setText(details, TextView.BufferType.SPANNABLE);
            viewHolder0.simple_time_date.setText(date, TextView.BufferType.SPANNABLE);

            if (requestsList.get(position).getStatus().equalsIgnoreCase("requested"))
                viewHolder0.cardView.setCardBackgroundColor(getColor(R.color.request_back));
            else
                viewHolder0.cardView.setCardBackgroundColor(getColor(R.color.accept));

            if (requestsList.get(position).getStatus().equalsIgnoreCase("ignored")){
                viewHolder0.cardView.setCardBackgroundColor(getColor(R.color.red));
                viewHolder0.paid.setVisibility(View.GONE);
                viewHolder0.reject.setVisibility(View.GONE);
            }

            viewHolder0.re_day_timeAgo.setTextColor(getColor(R.color.white));
            viewHolder0.simple_price_value.setTextColor(getColor(R.color.blue_light));

            // title
            viewHolder0.simple_title.setTextColor(getColor(R.color.white));
            setColor(viewHolder0.simple_title, viewHolder0.simple_title.getText().toString(), transactionHistory.getTitle(), getColor(R.color.blue_light));

            //name
            viewHolder0.simple_name.setTextColor(getColor(R.color.white));

            //date
            viewHolder0.simple_time_date.setTextColor(getColor(R.color.white));
            String s = transactionHistory.getDate() + " " + transactionHistory.getTime();
            setColor(viewHolder0.simple_time_date, viewHolder0.simple_time_date.getText().toString(), s, getColor(R.color.blue_light));

            //status
            viewHolder0.simple_status.setTextColor(getColor(R.color.white));
            setColor(viewHolder0.simple_status, viewHolder0.simple_status.getText().toString(), transactionHistory.getStatus(), getColor(R.color.blue_light));

            //details
            viewHolder0.re_details.setTextColor(getColor(R.color.white));
            setColor(viewHolder0.re_details, details, transactionHistory.getDescription(), getColor(R.color.blue_light));
            if (requestsList.get(position).getStatus().equalsIgnoreCase("rejected")) {
                viewHolder0.paid.setVisibility(View.GONE);
                viewHolder0.reject.setVisibility(View.GONE);
            }

            viewHolder0.paid.setOnClickListener(v -> {
                databaseReference = FirebaseDatabase.getInstance().getReference("requests");
                String taskId = transactionHistory.getPush_id();
                databaseReference.child(taskId).child("status").setValue("paid");
            });


            viewHolder0.reject.setOnClickListener(v -> {
                databaseReference = FirebaseDatabase.getInstance().getReference("requests");
                String taskId = transactionHistory.getPush_id();
                databaseReference.child(taskId).child("status").setValue("cancelled");
            });
        }
    }

    private int getColor(int colorResource) {
        return ContextCompat.getColor(context, colorResource);
    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (requestsList.get(position).getStatus().equalsIgnoreCase("paid")) {
            return 0;
        } else
            return 1;
    }

    class MyReceivedViewHolder extends BaseHolderClass {

        TextView re_day_timeAgo, re_details;

        ImageView simple_display;

        private View view;
        private View innerView;

        Button accept, ignore;
        com.google.android.material.card.MaterialCardView cardView;

        MyReceivedViewHolder(@NonNull View itemView, Picasso picasso) {
            super(itemView);

            view = itemView.findViewById(R.id.request_in_received);
            innerView = view.findViewById(R.id.requestInnerView);

            re_details = view.findViewById(R.id.re_details);
            re_day_timeAgo = view.findViewById(R.id.re_day_timeAgo);

            simple_display = innerView.findViewById(R.id.simple_display_image);

            accept = itemView.findViewById(R.id.re_accept);
            ignore = itemView.findViewById(R.id.re_ignore);

            picasso.load(R.drawable.angelina).into(simple_display);

            cardView = itemView.findViewById(R.id.material_received);


        }

        @Override
        public View getView() {
            View view1 = itemView.findViewById(R.id.request_in_received);
            return view1.findViewById(R.id.requestInnerView);

        }
    }


    class MyRequestViewHolder extends BaseHolderClass {

        TextView re_day_timeAgo, re_details;
        ImageView simple_display;
        private View view;
        private View innerView;
        Button paid, reject;
        com.google.android.material.card.MaterialCardView cardView;

        MyRequestViewHolder(@NonNull View itemView, Picasso picasso) {
            super(itemView);

            view = itemView.findViewById(R.id.request_in_material_request);
            innerView = view.findViewById(R.id.requestInnerView);

            re_details = view.findViewById(R.id.re_details);
            re_day_timeAgo = view.findViewById(R.id.re_day_timeAgo);


            simple_display = innerView.findViewById(R.id.simple_display_image);

            picasso.load(R.drawable.angelina).into(simple_display);

            cardView = itemView.findViewById(R.id.material_request);
            paid = itemView.findViewById(R.id.req_paid);
            reject = itemView.findViewById(R.id.req_cancelled);
        }

        @Override
        public View getView() {
            View view1 = itemView.findViewById(R.id.request_in_material_request);
            return view1.findViewById(R.id.requestInnerView);
        }
    }


    class MySimpleViewHolder extends BaseHolderClass {

        ImageView simple_display;


        private View view;

        MySimpleViewHolder(@NonNull View itemView, Picasso picasso) {
            super(itemView);
            view = itemView.findViewById(R.id.layout_simple_material_ls);
            simple_display = view.findViewById(R.id.simple_display_image);
            picasso.load(R.drawable.angelina).centerCrop().resize(IMAGE_DIMENSION, IMAGE_DIMENSION).into(simple_display);
        }

        @Override
        public View getView() {
            return itemView.findViewById(R.id.layout_simple_material_ls);
        }
    }

    public abstract class BaseHolderClass extends RecyclerView.ViewHolder {

        TextView simple_name, simple_title, simple_price_value, simple_status, simple_time_date;

        public abstract View getView();

        BaseHolderClass(@NonNull View itemView) {
            super(itemView);
            View internalView = getView();

            simple_name = internalView.findViewById(R.id.simple_name);
            simple_title = internalView.findViewById(R.id.simple_title);
            simple_price_value = internalView.findViewById(R.id.simple_price_value);
            simple_status = internalView.findViewById(R.id.simple_status);
            simple_time_date = internalView.findViewById(R.id.simple_time_date);


        }
    }

    private void setColor(TextView view, String fulltext, String subtext, int color) {
//        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}

