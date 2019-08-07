package com.hector.reines;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private DatabaseReference mGetRef;
    private Account account;

    private ArrayList<Account> accountList;

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

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
        holder.txt_uid.setText("UID : " + accountList.get(pos).getUid());
        holder.txt_genId.setText("Generated ID : " + accountList.get(pos).getGen_id());
        holder.txt_passCode.setText("Pass Code : " + accountList.get(pos).getPass_code());
    }

    @Override
    public int getItemCount() {
        return (accountList != null) ? accountList.size() : 0;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_gameName, txt_uid, txt_genId, txt_passCode;
        private Button delBtn;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_gameName = itemView.findViewById(R.id.txt_gameName);
            txt_uid = itemView.findViewById(R.id.txt_uid);
            txt_genId = itemView.findViewById(R.id.txt_genID);
            txt_passCode = itemView.findViewById(R.id.txt_passCode);
            delBtn = itemView.findViewById(R.id.deleteAcc);

            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getAdapterPosition());
//                    Log.e("adapterpos", "onClick: "+accountList.get(getAdapterPosition()).getAcc_id());
//                    Log.e("adapterpos", "pos: "+getAdapterPosition());

                }
            });
        }

        public void removeItem(int pos) {
            mGetRef = FirebaseDatabase.getInstance().getReference().child("archives").child("ver1");
            mGetRef.child(accountList.get(pos).getAcc_id()).removeValue();
//            accountList.remove(pos);
//            notifyItemRemoved(pos);
            notifyDataSetChanged();
        }
    }
}
