package com.example.projekti.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projekti.Domain.Kategoria;
import com.example.projekti.R;

import java.util.ArrayList;

public class KategoriaAdpator extends RecyclerView.Adapter<KategoriaAdpator.ViewHolder> {
    ArrayList<Kategoria>category;
    public KategoriaAdpator(ArrayList<Kategoria>category)
    {
        this.category = category;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriaAdpator.ViewHolder holder, int position) {
        holder.emriKategorise.setText(category.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "electriccar";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }
            case 1: {
                picUrl = "mechanicar";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }
            case 2: {
                picUrl = "dekor";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }
            case 3: {
                picUrl = "wheel";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background4));
                break;
            }
            case 4: {
                picUrl = "interiori";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background5));
                break;
            }
            case 5: {
                picUrl = "ule";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background6));
                break;
            }
        }
        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.kategoriaPic);
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView emriKategorise;
        ImageView kategoriaPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emriKategorise=itemView.findViewById(R.id.emriKategorise);
            kategoriaPic=itemView.findViewById(R.id.kategoriaPic);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
