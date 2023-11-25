package com.example.amrproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import com.example.amrproject.ViewModels.HomeViewModel;
import com.example.amrproject.adapters.GridAdapter;
import com.example.amrproject.models.Umrah;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;


public class HomeFragment extends Fragment {

    MaterialButton addUmrah;

    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        addUmrah = view.findViewById(R.id.addUmrah);
        GridView gridView = view.findViewById(R.id.grid_view);
        gridView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        viewModel = new HomeViewModel(requireActivity().getApplication());
        viewModel.get_umrah();

        viewModel.getUmrahList().observe(getViewLifecycleOwner(), umrahListFromViewModel -> {
            // Update the GridView adapter when the Umrah list changes
            GridAdapter adapter = new GridAdapter(requireContext(), umrahListFromViewModel);
            gridView.setAdapter(adapter);
        });
        gridView.setOnItemClickListener((parent, view1, position, id) -> {

            Umrah umrah = viewModel.getUmrahList().getValue().get(position);
            Bundle bundle = new Bundle();
            bundle.putString("umrahid",String.valueOf(umrah.getId()));
            Fragment frag = new UmrahView();
            frag.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        });

        addUmrah.setOnClickListener(view1 ->  {
                Fragment frag = new CreateUmrah();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

        });

        return view;
    }
}