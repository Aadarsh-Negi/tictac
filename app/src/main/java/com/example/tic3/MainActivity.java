package com.example.tic3;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static work_done ck = new work_done();


    @SuppressLint("SetTextI18n")
    public void changC(View view) {
        TextView cur = findViewById(view.getId());
        int val=Integer.parseInt(cur.getTag().toString());
        if(val!=-1){
            return;
        }
          GradientDrawable sp = new GradientDrawable();
          TextView stat = findViewById(R.id.stat);
        if(ck.ChangeIt()){
            sp.setColor(RED);
            cur.setText("O");
            cur.setTag("2");
//            cur.setClickable(false);
            stat.setText("X's turn");
        }else {
            sp.setColor(GREEN);
            cur.setText("X");
//            cur.setClickable(false);
            cur.setTag("1");
            stat.setText("0's turn");
        }
        view.setBackground(sp);
        int verdict=IsItOver();
        if(verdict>0){
            String title = "Game over, ";

            if(verdict==12){
                title+="X won";
            }else if(verdict==13){
                title+="O won";
            }else if(verdict==2){
                title+="DRAW";
            }

            AlertDialog.Builder won = new AlertDialog.Builder(MainActivity.this);
            won.setTitle(title);
            won.setMessage("Do you want to play again.");
            won.setPositiveButton("Yes", (dialog, which) -> reset(view));
            won.setNegativeButton("No", (dialog, which) -> System.exit(0));
            won.setCancelable(false);
            won.show();



        }

        

    }
    public int IsItOver(){
        int a11=Integer.parseInt(findViewById(R.id.a11).getTag().toString());
        int a12=Integer.parseInt(findViewById(R.id.a12).getTag().toString());
        int a13=Integer.parseInt(findViewById(R.id.a13).getTag().toString());
        int a21=Integer.parseInt(findViewById(R.id.a21).getTag().toString());
        int a22=Integer.parseInt(findViewById(R.id.a22).getTag().toString());
        int a23=Integer.parseInt(findViewById(R.id.a23).getTag().toString());
        int a31=Integer.parseInt(findViewById(R.id.a31).getTag().toString());
        int a32=Integer.parseInt(findViewById(R.id.a32).getTag().toString());
        int a33=Integer.parseInt(findViewById(R.id.a33).getTag().toString());

        if(a11!=-1 && a11==a12 && a13==a11){
            if(a11==1) return 12;
            return 13;
        }
        if(a21!=-1 && a21==a22 && a23==a21){
            if(a21==1) return 12;
           return 13;
        }
        if(a31!=-1 && a31==a32 && a33==a31){
            if(a31==1) return 12;
            return 13;
        }
        if(a12!=-1 && a12==a22 && a12==a32){
            if(a12==1) return 12;
            return 13;
        }
        if(a11!=-1 && a31==a11 && a11==a21){
            if(a11==1) return 12;
            return 13;
        }
        if(a13!=-1 && a13==a23 && a33==a23){
            if(a13==1) return 12;
            return 13;
        }
        if(a13!=-1 && a13==a22 && a13==a31){
            if(a13==1) return 12;
            return 13;
        }
        if(a11!=-1 && a11==a22 && a33==a11){
            if(a11==1) return 12;
            return 13;
        }
        if(a11!=-1 && a12!=-1 && a13!=-1 && a21!=-1 && a22!=-1 && a23!=-1 && a31!=-1 && a32!=-1 && a33!=-1){
            return 2;
        }
        return 0;


    }

    public void reset(View view) {
        GradientDrawable sp = new GradientDrawable();
        sp.setColor(BLACK);
        findViewById(R.id.a11).setTag("-1");
        findViewById(R.id.a11).setBackground(sp);
        findViewById(R.id.a11).setClickable(true);
        findViewById(R.id.a12).setTag("-1");
        findViewById(R.id.a12).setClickable(true);
        findViewById(R.id.a12).setBackground(sp);
        findViewById(R.id.a13).setTag("-1");
        findViewById(R.id.a13).setClickable(true);
        findViewById(R.id.a13).setBackground(sp);
        findViewById(R.id.a21).setTag("-1");
        findViewById(R.id.a21).setClickable(true);
        findViewById(R.id.a21).setBackground(sp);
        findViewById(R.id.a22).setTag("-1");
        findViewById(R.id.a22).setClickable(true);
        findViewById(R.id.a22).setBackground(sp);
        findViewById(R.id.a23).setTag("-1");
        findViewById(R.id.a23).setClickable(true);
        findViewById(R.id.a23).setBackground(sp);
        findViewById(R.id.a31).setTag("-1");
        findViewById(R.id.a31).setClickable(true);
        findViewById(R.id.a31).setBackground(sp);
        findViewById(R.id.a32).setTag("-1");
        findViewById(R.id.a32).setClickable(true);
        findViewById(R.id.a32).setBackground(sp);
        findViewById(R.id.a33).setTag("-1");
        findViewById(R.id.a33).setClickable(true);
        findViewById(R.id.a33).setBackground(sp);
    }
}