package com.hector.reines;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView accountRecycler;
    private AccountAdapter adapter;
    private ArrayList<Account> accountArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        accountRecycler = findViewById(R.id.accountRecy);
        adapter = new AccountAdapter(accountArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        accountRecycler.setLayoutManager(layoutManager);
        accountRecycler.setAdapter(adapter);
    }

    void addData(){
        accountArrayList = new ArrayList<>();
        accountArrayList.add(new Account("FGO JP", "123456789", "tdyfeWF4568", "17081945"));
        accountArrayList.add(new Account("FGO JP", "123456789", "asdwEET8723", "17081945"));
        accountArrayList.add(new Account("FGO USA", "123456789", "geyrnwVwWE546", "17081945"));
    }
}
