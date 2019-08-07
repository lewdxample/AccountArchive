package com.hector.reines;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mDatabase, mGetRef, mGetUser;

    String user_name;
    LinearLayout linear;

    private MaterialEditText input_genId, input_passCode, input_Uid;
    private Spinner input_gameName;
    private Button saveBtn;

    private RecyclerView accountRecycler;
    private AccountAdapter adapter;
    private ArrayList<Account> accountArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mGetRef = mDatabase.child("archives").child("ver1");
        mGetUser = mDatabase.child("users").child("0");

        linear = findViewById(R.id.linear);

        input_Uid = findViewById(R.id.materialEditText0);
        input_genId = findViewById(R.id.materialEditText);
        input_passCode = findViewById(R.id.materialEditText2);
        input_gameName = findViewById(R.id.spinner);
        saveBtn = findViewById(R.id.save_btn);

        accountArrayList = new ArrayList<>();

        accountRecycler = findViewById(R.id.accountRecy);
        adapter = new AccountAdapter(accountArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        accountRecycler.setLayoutManager(layoutManager);
        accountRecycler.setAdapter(adapter);


        mGetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                accountArrayList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                    Log.e("data", "data : " +dataSnapshot1.getValue());
                    accountArrayList.add(new Account(
                            dataSnapshot1.child("acc_id").getValue().toString(),
                            dataSnapshot1.child("game_name").getValue().toString(),
                            dataSnapshot1.child("uid").getValue().toString(),
                            dataSnapshot1.child("gen_id").getValue().toString(),
                            dataSnapshot1.child("pass_code").getValue().toString()));
                }

                adapter.setAccountList(accountArrayList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mGetUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_name = dataSnapshot.child("name").getValue().toString();
                Snackbar.make(linear, "Welcome back, " +user_name, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_Uid.length() == 0){
                    Toast.makeText(MainActivity.this, "UID is Empty!", Toast.LENGTH_SHORT).show();
                } else if(input_genId.length() == 0){
                    Toast.makeText(MainActivity.this, "Generated ID is Empty!", Toast.LENGTH_SHORT).show();
                }else if (input_passCode.length() == 0){
                    Toast.makeText(MainActivity.this, "Pass Code is Empty!", Toast.LENGTH_SHORT).show();
                }else {
                    addToDB();
                    input_Uid.setText("");
                    input_genId.setText("");
                    input_passCode.setText("");
                    Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please tap BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    void addToDB(){
        Account account = new Account("", "", "", "", "");
        account.setAcc_id(mGetRef.push().getKey());
        account.setGame_name(input_gameName.getSelectedItem().toString());
        account.setUid(input_Uid.getText().toString());
        account.setGen_id(input_genId.getText().toString());
        account.setPass_code(input_passCode.getText().toString());
        mGetRef.child(account.getAcc_id()).setValue(account);
    }
}
