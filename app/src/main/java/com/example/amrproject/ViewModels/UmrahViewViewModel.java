package com.example.amrproject.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amrproject.Repositories.GhorfaRepository;
import com.example.amrproject.Repositories.MootamarRepository;
import com.example.amrproject.Repositories.UmrahRepository;
import com.example.amrproject.models.Mootamar;
import com.example.amrproject.models.Umrah;
import com.example.amrproject.models.UmrahWithMootamar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class UmrahViewViewModel extends ViewModel {

    private UmrahRepository umrahRepository;

    private MootamarRepository mootamarRepository;

    private GhorfaRepository ghorfaRepository;

    public MutableLiveData<List<Mootamar>> mootamarList = new MutableLiveData<>() ;

    private MutableLiveData<UmrahWithMootamar> umrahLiveData = new MutableLiveData<>();

    private MutableLiveData<String> deleteumrah = new MutableLiveData<>();

    private MutableLiveData<String> updateumrah = new MutableLiveData<>();

    private MutableLiveData<Double> marabih = new MutableLiveData<>();
    public int error = 0;

    public UmrahViewViewModel(Application application) {
        super();
        umrahRepository = new UmrahRepository(application);
        mootamarRepository = new MootamarRepository(application);
        ghorfaRepository = new GhorfaRepository(application);
    }

    public LiveData<UmrahWithMootamar> getUmrahLiveData() {
        return umrahLiveData;
    }

    public LiveData<Double> getMarabihLiveData() {
        return marabih;
    }
    public LiveData<String> getupdateumrah() {
        return updateumrah;
    }

    public MutableLiveData<List<Mootamar>> getMootamarList() {
        return mootamarList;
    }

    public void findUmrahById(int id) {
        umrahRepository.findUmrahById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(umrah -> {
                    umrahLiveData.setValue(umrah);
                    Log.d("umrah", umrahLiveData.toString());
                    mootamarList.setValue(umrah.mootamarList);
                }, throwable -> {
                    error = 1;
                    Log.e("error", String.valueOf(error));
                });
    }

    public void delete_umrah(Umrah umrah) {

        Completable.concatArray(ghorfaRepository.delete_ghoraf(umrah.getId()), mootamarRepository.deleteMootamrs(umrah.getId()),  umrahRepository.deleteUmrah(umrah))
                .subscribe(
                        () -> {
                            // All operations completed successfully
                            Log.d("success", "success");
                            deleteumrah.setValue("done");
                        },
                        throwable -> {
                            // Handle the error
                            Log.d("failed", "failed");
                            deleteumrah.setValue("error");
                        }
                );
    }

    public void update_umrah(int id,String hotel,int price){
        umrahRepository.updateUmrah(id, hotel, price).subscribe(()->updateumrah.setValue("تم تعديل العمرة"),throwable ->updateumrah.setValue("الرجاء اعادة المحاولة"));
    }

    /*public void getMootamarList(int umrahid) {
        mootamarRepository.get_mootamars(umrahid).subscribe(list->{
                    mootamarList.setValue(list);
                    Log.d("mootamar", mootamarList.toString());}
                ,throwable -> error = 1);
    }*/

    public void delete_mootamar(Mootamar mootamar) {
        mootamarRepository.deleteMootamr(mootamar).subscribe(()->error = 0,throwable -> error = 1);
    }

    public void add_mootamar(Mootamar mootamar) {
        mootamarRepository.createMootamar(mootamar).subscribe(()->error = 0,throwable -> error = 1);
    }

    public void calculateMarabih() {
        UmrahWithMootamar umrah = getUmrahLiveData().getValue();
        if (umrah != null) {
            int takalif = umrah.umrah.getTakalif();
            double priceallMootamar = 0;
            List<Mootamar> mootamarList = umrah.mootamarList;
            if (mootamarList != null) {
                for (Mootamar i : mootamarList) {
                    priceallMootamar += i.getPrice();
                }
                marabih.setValue(priceallMootamar - takalif);
            }
        }
    }

}
