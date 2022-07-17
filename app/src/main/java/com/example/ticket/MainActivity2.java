package com.example.ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    TextView tv_total;
    RecyclerView rv;
    ArrayList<String> Data_count =new ArrayList<String>();
    ArrayList<String> Data_price =new ArrayList<String>();
    recycleview adapter;
    String total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_total = (TextView)findViewById(R.id.tv_total);
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Bundle getdata = this.getIntent().getExtras();
        getdata.getString("start");
        getdata.getString("end");
        Data_count.add("孩童票張數:"+getdata.getString("children"));
        Data_count.add("成人票張數:"+getdata.getString("adault"));
        Data_count.add("老人票張數:"+getdata.getString("old"));
        Data_count.add("總金額");
        Data_price.add(getdata.getString("chi_total")+"元");
        Data_price.add(getdata.getString("ada_total")+"元");
        Data_price.add(getdata.getString("old_total")+"元");

        total = getdata.getString("total");
        Data_price.add("總金額:"+total+"元");
        tv_total.setText("總金額 "+getdata.getString("total")+"元");
        adapter = new recycleview(Data_count,Data_price);
        rv.setAdapter(adapter);
    }

    public void SecondageToThirdpage(View view) {
        Intent nextToThird = new Intent(this, MainActivity3.class);
        nextToThird.putExtra("total_price",total);
        startActivity(nextToThird);
        }

    public void returnToFirstpage(View view) {
        finish();
    }


    @Override
    public void onClick(View v) {

    }
}
