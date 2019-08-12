package com.hector.reines;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.hector.reines.MainActivity.aaid;

public class FirstActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    DatabaseReference mGetRef;

    List<Integer> savedPasscodes = new ArrayList<>();
    List<Integer> userKey = new ArrayList<>();

    PinLockView pinLockView;
    IndicatorDots indicatorDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGetRef = mDatabase.child("users");

        mGetRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    savedPasscodes.add(Integer.valueOf(dataSnapshot1.child("password").getValue().toString()));
                    userKey.add(Integer.valueOf(dataSnapshot1.getKey()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pinLockView = findViewById(R.id.pinlock);
        indicatorDots = findViewById(R.id.indicatorDots);
        pinLockView.setPinLockListener(pinLockListener);
        pinLockView.attachIndicatorDots(indicatorDots);
    }

    private PinLockListener pinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            for (int j = 0; j < savedPasscodes.size(); j++){
                if(Integer.parseInt(pin) == savedPasscodes.get(j)){
                    startActivity(new Intent(FirstActivity.this, MainActivity.class));
                    finish();
                    aaid = userKey.get(j);
                }
            }
        }

        @Override
        public void onEmpty() {

        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {

        }
    };
}
