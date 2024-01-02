package com.example.amrproject.adapters;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amrproject.R;
import com.example.amrproject.models.MootamarAR;

import java.util.List;

public class ARadapter extends RecyclerView.Adapter<ARadapter.ViewHolder> {

    private List<MootamarAR> localDataSet;

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

    public void setLocalDataSet(List<MootamarAR> localDataSet) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowmootamarelement, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("ARadapter", "onBindViewHolder position: " + position);
        if (localDataSet != null && localDataSet.size() > position) {
            MootamarAR mootamarAR = localDataSet.get(position);
            holder.getEsem().setText(mootamarAR.getFullname());
            holder.getPhonemootamar().setText(String.valueOf(mootamarAR.getPhoneNumber()));
        }
    }

    @Override
    public int getItemCount() {

            int size = localDataSet != null ? localDataSet.size() : 0;
            Log.d("ARadapter", "Item count: " + size);
            return size;

    }
}
