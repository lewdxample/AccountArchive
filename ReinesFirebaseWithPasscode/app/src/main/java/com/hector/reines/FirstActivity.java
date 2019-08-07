package com.hector.reines;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirstActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    DatabaseReference mGetRef;

    int savedPasscode;

    PinLockView pinLockView;
    IndicatorDots indicatorDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGetRef = mDatabase.child("users").child("0");

        mGetRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                savedPasscode = Integer.parseInt(dataSnapshot.child("password").getValue().toString());
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
//            Toast.makeText(FirstActivity.this, pin, Toast.LENGTH_SHORT).show();
            if(Integer.parseInt(pin) == savedPasscode){
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
                finish();
            }else {
                Toast.makeText(FirstActivity.this, "Pin Invalid!", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onEmpty() {
//            Toast.makeText(FirstActivity.this, "Pin Empty!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {

        }
    };
}
