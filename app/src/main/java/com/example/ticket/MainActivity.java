package com.example.ticket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, DialogInterface.OnClickListener {
    TextView tv_children,tv_adault,tv_old;
    RadioButton rb_oneway,rb_gaf;
    CheckBox cb_children,cb_adault,cb_old;
    Spinner sp_start,sp_end,sp_children,sp_adault,sp_old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_children = (TextView)findViewById(R.id.textView7);
        tv_adault = (TextView)findViewById(R.id.textView8);
        tv_old =(TextView)findViewById(R.id.textView9);
        rb_oneway = (RadioButton) findViewById(R.id.radioButton2);
        rb_gaf = (RadioButton) findViewById(R.id.radioButton3);
        cb_children =(CheckBox) findViewById(R.id.checkBox1);
        cb_adault =(CheckBox) findViewById(R.id.checkBox2);
        cb_old =(CheckBox) findViewById(R.id.checkBox3);
        sp_start = (Spinner) findViewById(R.id.spinner_start);
        sp_end =(Spinner) findViewById(R.id.spinner_end);
        sp_children =(Spinner) findViewById(R.id.spinner_chidren);
        sp_adault = (Spinner) findViewById(R.id.spinner_adault);
        sp_old = (Spinner) findViewById(R.id.spinner_old);

        cb_children.setOnCheckedChangeListener(this);
        cb_adault.setOnCheckedChangeListener(this);
        cb_old.setOnCheckedChangeListener(this);

        sp_children.setEnabled(false);
        sp_old.setEnabled(false);
    }

    public void goToSecondpage(View view){
        AlertDialog.Builder bdr = new AlertDialog.Builder(this);

        String chi = sp_children.getSelectedItem().toString();
        String ada = sp_adault.getSelectedItem().toString();
        String old = sp_old.getSelectedItem().toString();

        int Chi = Integer.parseInt(chi);
        int Ada = Integer.parseInt(ada);
        int Old = Integer.parseInt(old);

        if(sp_start.getSelectedItem().toString().equals(sp_end.getSelectedItem().toString())){
            bdr.setTitle("提醒");
            bdr.setMessage("不可選擇相同站別");
            bdr.setNegativeButton("確定",this);
            bdr.setCancelable(true);
            bdr.show();
        }
        else if(Chi == 0 && Ada == 0 && Old == 0){
            Toast.makeText(this, "請選擇車票數量", Toast.LENGTH_SHORT).show();
        }
        else{
            dataToSecondpage();
        }
    }

    public void dataToSecondpage(){
        Intent secondpages = new Intent(this,MainActivity2.class);
        Bundle data = new Bundle();
        Values values = total();

        String start = sp_start.getSelectedItem().toString();
        String end = sp_end.getSelectedItem().toString();
        String children = sp_children.getSelectedItem().toString();
        String adault = sp_adault.getSelectedItem().toString();
        String old = sp_old.getSelectedItem().toString();
        if(rb_oneway.isChecked()){
            String trip = "單程";
            data.putString("trip",trip);
        }
        else{
            String trip = "來回";
            data.putString("trip",trip);
        }
        data.putString("start",start);
        data.putString("end",end);
        data.putString("children",children);
        data.putString("adault",adault);
        data.putString("old",old);
        data.putString("total",values.total);
        data.putString("chi_total",values.chi_total);
        data.putString("ada_total",values.ada_total);
        data.putString("old_total",values.old_total);
        secondpages.putExtras(data);
        startActivity(secondpages);
    }



    public double TicketPrice(){
        double price= 0.0 ;
        if((sp_start.getSelectedItem().toString().equals("台北")&&sp_end.getSelectedItem().toString().equals("新竹"))||
                (sp_start.getSelectedItem().toString().equals("新竹")&&sp_end.getSelectedItem().toString().equals("苗栗"))||
                (sp_start.getSelectedItem().toString().equals("苗栗")&&sp_end.getSelectedItem().toString().equals("台中"))||
                (sp_start.getSelectedItem().toString().equals("台中")&&sp_end.getSelectedItem().toString().equals("台南"))||
                (sp_start.getSelectedItem().toString().equals("台南")&&sp_end.getSelectedItem().toString().equals("高雄"))||
                (sp_start.getSelectedItem().toString().equals("高雄")&&sp_end.getSelectedItem().toString().equals("台南"))||
                (sp_start.getSelectedItem().toString().equals("台南")&&sp_end.getSelectedItem().toString().equals("台中"))||
                (sp_start.getSelectedItem().toString().equals("台中")&&sp_end.getSelectedItem().toString().equals("苗栗"))||
                (sp_start.getSelectedItem().toString().equals("苗栗")&&sp_end.getSelectedItem().toString().equals("新竹"))||
                (sp_start.getSelectedItem().toString().equals("新竹")&&sp_end.getSelectedItem().toString().equals("台北"))){
            price = 100.0;
        }
        else if ((sp_start.getSelectedItem().toString().equals("台北")&&sp_end.getSelectedItem().toString().equals("苗栗"))||
                (sp_start.getSelectedItem().toString().equals("新竹")&&sp_end.getSelectedItem().toString().equals("台中"))||
                (sp_start.getSelectedItem().toString().equals("苗栗")&&sp_end.getSelectedItem().toString().equals("台北"))||
                (sp_start.getSelectedItem().toString().equals("苗栗")&&sp_end.getSelectedItem().toString().equals("台南"))||
                (sp_start.getSelectedItem().toString().equals("台中")&&sp_end.getSelectedItem().toString().equals("高雄"))||
                (sp_start.getSelectedItem().toString().equals("台中")&&sp_end.getSelectedItem().toString().equals("新竹"))||
                (sp_start.getSelectedItem().toString().equals("台南")&&sp_end.getSelectedItem().toString().equals("苗栗"))||
                (sp_start.getSelectedItem().toString().equals("高雄")&&sp_end.getSelectedItem().toString().equals("台中"))){
            price = 200.0;
        }
        else if((sp_start.getSelectedItem().toString().equals("台北")&&sp_end.getSelectedItem().toString().equals("台中"))||
                (sp_start.getSelectedItem().toString().equals("新竹")&&sp_end.getSelectedItem().toString().equals("台南"))||
                (sp_start.getSelectedItem().toString().equals("苗栗")&&sp_end.getSelectedItem().toString().equals("高雄"))||
                (sp_start.getSelectedItem().toString().equals("台中")&&sp_end.getSelectedItem().toString().equals("台北"))||
                (sp_start.getSelectedItem().toString().equals("台南")&&sp_end.getSelectedItem().toString().equals("新竹"))||
                (sp_start.getSelectedItem().toString().equals("高雄")&&sp_end.getSelectedItem().toString().equals("苗栗"))){
            price =300.0;
        }
        else if((sp_start.getSelectedItem().toString().equals("台北")&&sp_end.getSelectedItem().toString().equals("台南"))||
                (sp_start.getSelectedItem().toString().equals("新竹")&&sp_end.getSelectedItem().toString().equals("高雄"))||
                (sp_start.getSelectedItem().toString().equals("台南")&&sp_end.getSelectedItem().toString().equals("台北"))||
                (sp_start.getSelectedItem().toString().equals("高雄")&&sp_end.getSelectedItem().toString().equals("新竹"))){
            price =400.0;
        }
        else if((sp_start.getSelectedItem().toString().equals("台北")&&sp_end.getSelectedItem().toString().equals("高雄"))||
                (sp_start.getSelectedItem().toString().equals("高雄")&&sp_end.getSelectedItem().toString().equals("台北"))){
            price =500.0;
        }
        return price;
    }

    static class Values{
        String chi_total ,ada_total,old_total,total;
        Values(String chi_total,String ada_total,String old_total,String total){
            this.chi_total = chi_total;
            this.ada_total = ada_total;
            this.old_total =old_total;
            this.total = total;
        }
    }

    public Values total(){
        String total = "";
        String chi_total ="";
        String ada_total = "";
        String old_total = "";
        double tot = 0.0;
        double chi_tot = 1.0*0.7;
        double ada_tot = 1.0;
        double old_tot = 1.0*0.8;
        double p  = TicketPrice();
        double kk2 =  Double.parseDouble(String.valueOf(sp_adault.getSelectedItemId()));
        double kk3 =  Double.parseDouble(String.valueOf(sp_old.getSelectedItemId()));
        double kk1 =  Double.parseDouble(String.valueOf(sp_children.getSelectedItemId()));
        if(rb_oneway.isChecked()){
            double choice = 1.0;
            chi_tot*= choice*p*kk1;
            ada_tot*= choice*p*kk2;
            old_tot*= choice*p*kk3;
            tot += chi_tot+ada_tot+old_tot;
        }
        else {
            double choice = 2.0;
            chi_tot*= choice*p*kk1;
            ada_tot*= choice*p*kk2;
            old_tot*= choice*p*kk3;
            tot += chi_tot+ada_tot+old_tot;
        }
        chi_total = Double.toString(chi_tot);
        ada_total = Double.toString(ada_tot);
        old_total = Double.toString(old_tot);
        total = Double.toString(tot);
        return new Values(chi_total,ada_total,old_total,total);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        AlertDialog.Builder bdr = new AlertDialog.Builder(this);
        if (!cb_children.isChecked()&&!cb_adault.isChecked()&&!cb_old.isChecked()){
            bdr.setTitle("提醒") ;
            bdr.setMessage("請選擇票種");
            bdr.setNegativeButton("確定",this);
            bdr.setCancelable(true);
            bdr.show();
        }

        if(cb_children.isChecked()==false){
            sp_children.setEnabled(false);
        }
        else {
            sp_children.setEnabled(true);
        }
        if(cb_adault.isChecked()==false){
            sp_adault.setEnabled(false);
        }
        else{
            sp_adault.setEnabled(true);
        }
        if(cb_old.isChecked()==false){
            sp_old.setEnabled(false);
        }
        else{
            sp_old.setEnabled(true);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

}