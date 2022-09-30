package com.example.projekti.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projekti.Adaptor.Interface.ChangeNumberItemsListerner;
import com.example.projekti.CartListActivity;
import com.example.projekti.Domain.CarDomain;
import com.example.projekti.Helper.MenaxhimiKarteles;
import com.example.projekti.R;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {
    private ArrayList<CarDomain> carDomains;
    private MenaxhimiKarteles menaxhimiKarteles;
    private ChangeNumberItemsListerner changeNumberItemsListerner;

    public CardListAdapter(ArrayList<CarDomain> carDomains, Context context, ChangeNumberItemsListerner changeNumberItemsListerner) {
        this.carDomains = carDomains;
        this.menaxhimiKarteles = new MenaxhimiKarteles(context);
        this.changeNumberItemsListerner = changeNumberItemsListerner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);


        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(carDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(carDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((carDomains.get(position).getNumberInCart()*carDomains.get(position).getFee())*100)/100));
        holder.num.setText(String.valueOf(carDomains.get(position).getNumberInCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(carDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menaxhimiKarteles.plusnumberCars(carDomains, position, new ChangeNumberItemsListerner() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListerner.changed();
                    }
                });

            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menaxhimiKarteles.minusNumberCars(carDomains, position, new ChangeNumberItemsListerner() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();;
                        changeNumberItemsListerner.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return carDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCard);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItem);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
        }
    }
}
