package com.example.amrproject.ViewModels;

import android.app.Application;
import android.database.Observable;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amrproject.Repositories.MootamarRepository;
import com.example.amrproject.models.Mootamar;


public class MootamarViewViewModel extends ViewModel {

    private MootamarRepository mootamarRepository;

    private MutableLiveData<Mootamar> mootamar = new MutableLiveData<>();

    public MootamarViewViewModel(Application application) {
        super();
        mootamarRepository = new MootamarRepository(application);
    };

    public MutableLiveData<Mootamar> getMootamar() {
        return mootamar;
    }

    public void get_mootamar(int id){
        mootamarRepository.get_mootamar(id).subscribe(mootamar1 -> mootamar.setValue(mootamar1),throwable -> Log.d("404","not found"));
    }

}
