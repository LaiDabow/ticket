package com.example.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
TextView tv_discnt,tv_price;
float rdm,price,result ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv_discnt = (TextView) findViewById(R.id.discount);
        tv_price = (TextView) findViewById(R.id.price);
    }

    public void discountRandom(View view){
        Random random = new Random();
        ArrayList<Integer> rdm_num = new ArrayList<Integer>();
        rdm_num.add(0);
        rdm_num.add(100);
        rdm_num.add(200);
        rdm_num.add(300);
        rdm_num.add(400);
        rdm_num.add(500);

        Intent getPrice = getIntent();
        rdm = rdm_num.get(random.nextInt(rdm_num.size()));
        String total_price = getPrice.getStringExtra("total_price");
        price = Float.parseFloat(total_price);
        result = price - rdm;
        if(rdm==0){
            tv_discnt.setText("獲得"+rdm+"元"+"\n"+"再接再厲");
        }
        else {
            if (price <= rdm){
                result = 0;
                tv_discnt.setText("恭喜免費,您抽到金額為"+rdm+"元");
            }
            else {
                tv_discnt.setText("恭喜獲得折扣"+rdm+"元");
            }
        }
        tv_price.setText("總金額為"+result+"元");
    }


    public void returnToSecondpage(View view){
        finish();
    }
}