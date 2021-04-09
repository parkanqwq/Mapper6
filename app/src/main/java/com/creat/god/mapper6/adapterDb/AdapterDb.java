package com.creat.god.mapper6.adapterDb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creat.god.mapper6.R;
import com.creat.god.mapper6.model.User;

import java.util.List;

public class AdapterDb extends RecyclerView.Adapter<AdapterDb.UserViewHolder>{

    private List<User> User;
    private Context mContext;

    public AdapterDb(Context mContext, List<User> User) {
        this.mContext = mContext;
        this.User = User;
    }

    @NonNull
    @Override
    public AdapterDb.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new AdapterDb.UserViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final AdapterDb.UserViewHolder holder, final int position) {
        User user = User.get(position);

        holder.id.setText("ID: " + user.getId());
        holder.name.setText("Name: " + user.getName());
        holder.power.setText("Power: " + user.getPower());
    }

    @Override
    public int getItemCount() {
        return User.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView name, power, id;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            power = itemView.findViewById(R.id.power);
        }
    }
}

