package com.example.projekti.Helper;

import android.content.Context;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.projekti.Adaptor.Interface.ChangeNumberItemsListerner;
import com.example.projekti.Domain.CarDomain;

import java.util.ArrayList;

public class MenaxhimiKarteles {
    private Context context;
    private DB db;

    public MenaxhimiKarteles(Context context)
    {
        this.context=context;
        this.db = new DB(context);
    }
    public void shtoPjese(CarDomain item){
        ArrayList<CarDomain>listPjese=getlistCart();
        boolean existAlready=false;
        int n=0;
        for (int i = 0; i < listPjese.size(); i++) {
            if(listPjese.get(i).getTitle().equals(item.getTitle()))
            {
                existAlready=true;
                n = i;
                break;
            }
        }
        if(existAlready)
        {
            listPjese.get(n).setNumberInCart(item.getNumberInCart());
        }
        else
        {
            listPjese.add(item);
        }
        db.putListObject("CardList",listPjese);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<CarDomain>getlistCart()
    {
        return db.getListObject("CardList");
    }
    public void plusnumberCars(ArrayList<CarDomain>listPjese, int position, ChangeNumberItemsListerner changeNumberItemsListerner)
    {
        listPjese.get(position).setNumberInCart(listPjese.get(position).getNumberInCart()+1);
        db.putListObject("CardList",listPjese);
        changeNumberItemsListerner.changed();
    }
    public void minusNumberCars(ArrayList<CarDomain>listpjese,int position,ChangeNumberItemsListerner changeNumberItemsListerner)
    {
        if(listpjese.get(position).getNumberInCart()==1)
        {
            listpjese.remove(position);
        }
        else
        {
            listpjese.get(position).setNumberInCart(listpjese.get(position).getNumberInCart()-1);
        }
        db.putListObject("CardList",listpjese);
        changeNumberItemsListerner.changed();
    }
    public Double getTotalFee()
    {
        ArrayList<CarDomain> listpjese = getlistCart();
        double fee=0;
        for (int i = 0; i < listpjese.size(); i++) {
            fee = fee + (listpjese.get(i).getFee() * listpjese.get(i).getNumberInCart());

        }
        return fee;
    }


}
