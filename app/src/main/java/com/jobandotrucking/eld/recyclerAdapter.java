package com.jobandotrucking.eld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eld.R;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<records> recordsList;

    public recyclerAdapter(ArrayList<records> recordsList){
        this.recordsList = recordsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView clientText;
        private TextView detailsText;

        public MyViewHolder(final View view){
            super(view);

            clientText = view.findViewById(R.id.clientTxt);
            detailsText = view.findViewById(R.id.detailsTxt);

        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_recycler, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String name = recordsList.get(position).getClient();
        String details = recordsList.get(position).getDetails();

        holder.clientText.setText(name);
        holder.detailsText.setText(details);
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }
}
