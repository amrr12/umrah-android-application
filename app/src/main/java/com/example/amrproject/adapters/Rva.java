package com.example.amrproject.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amrproject.R;
import com.example.amrproject.models.Mootamar;

import java.util.List;

public class Rva extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{

    private List<Mootamar> localDataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView esem;
        private final TextView phonemootamar;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            esem = view.findViewById(R.id.esmmootamarelement);
            phonemootamar = view.findViewById(R.id.phonemootamarelement);

            phonemootamar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uri = "tel:" + phonemootamar.getText().toString().trim();
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse(uri));
                    v.getContext().startActivity(i);
                }
            });
        }

        public TextView getEsem() {
            return esem;
        }
        public TextView getPhonemootamar() {
            return phonemootamar;
        }
    }

    public void setLocalDataSet(List<Mootamar> localDataSet) {
        this.localDataSet = localDataSet;
    }
    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowmootamarelement, parent, false);

        return new RecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        if (!localDataSet.isEmpty()) {
            String mootamarFullName = localDataSet.get(position).getFullName();
            int mootamarPhoneNumber = localDataSet.get(position).getPhoneNumber();

            holder.getEsem().setText(mootamarFullName);
            holder.getPhonemootamar().setText(String.valueOf(mootamarPhoneNumber));
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet != null ? localDataSet.size() : 0;
    }
}
