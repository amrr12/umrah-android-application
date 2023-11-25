package com.example.amrproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amrproject.ViewModels.CreateUmrahViewModel;
import com.google.android.material.button.MaterialButton;


public class CreateUmrah extends Fragment {

    private CreateUmrahViewModel viewModel;
    MaterialButton back;

    MaterialButton add_mootamar;
    TextView rdate;

    EditText hotel, takalif;

    CalendarView calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_umrah, container, false);

        viewModel = new CreateUmrahViewModel(requireActivity().getApplication());
        back = view.findViewById(R.id.umrahback1);
        add_mootamar = view.findViewById(R.id.add_mootamar);
        rdate = view.findViewById(R.id.rdate);
        calendar = view.findViewById(R.id.calendar);

        hotel = view.findViewById(R.id.hotel);
        takalif = view.findViewById(R.id.takalif);




        back.setOnClickListener(view1 -> {
            Fragment fragHome = new HomeFragment();
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
            ft.replace(R.id.fragment_container, fragHome);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        });

        calendar.setOnDateChangeListener((calendarView, year, month, day) -> {
            String date = day+"/"+month+"/"+year;
            rdate.setText(date);
        });

        add_mootamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.add_umrah(rdate.getText().toString(), hotel.getText().toString(),Integer.valueOf(takalif.getText().toString()));
                if (viewModel.insertState == true) {
                    Toast.makeText(getContext(), "تم اضافة العمرة",Toast.LENGTH_SHORT).show();
                    Fragment fragHome = new HomeFragment();
                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
                    ft.replace(R.id.fragment_container, fragHome);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }else {
                    Toast.makeText(getContext(), "لم يتم اضافة العمرة, الرجاء اعادة المحاولة",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}