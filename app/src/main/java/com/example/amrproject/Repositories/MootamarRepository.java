package com.example.amrproject.Repositories;

import android.app.Application;

import com.example.amrproject.Dao.MootamarDao;
import com.example.amrproject.Dao.UmrahDao;
import com.example.amrproject.DataBase.DataBase;
import com.example.amrproject.models.Mootamar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MootamarRepository {
    private MootamarDao mootamarDao;


    public MootamarRepository(Application application) {
        DataBase db = DataBase.getInstance(application);
        mootamarDao = db.mootamarDao();
    }

    public Completable createMootamar (Mootamar mootamar){
        return mootamarDao.create_mootamar(mootamar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable updateMootamar (String newName, int newPrice, int newPhone){
        return mootamarDao.update_mootamar(newName,newPrice,newPhone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteMootamr (Mootamar mootamar){
        return mootamarDao.delete_mootamar(mootamar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteMootamrs (int id){
        return mootamarDao.delete_mootamars(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Mootamar>> get_mootamars(int umrahid){
        return mootamarDao.get_mootamar(umrahid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Mootamar> get_mootamar(int id) {
        return mootamarDao.get_mootamarr(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
