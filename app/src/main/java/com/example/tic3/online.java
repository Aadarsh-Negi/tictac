package com.example.tic3;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    MainActivity play = new MainActivity();


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
    public void reset(View view) {
        play.reset(view);
    }

    public void make_move(View view) {
//        res.getResourceEntryName(view.getId())

//        Toast.makeText(getApplicationContext(),a11,Toast.LENGTH_LONG).show();
        if(turn==1){
            turn=0;
//            String loc = view.getId();
//            Toast.makeText(getApplicationContext(),""+ view.getId(),Toast.LENGTH_LONG).show();
//            int loc = view.getId();
            pos.setValue(view.getId());
            play.changC(view);
        }else{
            turn = 1;
//            Toast.makeText(getApplicationContext(),"" + view.getId(),Toast.LENGTH_LONG).show();
            pos.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    int ppp = snapshot.getValue();
//                    View vv = findViewById(ppp);
//                    play.changC(vv);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

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
}