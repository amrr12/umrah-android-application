package com.example.amrproject.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amrproject.R;
import com.example.amrproject.ViewModels.CreateMootamarViewModel;
import com.google.android.material.button.MaterialButton;


public class Create_mootamar extends Fragment {

    CreateMootamarViewModel viewModel;

    Spinner spinner;

    EditText fullname;

    EditText phonenumber;

    EditText price;

    RadioButton male;

    RadioButton female;

    MaterialButton createMootamar,createmootamrback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String umrahid = getArguments().getString("umrahid");
        viewModel = new CreateMootamarViewModel(requireActivity().getApplication());
        View view =  inflater.inflate(R.layout.fragment_create_mootamar, container, false);
        String[] options = {"2", "3", "4", "5"};

        createMootamar = view.findViewById(R.id.createMootamar);
        createmootamrback = view.findViewById(R.id.createmootamrback);
        fullname = view.findViewById(R.id.motamarname);
        phonenumber = view.findViewById(R.id.createphonemootamar);
        price = view.findViewById(R.id.price);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        spinner = view.findViewById(R.id.ghorfatype);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        createMootamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullname.getText().toString();
                Log.d("amr",name);
                int phone = Integer.valueOf(phonenumber.getText().toString());
                int priceMootamar = Integer.valueOf(price.getText().toString());
                int gendre;
                if (male.isChecked()){
                     gendre = 0;
                }else{
                     gendre = 1;
                }
                String selectedSpinnerItem = spinner.getSelectedItem().toString();


                viewModel.add_mootamar(name,phone,priceMootamar,gendre,Integer.valueOf(umrahid),selectedSpinnerItem);
                viewModel.getMootamarCreated().observe(getViewLifecycleOwner(),responce ->{
                    Toast.makeText(getContext(),viewModel.getMootamarCreated().getValue(),Toast.LENGTH_SHORT).show();
                });

                Bundle bundle = new Bundle();
                bundle.putString("umrahid",umrahid);
                Fragment frag = new UmrahView();
                frag.setArguments(bundle);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
                ft.replace(R.id.fragment_container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            }
        });

        createmootamrback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("umrahid",umrahid);
                Fragment frag = new UmrahView();
                frag.setArguments(bundle);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
                ft.replace(R.id.fragment_container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        return  view;
    }
}