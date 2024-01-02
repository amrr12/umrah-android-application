package com.example.amrproject.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amrproject.R;
import com.example.amrproject.ViewModels.ArchiveViewModel;
import com.example.amrproject.adapters.ARadapter;


public class ArchiveFragment extends Fragment {

    RecyclerView list;

    ArchiveViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_archive, container, false);
        viewModel=new ArchiveViewModel();

        list = view.findViewById(R.id.mootamarlistar);

        viewModel.get_mootamarArList();

        ARadapter adapter = new ARadapter();

        viewModel.getMootamrList().observe(getViewLifecycleOwner(),list->{

            adapter.setLocalDataSet(list);
            adapter.notifyDataSetChanged();
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);

        return view;
    }
}