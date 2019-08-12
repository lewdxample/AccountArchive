package com.hector.reines;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OptionActivity extends AppCompatActivity {

    DatabaseReference mDatabase, mGetRefProfile;

    EditText mi_name, mi_pin;
    Button savepin_btn;
    String new_pin;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Option");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGetRefProfile = mDatabase.child("users");

        mi_name = findViewById(R.id.mi_name);
        mi_pin = findViewById(R.id.input_newpin);
        savepin_btn = findViewById(R.id.savepin_btn);

        mGetRefProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_name = dataSnapshot.child(MainActivity.aaid+"").child("name").getValue().toString();
                mi_name.setText(user_name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        savepin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mi_pin.length() < 4){
                    Toast.makeText(OptionActivity.this, "PIN harus 4 digit", Toast.LENGTH_SHORT).show();
                }else{
                    new_pin = mi_pin.getText().toString();
                    mGetRefProfile.child(MainActivity.aaid+"").child("password").setValue(new_pin);
                    mi_pin.setText("");
                    Toast.makeText(OptionActivity.this, "PIN Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
