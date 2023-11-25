package com.example.amrproject.ViewModels;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.amrproject.Repositories.UmrahRepository;
import com.example.amrproject.models.Umrah;


public class CreateUmrahViewModel extends ViewModel {

    private UmrahRepository umrahRepository;

    public Boolean insertState = true ;

    public CreateUmrahViewModel(Application application) {
        super();
        umrahRepository = new UmrahRepository(application);
    }
    public void add_umrah(String date , String hotel, int takalif) {
        Umrah umrah = new Umrah(date, hotel, takalif);
        umrahRepository.createUmrah(umrah).subscribe(()-> insertState = true,
                throwable -> insertState = false);
    }
}
