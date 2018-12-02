package com.example.prash.xapodemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.prash.xapodemo.eventbus.IEventbus;
import com.example.prash.xapodemo.events.IGetServiceFailureEngineEvent;
import com.example.prash.xapodemo.events.IGetServiceSuccessEngineEvent;
import com.example.prash.xapodemo.factory.EngineFactory;
import com.example.prash.xapodemo.pojo.SearchRepo;
import com.example.prash.xapodemo.threading.IBusinessExecutor;
import com.example.prash.xapodemo.viewmodel.BaseViewModel;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MainViewModel extends BaseViewModel {

    private static final String TAG = "MainViewModel";

    public MainViewModel(IEventbus eventbus, IBusinessExecutor businessExecutor) {
        super(eventbus, businessExecutor);
    }

    private MutableLiveData<ArrayList<SearchRepo>> getSuccessObservable = new MutableLiveData<ArrayList<SearchRepo>>();

    public LiveData<ArrayList<SearchRepo>> getSuccessObservable() {
        return getSuccessObservable;
    }

    private MutableLiveData<String> getFailureObservable = new MutableLiveData<String>();

    public LiveData<String> getFailureObservable() {
        return getFailureObservable;
    }

    public void getGithubTrendingList(final int page) {
        businessExecutor.executeInBusinessThread(new Runnable() {
            @Override
            public void run() {
                EngineFactory.getEngine().getGithubTrendingList(page);
            }
        });
    }

    //the success POJO pushed from Engine layer comes here
    @Subscribe
    public void onGetListSuccess(final IGetServiceSuccessEngineEvent event){
        Log.d(TAG, "onGetListSuccess");
        getSuccessObservable.postValue(event.getSearchList());
    }

    @Subscribe
    public void onGetListFailure(final IGetServiceFailureEngineEvent event){
        getFailureObservable.postValue(event.getErrorMessage());
        Log.d(TAG, "onGetListFailure");
    }
}
