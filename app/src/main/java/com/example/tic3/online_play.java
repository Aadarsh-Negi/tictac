package com.example.tic3;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class online_play extends Activity {
    MainActivity play = new MainActivity();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference pos = database.getReference("pos");
    static public int turn=0;

    public void reset(View view) {
        play.reset(view);
    }

    public void make_move(View view) {
        Toast.makeText(getApplicationContext(),"mm1666" + view.getId(),Toast.LENGTH_LONG).show();
        if(turn==1){
            turn=0;
            Toast.makeText(getApplicationContext(),"mm1" + view.getId(),Toast.LENGTH_LONG).show();
//            int loc = view.getId();
            pos.setValue(view.getId());
            play.changC(view);
        }else{
            turn = 1;
            Toast.makeText(getApplicationContext(),"mm2" + view.getId(),Toast.LENGTH_LONG).show();
            pos.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int ppp = (Integer) snapshot.getValue();
                    View vv = findViewById(ppp);
                    play.changC(vv);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
}
