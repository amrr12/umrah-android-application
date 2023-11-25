package com.example.amrproject.Repositories;


import android.app.Application;
import android.util.Log;

import com.example.amrproject.Dao.UmrahDao;
import com.example.amrproject.DataBase.DataBase;
import com.example.amrproject.models.Mootamar;
import com.example.amrproject.models.Umrah;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UmrahRepository {

    private UmrahDao umrahDao;




    public UmrahRepository(Application application) {
        DataBase db = DataBase.getInstance(application);
        umrahDao = db.umrahDao();

    }

    public Completable  createUmrah(Umrah umrah) {
          return umrahDao.create_umrah(umrah)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public  Observable<List<Umrah>> getUmrah() {
        return  umrahDao.get_umrah()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    } ;

    public Observable<Umrah> findUmrahById(int id) {
        Log.d("umrahRepository", "findUmrahById called with ID: " + id);
        return umrahDao.find_umrah_by_id(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable deleteUmrah(Umrah umrah){
        return umrahDao.delete_umrah(umrah)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    };


    public Completable updateUmrah(int id,String hotel,int price) {
        return umrahDao.update_umrah(id,hotel,price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }





}

