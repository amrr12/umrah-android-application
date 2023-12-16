package com.example.amrproject.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.amrproject.R;
import com.example.amrproject.models.Mootamar;

import java.util.List;

public  class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private List<Mootamar> localDataSet;
    private OnItemClickListener onItemClickListener;


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
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
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


    public RecycleViewAdapter(OnItemClickListener listener) {
        this.onItemClickListener = listener;
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
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {


        if (!localDataSet.isEmpty()) {
            String mootamarFullName = localDataSet.get(position).getFullName();
            int mootamarPhoneNumber = localDataSet.get(position).getPhoneNumber();

            viewHolder.getEsem().setText(mootamarFullName);
            viewHolder.getPhonemootamar().setText(String.valueOf(mootamarPhoneNumber));
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call onItemClick when an item is clicked
                    onItemClickListener.onItemClick(position);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return localDataSet == null ? 0 : localDataSet.size();
    }




}