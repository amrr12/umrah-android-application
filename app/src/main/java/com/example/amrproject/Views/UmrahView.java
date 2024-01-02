package com.example.amrproject.Views;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amrproject.R;
import com.example.amrproject.ViewModels.UmrahViewViewModel;
import com.example.amrproject.adapters.RecycleViewAdapter;
import com.example.amrproject.models.Mootamar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class UmrahView extends Fragment implements RecycleViewAdapter.OnItemClickListener {

    UmrahViewViewModel viewModel;


    TextView umrah_date,umrahmarabih;
    MaterialButton back;
    MaterialButton lock_unlock;
    MaterialButton delete;

    MaterialButton save,addMootamar;

    EditText ikama;
    EditText takalif;

    RecyclerView listmootamar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new UmrahViewViewModel(requireActivity().getApplication());



        String umrahid = getArguments().getString("umrahid");
        Log.d("umrahId",umrahid);
        View view = inflater.inflate(R.layout.fragment_umrah, container, false);
        viewModel.findUmrahById(Integer.valueOf(umrahid));

        AtomicBoolean isLocked = new AtomicBoolean(true);
        back = view.findViewById(R.id.umrahback);
        lock_unlock = view.findViewById(R.id.dataaccessumrah);
        delete = view.findViewById(R.id.deleteumrah);
        save = view.findViewById(R.id.save_changes);
        addMootamar = view.findViewById(R.id.add_mootamar);

        umrah_date = view.findViewById(R.id.umrah_date);
        ikama = view.findViewById(R.id.hotel);
        takalif = view.findViewById(R.id.takalif);
        umrahmarabih = view.findViewById(R.id.umrahmarabih);

        viewModel.getUmrahLiveData().observe(getViewLifecycleOwner(), umrah -> {
            if (umrah != null) {
                umrah_date.setText(umrah.getDate());
                ikama.setText(umrah.getHotel());
                takalif.setText(String.valueOf(umrah.getTakalif()));
                viewModel.calculateMarabih();
            }
        });

        viewModel.getMootamarList(Integer.valueOf(umrahid));


        back.setOnClickListener(view1 -> {
            Fragment fragHome = new HomeFragment();
            FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
            ft.replace(R.id.fragment_container, fragHome);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        });

        lock_unlock.setOnClickListener(view1 -> {
            isLocked.set(!isLocked.get());

            if (isLocked.get()==true) {
                Drawable icon = getResources().getDrawable(R.drawable.baseline_lock_24);
                lock_unlock.setIcon(icon);
                ikama.setEnabled(true);
                takalif.setEnabled(true);
                save.setVisibility(MaterialButton.VISIBLE);


            }else {
                Drawable icon = getResources().getDrawable(R.drawable.baseline_lock_open_24);
                lock_unlock.setIcon(icon);
                ikama.setEnabled(false);
                takalif.setEnabled(false);
                save.setVisibility(MaterialButton.GONE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price = Integer.valueOf(takalif.getText().toString());
                Log.d("price",String.valueOf(price));
                viewModel.update_umrah(viewModel.getUmrahLiveData().getValue().getId(),ikama.getText().toString(),price);
                viewModel.getupdateumrah().observe(getViewLifecycleOwner(),responce -> {
                    Toast.makeText(requireActivity(),responce,Toast.LENGTH_SHORT).show();
                });
            }
        });

        delete.setOnClickListener(view1 -> {
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(requireActivity()).create();
            alertDialog.setMessage("لكي تحذف هذه العمرة انقر على حذف العمرة");
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "حذف العمرة", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    viewModel.delete_umrah(viewModel.getUmrahLiveData().getValue());
                    dialog.dismiss();
                    Fragment fragHome = new HomeFragment();
                    FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
                    ft.replace(R.id.fragment_container, fragHome);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            });
            alertDialog.show();

        });

        listmootamar = view.findViewById(R.id.mootamarlist);
        RecycleViewAdapter adapter = new RecycleViewAdapter(this);
        viewModel.getMootamarList().observe(getViewLifecycleOwner(),list->{
            adapter.setLocalDataSet(list);
            adapter.notifyDataSetChanged();
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listmootamar.setLayoutManager(layoutManager);
        listmootamar.setAdapter(adapter);



        viewModel.getMarabihLiveData().observe(getViewLifecycleOwner(),marabih->{
            Log.d("marabih",String.valueOf(marabih));
            umrahmarabih.setText(String.valueOf(marabih));
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<Mootamar> mootamarList = viewModel.getMootamarList().getValue();

                switch (direction) {
                    case ItemTouchHelper.RIGHT:
                        if (position >= 0 && position < mootamarList.size()) {

                            // Remove the item from the list
                            Mootamar deletedMootamar = mootamarList.remove(position);
                            viewModel.delete_mootamar(deletedMootamar);
                            adapter.notifyItemRemoved(position);

                            Snackbar.make(view, "حذف المعتمر: " + deletedMootamar.getFullName(), Snackbar.LENGTH_LONG)
                                    .setAction("الغاء", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            // Add the item back to the list
                                            viewModel.add_mootamar(deletedMootamar);
                                            mootamarList.add(position, deletedMootamar);
                                            adapter.notifyItemInserted(position);
                                        }
                                    }).show();
                        }
                        break;
                }
            }



            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(view.getContext(),R.color.delete))
                        .addSwipeRightActionIcon(R.drawable.baseline_delete_25)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(listmootamar);



        listmootamar.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(

        ));

        addMootamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("umrahid",umrahid);
                Fragment frag = new Create_mootamar();
                frag.setArguments(bundle);
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
                ft.replace(R.id.fragment_container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });




        return view;
    }


    @Override
    public void onItemClick(int position) {
        int Mootamarid = viewModel.getMootamarList().getValue().get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putString("mootamar",String.valueOf(Mootamarid));
        Fragment frag = new MootamarView();
        frag.setArguments(bundle);
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();;
        ft.replace(R.id.fragment_container, frag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}

