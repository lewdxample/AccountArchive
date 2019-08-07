package com.hector.reines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        input_Uid = findViewById(R.id.materialEditText0);
        input_genId = findViewById(R.id.materialEditText);
        input_passCode = findViewById(R.id.materialEditText2);
        input_gameName = findViewById(R.id.spinner);
        saveBtn = findViewById(R.id.save_btn);

        addData();

        accountRecycler = findViewById(R.id.accountRecy);
        adapter = new AccountAdapter(accountArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        accountRecycler.setLayoutManager(layoutManager);
        accountRecycler.setAdapter(adapter);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewData();
                adapter.notifyDataSetChanged();
            }
        });
    }

    void addData(){
        accountArrayList = new ArrayList<>();
        accountArrayList.add(new Account("FGO JP", "123456789", "tdyfeWF4568", "17081945"));
        accountArrayList.add(new Account("FGO JP", "123456789", "asdwEET8723", "17081945"));
        accountArrayList.add(new Account("FGO USA", "123456789", "geyrnwVwWE546", "17081945"));
    }

    void addNewData(){
        accountArrayList.add(new Account(input_gameName.getSelectedItem().toString(), input_Uid.getText().toString(), input_genId.getText().toString(), input_passCode.getText().toString()));
    }

}
