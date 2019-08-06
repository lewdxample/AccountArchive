package com.hector.reines;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private ArrayList<Account> accountList;

    public AccountAdapter(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }
    @NonNull
    @Override
    public AccountAdapter.AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.saved_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.AccountViewHolder holder, int pos) {
        holder.txt_gameName.setText(accountList.get(pos).getGame_name());
        holder.txt_uid.setText("UID : " +accountList.get(pos).getUid());
        holder.txt_genId.setText("Generated ID : " +accountList.get(pos).getGen_id());
        holder.txt_passCode.setText("Pass Code : " +accountList.get(pos).getPass_code());
    }

    @Override
    public int getItemCount() {
        return (accountList != null) ? accountList.size() : 0;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_gameName, txt_uid, txt_genId, txt_passCode;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_gameName = itemView.findViewById(R.id.txt_gameName);
            txt_uid = itemView.findViewById(R.id.txt_uid);
            txt_genId = itemView.findViewById(R.id.txt_genID);
            txt_passCode = itemView.findViewById(R.id.txt_passCode);
        }
    }
}
