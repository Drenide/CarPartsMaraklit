package com.example.projekti.Adaptor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projekti.Domain.CarDomain;
import com.example.projekti.R;
import com.example.projekti.SecondActivity;

import java.util.ArrayList;

public class PopularAdpator extends RecyclerView.Adapter<PopularAdpator.ViewHolder> {

    ArrayList<CarDomain> popularCars;

    public PopularAdpator(ArrayList<CarDomain>category)
    {
        this.popularCars = category;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdpator.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(popularCars.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularCars.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(popularCars.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.butoniad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(holder.itemView.getContext(), SecondActivity.class);
                intent.putExtra("object",popularCars.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView pic;
        TextView butoniad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            fee=itemView.findViewById(R.id.fee);
            pic=itemView.findViewById(R.id.pic);
            butoniad=itemView.findViewById(R.id.butoniad);


        }
    }
}
