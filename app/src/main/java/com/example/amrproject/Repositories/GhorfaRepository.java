package com.example.amrproject.Repositories;

import static kotlinx.coroutines.flow.FlowKt.subscribeOn;

import android.app.Application;

import com.example.amrproject.Dao.GhorfaDao;
import com.example.amrproject.DataBase.DataBase;
import com.example.amrproject.models.Ghorfa;
import com.example.amrproject.models.GhorfaWithMootamar;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GhorfaRepository {
    private GhorfaDao ghorfaDao;

    public GhorfaRepository(Application application){
        DataBase db = DataBase.getInstance(application);
        ghorfaDao = db.ghorfaDao();
    }

    public Completable createGhorfa (Ghorfa ghorfa){
        return ghorfaDao.create_ghorfa(ghorfa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<GhorfaWithMootamar>> find_ghorfa_by_type(String type){
        return ghorfaDao.find_ghorfa_by_type(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    };

    public Observable<List<GhorfaWithMootamar>> get_ghorfaWithMootamar(){
        return ghorfaDao.get_ghoraf()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<GhorfaWithMootamar>> find_ghorfa_by_id(int id){
        return ghorfaDao.find_ghorfa_by_id(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delete_ghorfa(Ghorfa ghorfa){
        return ghorfaDao.delete_Ghorfa(ghorfa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Integer>> getLastGhorfa(){
        return ghorfaDao.getLastGhorfa();
    }

    public Completable delete_ghoraf(int id){
        return ghorfaDao.delete_Ghoraf(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
