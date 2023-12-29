package com.example.amrproject.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amrproject.R;
import com.example.amrproject.ViewModels.MootamarViewViewModel;
import com.example.amrproject.adapters.RecycleViewAdapter;
import com.example.amrproject.adapters.Rva;
import com.example.amrproject.models.Mootamar;
import com.google.android.material.button.MaterialButton;

public class MootamarView extends Fragment {

    MootamarViewViewModel viewModel;

    EditText esmmmootamar;

    EditText phonemootamar;

    EditText price;

    RecyclerView roommates;

    MaterialButton edit_mootamar,save_changesmoptamar,mootamrback,deletemootamar;
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

        roommates = view.findViewById(R.id.roommateslist);

        edit_mootamar = view.findViewById(R.id.edit_mootamar);
        save_changesmoptamar = view.findViewById(R.id.save_changesmoptamar);
        mootamrback = view.findViewById(R.id.mootamrback);
        deletemootamar = view.findViewById(R.id.deletemootamar);
        viewModel.getMootamar().observe(getViewLifecycleOwner(),mootamar -> {
            esmmmootamar.setText(mootamar.getFullName());
            phonemootamar.setText(String.valueOf(mootamar.getPhoneNumber()));
            price.setText(String.valueOf(mootamar.getPrice()));
            viewModel.getRoomateslist(mootamar.getGhorfaid());
        });



        Rva adapter = new Rva();
        viewModel.getRoomates1().observe(getViewLifecycleOwner(),list->{
            for (Mootamar i : list) {
                Log.d("roomates",i.getFullName().toString());
            }
            adapter.setLocalDataSet(list);
            adapter.notifyDataSetChanged();
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        roommates.setLayoutManager(layoutManager);
        roommates.setAdapter(adapter);




        edit_mootamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_changesmoptamar.setVisibility(MaterialButton.VISIBLE);
                esmmmootamar.setEnabled(true);
                phonemootamar.setEnabled(true);
                price.setEnabled(true);
            }
        });

        save_changesmoptamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = esmmmootamar.getText().toString();
                int newPrice = Integer.valueOf(price.getText().toString());
                int newPhone = Integer.valueOf(phonemootamar.getText().toString());
                viewModel.update_mootamar(newName,newPrice,newPhone);
                viewModel.getGetupdateMootamr().observe(getViewLifecycleOwner(),mootamar->{
                    Toast.makeText(getContext(),mootamar,Toast.LENGTH_SHORT).show();
                });
            }
        });

        mootamrback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int umrahid = viewModel.getMootamar().getValue().getUmrahid();
                Bundle bundle = new Bundle();
                bundle.putString("umrahid",String.valueOf(umrahid));
                Fragment frag= new UmrahView();
                frag.setArguments(bundle);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
                ft.replace(R.id.fragment_container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        deletemootamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(requireActivity()).create();
                alertDialog.setMessage("لكي تحذف المعتمر انقر على حذف المعتمر");

                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "حذف المعتمر", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int umrahid = viewModel.getMootamar().getValue().getUmrahid();
                        Bundle bundle = new Bundle();
                        bundle.putString("umrahid",String.valueOf(umrahid));

                        viewModel.delete_mootamar(viewModel.getMootamar().getValue());

                        Fragment frag= new UmrahView();
                        frag.setArguments(bundle);
                        FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
                        ft.replace(R.id.fragment_container, frag);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.commit();
                    }

                });
                alertDialog.show();
            }
        });


        return view;
    }



}