package com.example.chips_java;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<Contact> Contacts;
    private RecycleItemSelectedListener recycleItemSelectedListener;
    public RecyclerAdapter(Context context,List<Contact> contacts){
        this.context=context;
        this.Contacts=contacts;
        recycleItemSelectedListener=(MainActivity)context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        Log.d("111", "onCreateViewHolder: "+Contacts);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.circleImageView.setImageDrawable(ContextCompat.getDrawable(context,Contacts.get(position).getPinId()));
        holder.textView.setText(Contacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Contacts.size();
    }
    public void  updateList(List<Contact> newcontacts){
        MyDiffUtilCallback diffUtilCallback=new MyDiffUtilCallback(Contacts,newcontacts);
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(diffUtilCallback);
        Contacts.clear();
        Contacts.addAll(newcontacts);
        diffResult.dispatchUpdatesTo(this);
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView circleImageView;
        TextView textView;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.profile_pic);
            textView=itemView.findViewById(R.id.profile_name);
            linearLayout=itemView.findViewById(R.id.rootView);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recycleItemSelectedListener.onItemSelected(Contacts.get(getAdapterPosition()));

        }



    }
}
