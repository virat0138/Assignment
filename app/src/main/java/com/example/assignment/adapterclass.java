package com.example.assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class adapterclass extends RecyclerView.Adapter<adapterclass.myviewholder> {

    ArrayList<dataclass> dataholder;

    public adapterclass(ArrayList<dataclass> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //converting raw xml into view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowdesign,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.dname.setText(dataholder.get(position).getName());
        holder.dage.setText(dataholder.get(position).getAge());
        holder.dcity.setText(dataholder.get(position).getCity());
        holder.dgender.setText(dataholder.get(position).getGender());

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView dname, dage,dcity, dgender;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            dname = itemView.findViewById(R.id.displayname);
            dage = itemView.findViewById(R.id.displayage);
            dcity = itemView.findViewById(R.id.displaycity);
            dgender = itemView.findViewById(R.id.displaygender);

        }
    }
}
