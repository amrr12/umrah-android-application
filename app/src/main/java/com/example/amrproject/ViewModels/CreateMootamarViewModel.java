package com.example.amrproject.ViewModels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amrproject.Repositories.MootamarRepository;
import com.example.amrproject.models.Mootamar;
import com.example.amrproject.models.Umrah;

public class CreateMootamarViewModel extends ViewModel {

    private MootamarRepository mootamarRepository;

    private MutableLiveData<String> mootamarcreated  = new MutableLiveData<>();

    public CreateMootamarViewModel(Application application){
        super();
        mootamarRepository = new MootamarRepository(application);
    }

    public LiveData<String> getMootamarCreated() {
        return mootamarcreated;
    }

    public void add_mootamar(String fullname, int phoneName, int price, int gendre, int ghorfaId, int umrahId){
        Mootamar mootamar = new Mootamar(fullname,phoneName,price,gendre,ghorfaId,umrahId);
        mootamarRepository.createMootamar(mootamar).subscribe(()->mootamarcreated.setValue("mootamr created successfully"),throwable -> mootamarcreated.setValue("mootamr not created"));
    }
}
