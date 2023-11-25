package com.example.amrproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.amrproject.R;
import com.example.amrproject.models.Umrah;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context context;
    public List<Umrah> itemList ;

    public GridAdapter(Context context, List<Umrah> itemList) {
        this.context = context;
        this.itemList = itemList;
    }


    @Override
    public int getCount() {
        if (itemList == null)
                return 0;
        return itemList.size();
    }

    @Override
    public Umrah getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.gridelement, null);
        } else {
            gridView = convertView;
        }


        TextView itemText2 = gridView.findViewById(R.id.card_content);
        TextView itemText3 = gridView.findViewById(R.id.card_date);

        Umrah item = itemList.get(position);

        // Set the data for the current item

        itemText2.setText("موعد الرحلة :");
        itemText3.setText(item.getDate());

        return gridView;
    }
}