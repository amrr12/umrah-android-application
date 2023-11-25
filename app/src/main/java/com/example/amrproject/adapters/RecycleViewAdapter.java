package com.example.amrproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.amrproject.R;
import com.example.amrproject.models.Mootamar;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    public List<Mootamar> localDataSet;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView esem;
        private final TextView phonemootamar;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            esem = view.findViewById(R.id.esmmootamarelement);
            phonemootamar = view.findViewById(R.id.phonemootamarelement);
        }

        public TextView getEsem() {
            return esem;
        }
        public TextView getPhonemootamar() {
            return phonemootamar;
        }
    }


    public RecycleViewAdapter() {
    }

    public void setLocalDataSet(List<Mootamar> localDataSet) {
        this.localDataSet = localDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rowmootamarelement, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {



        if (!localDataSet.isEmpty()) {
            String mootamarFullName = localDataSet.get(position).getFullName();
            int mootamarPhoneNumber = localDataSet.get(position).getPhoneNumber();

            viewHolder.getEsem().setText(mootamarFullName);
            viewHolder.getPhonemootamar().setText(String.valueOf(mootamarPhoneNumber));
        }
    }


    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }




}