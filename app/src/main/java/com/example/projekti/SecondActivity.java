package com.example.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projekti.Domain.CarDomain;
import com.example.projekti.Domain.Kategoria;
import com.example.projekti.Helper.MenaxhimiKarteles;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    private TextView shtoneshporteButon;
    private TextView titleTxt,feeTxt,pershkrimi,numriporosive;
    private ImageView plusBtn,MinusBtn,picCar;
    private CarDomain object;
    int numripor=1;
    private MenaxhimiKarteles menaxhimiKarteles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        menaxhimiKarteles=new MenaxhimiKarteles(this);
        initView();
        getBundle();
    }

    private void getBundle()
    {
        object=(CarDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into((picCar));
        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        pershkrimi.setText(object.getDescription());
        numriporosive.setText(String.valueOf(numripor));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numripor=numripor+1;
                numriporosive.setText(String.valueOf(numripor));
            }
        });

        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numripor>1)
                {
                    numripor=numripor-1;
                }
                numriporosive.setText(String.valueOf(numripor));
            }
        });
        shtoneshporteButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numripor);
                menaxhimiKarteles.shtoPjese(object);
            }
        });
    }

    private void initView() {
        shtoneshporteButon=findViewById(R.id.shtoneshport);
        titleTxt=findViewById(R.id.texttitle);
        feeTxt=findViewById(R.id.cmimitext);
        pershkrimi=findViewById(R.id.Pershkrimi);
        numriporosive=findViewById(R.id.numriporosive);
        plusBtn=findViewById(R.id.plusButoni);
        MinusBtn=findViewById(R.id.minusButoni);
        picCar=findViewById(R.id.picCar);

    }
}