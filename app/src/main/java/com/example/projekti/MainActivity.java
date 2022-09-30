package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.projekti.Adaptor.KategoriaAdpator;
import com.example.projekti.Adaptor.PopularAdpator;
import com.example.projekti.Domain.CarDomain;
import com.example.projekti.Domain.Kategoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategoryList();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation()
    {
        FloatingActionButton floatingActionButton=findViewById(R.id.butoniKartes);
        LinearLayout homebutoni=findViewById(R.id.shtepia);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        homebutoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });

    }
    private void recyclerViewCategoryList()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        ArrayList<Kategoria> category=new ArrayList<>();
        category.add(new Kategoria("Electronic","electriccar"));
        category.add(new Kategoria("Mechanic","mechanicar"));
        category.add(new Kategoria("Decor","dekor"));
        category.add(new Kategoria("Wheel","wheel"));
        category.add(new Kategoria("Interior","interiori"));
        category.add(new Kategoria("Oil","ule"));
        adapter=new KategoriaAdpator(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<CarDomain> carList=new ArrayList<>();
        carList.add(new CarDomain("Golf 7 Timon","wollani","Radio,Shifter,Sensor,Window,Light,Door,Computer,Chair", 400.0));
        carList.add(new CarDomain("Fellne per Mercedes","fellnet","Radio,Shifter,Sensor,Window,Light,Door,Computer,Chair", 9.76));
        carList.add(new CarDomain("Difuser","difuseri","Radio,Shifter,Sensor,Window,Light,Door,Computer,Chair", 100.0));
        carList.add(new CarDomain("Led","ledat","Radio,Shifter,Sensor,Window,Light,Door,Computer,Chair", 9.76));
        carList.add(new CarDomain("Alternator","alternatori","Radio,Shifter,Sensor,Window,Light,Door,Computer,Chair", 9.76));



        adapter2=new PopularAdpator(carList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}