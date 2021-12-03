package com.example.tic3;

import static android.content.ContentValues.TAG;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class online extends AppCompatActivity{
    Button Create;
    Button Join;
    EditText code;


//    private DatabaseReference database;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("code");
    DatabaseReference pos = database.getReference("pos");
    static public int turn=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        Create = findViewById(R.id.button4);
        Join = findViewById(R.id.button5);
        code=findViewById(R.id.textView2);
//            pos.setValue(124);

//        mdb.child("code").setValue("it works");
//        String cc = code.getText().toString();
    }

    public void create_code(View view) {
        Create.setVisibility(View.INVISIBLE);
        Join.setVisibility(View.INVISIBLE);
        code.setVisibility(View.INVISIBLE);
        String cdx = code.getText().toString();

//        DataSnapshot snap

        myRef.addValueEventListener(new ValueEventListener() {              // ref listenr
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String val = dataSnapshot.getValue(String.class);
//                val="heelop  ";
//                Toast.makeText(getApplicationContext(),val,Toast.LENGTH_LONG).show();
//                if(val.equals(cdx)){
                myRef.setValue(cdx);
//                    Intent intent = new Intent(online.this, online_play.class);
//                    startActivity(intent);
                    setContentView(R.layout.online_play);
                    turn=1;
                    Toast.makeText(getApplicationContext(),"connected",Toast.LENGTH_LONG).show();
//                    online_play mm= new online_play();
//                    mm.turn=1;

//                }else Toast.makeText(getApplicationContext(),"invalid code",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
//        ValueEventListener listen = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot data: dataSnapshot.getChildren()){
//                    if (data.child(cdx).exists()) {
//                        mdb.child("code").setValue("it works");
//                    } else {
////                        DatabaseReference mdb;
//                        mdb.child("code").setValue(cdx);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        mdb.child("codes").addValueEventListener(listen); {
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                var check = isValueAvailable(snapshot, code)
//
//                Handler().postDelayed({
//                if (check == true) {
//                    Create.visibility = View.VISIBLE
//                    Join.visibility = View.VISIBLE
//                    GameCode.visibility = View.VISIBLE
//                    textView4.visibility = View.VISIBLE
//                    progressBar.visibility = View.GONE
//
//                } else {
//                    FirebaseDatabase.getInstance().reference.child("codes").push().setValue(code)
//                    isValueAvailable(snapshot, code)
//                    checkTemp = false
//                    Handler().postDelayed({
//                            accepted()
//                            errorMsg("Please don't go back")
//                    }, 300)
//
//                }
//                            }, 2000)
//
//
//            }
//
//        })
//}

    }
    static work_done ck = new work_done();

    public void make_move(View view) {
//        res.getResourceEntryName(view.getId())

//        Toast.makeText(getApplicationContext(),a11,Toast.LENGTH_LONG).show();
//        if(turn==1 || turn==0){
//            turn=0;
//            String loc = view.getId();
//            Toast.makeText(getApplicationContext(),""+ view.getId(),Toast.LENGTH_LONG).show();
//            int loc = view.getId();
            changC(view);
//            pos.setValue(view.getId());

//        }else{
//            turn = 1;
//            Toast.makeText(getApplicationContext(),"" + view.getId(),Toast.LENGTH_LONG).show();
//            pos.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    int ppp = snapshot.getValue();
//                    View vv = findViewById(ppp);
//                    play.changC(vv);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });

//        }

    }


    public void join_room(View view) {
        String cdx = code.getText().toString();
        turn=0;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String val = snapshot.getValue(String.class);
                if(val.equals(cdx)){
                    Intent intent = new Intent(online.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"connected",Toast.LENGTH_LONG).show();

                }else Toast.makeText(getApplicationContext(),"invalid code",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
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

            AlertDialog.Builder won = new AlertDialog.Builder(online.this);
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