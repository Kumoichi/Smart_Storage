package com.example.smart_storage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String[] title;
    String[] expiration;
    String[] storageType;
    Context context;

    public MyAdapter(Context c, String[] s1, String[] s2, String[] s3) {
        context = c;
        title = s1;
        expiration = s2;
        storageType = s3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (MainActivity.active == 0) {
            holder.title.setText(title[position]);
            holder.expiry.setText(expiration[position]);
        } else if (MainActivity.active == 1) {
            if (storageType[position].equals("1")) {
                holder.title.setText(title[position]);
                holder.expiry.setText(expiration[position]);
            } else { holder.detailLayout.setLayoutParams(holder.params); }
        } else if (MainActivity.active == 2) {
            if (storageType[position].equals("2")) {
                holder.title.setText(title[position]);
                holder.expiry.setText(expiration[position]);
            } else { holder.detailLayout.setLayoutParams(holder.params); }
        } else if (MainActivity.active == 3) {
            if (storageType[position].equals("3")) {
                holder.title.setText(title[position]);
                holder.expiry.setText(expiration[position]);
            } else { holder.detailLayout.setLayoutParams(holder.params); }
        }

        holder.detailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("detailTitle", title[position]);
                intent.putExtra("detailExpiry", expiration[position]);
                intent.putExtra("detailStorage", storageType[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout.LayoutParams params;
        TextView title, expiry;
        ConstraintLayout detailLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            params = new ConstraintLayout.LayoutParams(0, 0);

            title = itemView.findViewById(R.id.title);
            expiry = itemView.findViewById(R.id.expiry);
            detailLayout =itemView.findViewById(R.id.rootView);
        }
    }
}
