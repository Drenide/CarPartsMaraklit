package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.projekti.Adaptor.CardListAdapter;
import com.example.projekti.Adaptor.Interface.ChangeNumberItemsListerner;
import com.example.projekti.Helper.MenaxhimiKarteles;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private MenaxhimiKarteles menaxhimiKarteles;
TextView totalFeeText,taxTxt,deliveryTxt,totalTxt,emptyTxt;
private double tax;
private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        menaxhimiKarteles=new MenaxhimiKarteles(this);
        initView();
        initList();
        Calculator();
        bottomNavigation();
    }

    private void bottomNavigation()
    {
        FloatingActionButton floatingActionButton=findViewById(R.id.butoniKartes);
        LinearLayout homebutoni=findViewById(R.id.shtepia);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });
        homebutoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this,MainActivity.class));
            }
        });

    }

    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView);
        totalFeeText=findViewById(R.id.totalArtikuj);
        taxTxt=findViewById(R.id.totalTaks);
        deliveryTxt=findViewById(R.id.totalTransport);
        totalTxt=findViewById(R.id.totalFatura);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView4);
        recyclerViewList=findViewById(R.id.cartView);
    }
    private void initList()
    {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CardListAdapter(menaxhimiKarteles.getlistCart(),this, new ChangeNumberItemsListerner() {
            @Override
            public void changed()
            {
                Calculator();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(menaxhimiKarteles.getlistCart().isEmpty())
        {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else
        {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void Calculator()
    {
        double percentTax=0.02;
        double delivery=10;
        tax=Math.round((menaxhimiKarteles.getTotalFee()*percentTax)*100)/100;
        double total=Math.round((menaxhimiKarteles.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round(menaxhimiKarteles.getTotalFee()*100)/100;

        totalFeeText.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);
    }
}