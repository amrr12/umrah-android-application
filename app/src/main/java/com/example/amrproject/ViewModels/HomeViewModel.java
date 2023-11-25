package com.example.amrproject.ViewModels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amrproject.Repositories.UmrahRepository;
import com.example.amrproject.models.Umrah;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private UmrahRepository umrahRepository;

    private MutableLiveData<List<Umrah>> umrahList = new MutableLiveData<>();

    public boolean empty = false;

    public HomeViewModel(Application application) {
        super();
        umrahRepository = new UmrahRepository(application);
    }

    public LiveData<List<Umrah>> getUmrahList() {
        return umrahList;
    }

    public void get_umrah() {
        umrahRepository.getUmrah().subscribe(umrahListFromRepo -> {
                    umrahList.setValue(umrahListFromRepo);
                },
                throwable -> empty = true);
    }





}
