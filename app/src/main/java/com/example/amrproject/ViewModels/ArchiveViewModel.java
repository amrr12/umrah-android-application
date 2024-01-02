package com.example.amrproject.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amrproject.fireStoreRepository.FirebaseQueries;
import com.example.amrproject.models.MootamarAR;

import java.util.List;

public class ArchiveViewModel extends ViewModel {

    FirebaseQueries fq ;

    private MutableLiveData<List<MootamarAR>> MootamarList = new MutableLiveData<>();

    private MutableLiveData<Integer> isCreated = new MutableLiveData<>();

    public ArchiveViewModel() {
        fq = new FirebaseQueries();
    }

    public LiveData<List<MootamarAR>> getMootamrList() {
        return MootamarList;
    }

    public LiveData<Integer> getIsCreated() {
        return isCreated;
    }

    public void get_mootamarArList() {
        fq.get_MootamarAR(new FirebaseQueries.OnDataFetchedListener() {
            @Override
            public void onDataFetched(List<MootamarAR> mootamarARList) {
                MootamarList.postValue(mootamarARList);
            }
        });
    }

    public void add_to_ar(MootamarAR mootamarAR) {
        isCreated.setValue(fq.add_to_ar(mootamarAR));
    }

}
