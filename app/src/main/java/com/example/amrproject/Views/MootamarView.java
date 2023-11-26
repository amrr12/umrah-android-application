package com.example.amrproject.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.amrproject.R;
import com.example.amrproject.ViewModels.MootamarViewViewModel;

public class MootamarView extends Fragment {

    MootamarViewViewModel viewModel;

    EditText esmmmootamar;

    EditText phonemootamar;

    EditText price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int mootamarid = Integer.valueOf(getArguments().getString("mootamar"));
        viewModel = new MootamarViewViewModel(requireActivity().getApplication());
        viewModel.get_mootamar(mootamarid);
        View view = inflater.inflate(R.layout.fragment_mootamar, container, false);

        esmmmootamar = view.findViewById(R.id.esmmmootamar);
        phonemootamar = view.findViewById(R.id.phonemootamar);
        price = view.findViewById(R.id.price);

        viewModel.getMootamar().observe(getViewLifecycleOwner(),mootamar -> {
            esmmmootamar.setText(mootamar.getFullName());
            phonemootamar.setText(String.valueOf(mootamar.getPhoneNumber()));
            price.setText(String.valueOf(mootamar.getPrice()));
        });


        return view;
    }
}