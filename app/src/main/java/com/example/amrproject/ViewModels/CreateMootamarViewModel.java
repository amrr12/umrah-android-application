package com.example.amrproject.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amrproject.Repositories.GhorfaRepository;
import com.example.amrproject.Repositories.MootamarRepository;
import com.example.amrproject.models.Ghorfa;
import com.example.amrproject.models.GhorfaWithMootamar;
import com.example.amrproject.models.Mootamar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CreateMootamarViewModel extends ViewModel {

    private MootamarRepository mootamarRepository;
    private GhorfaRepository ghorfaRepository;

    private MutableLiveData<String> mootamarcreated = new MutableLiveData<>();

    private MutableLiveData<List<GhorfaWithMootamar>> ghorfa_by_Type = new MutableLiveData<>();

    public CreateMootamarViewModel(Application application) {
        super();
        mootamarRepository = new MootamarRepository(application);
        ghorfaRepository = new GhorfaRepository(application);
    }

    public LiveData<String> getMootamarCreated() {
        return mootamarcreated;
    }



    public void get_ghoraf_by_type(String type) {
        ghorfaRepository.find_ghorfa_by_type(type).subscribe((ghoraf) -> ghorfa_by_Type.setValue(ghoraf), throwable -> Log.d("failed", "failed"));
    }

    public void create_ghorfa(Ghorfa ghorfa) {
        ghorfaRepository.createGhorfa(ghorfa).subscribe(() -> Log.d("created", "created"), throwable -> Log.d("failed", "failed"));
    }

    public void add_mootamar(String fullname, int phoneName, int price, int gendre, int umrahId, String type) {
        get_ghoraf_by_type(type);

        // Use a boolean flag to ensure that the logic is executed only once
        AtomicReference<Boolean> isMootamarCreated = new AtomicReference<>(false);

        // Subscribe to ghorfa_by_Type LiveData
        ghorfa_by_Type.observeForever(ghorfaList -> {
            if (!isMootamarCreated.get()) {
                int test = getGhorfaNotFull(type);

                if (test == -1) {
                    Ghorfa ghorfa = new Ghorfa(type, umrahId);
                    create_ghorfa(ghorfa);

                    // Subscribe to getLastGhorfa
                    ghorfaRepository.getLastGhorfa().subscribe(ghorfa1 -> {
                        Mootamar mootamar = new Mootamar(fullname, phoneName, price, gendre, ghorfa1.get(0), umrahId);
                        createMootamarAndSetResult(mootamar);
                    });
                } else {
                    Mootamar mootamar = new Mootamar(fullname, phoneName, price, gendre, test, umrahId);
                    createMootamarAndSetResult(mootamar);
                }

                // Set the flag to true to prevent duplicate execution
                isMootamarCreated.set(true);
            }
        });
    }

    private void createMootamarAndSetResult(Mootamar mootamar) {
        mootamarRepository.createMootamar(mootamar).subscribe(
                () -> mootamarcreated.setValue("mootamar created successfully"),
                throwable -> mootamarcreated.setValue("mootamar not created")
        );
    }

    public int getGhorfaNotFull(String type) {
        // Check if the LiveData or its value is null
        if (ghorfa_by_Type == null || ghorfa_by_Type.getValue()== null) {
            return -1;
        }

        List<GhorfaWithMootamar> ghoraf = ghorfa_by_Type.getValue();

        // Check if the list is empty
        if (ghoraf == null) {
            return -1;
        }

        for (GhorfaWithMootamar ghorfa : ghoraf) {
            if(ghorfa.ghorfa.getType().equals(type)) {
                if (ghorfa.mootamarList.size() < Integer.valueOf(type)) {
                    return ghorfa.ghorfa.getId();
                }
            }
        }

        return -1;

    }


}