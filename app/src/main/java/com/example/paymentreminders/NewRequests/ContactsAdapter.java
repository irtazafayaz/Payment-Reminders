package com.example.paymentreminders.NewRequests;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paymentreminders.Interface.Common;
import com.example.paymentreminders.Interface.ItemClickListener;
import com.example.paymentreminders.Model.ContactClass;
import com.example.paymentreminders.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {


    private LayoutInflater layoutInflater;
    private List<ContactClass> dataList;
    int row_index = -1;


    ContactsAdapter(Context cOntext, List<ContactClass> dataList) {
        layoutInflater = LayoutInflater.from(cOntext);
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsHolder(layoutInflater.inflate(R.layout.add_new_request_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {

        holder.contact_name.setText(dataList.get(position).getName());
        holder.contact_number.setText(dataList.get(position).getPhoneNo());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                row_index = position;
                Common.currentItem = dataList.get(position);
                notifyDataSetChanged();
            }
        });

        if (row_index == position)
            holder.itemView.setBackgroundColor(Color.parseColor("#F8F8FA"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ContactsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView contact_number, contact_name;
        ImageView contact_image;
        ItemClickListener itemClickListener;


        public ContactsHolder(@NonNull View itemView) {
            super(itemView);

            contact_image = itemView.findViewById(R.id.contact_image);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }

}
