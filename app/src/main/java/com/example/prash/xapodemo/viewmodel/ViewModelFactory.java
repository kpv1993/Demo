package com.example.prash.xapodemo.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.prash.xapodemo.eventbus.EventbusImpl;
import com.example.prash.xapodemo.eventbus.IEventbus;
import com.example.prash.xapodemo.threading.BusinessExecutor;
import com.example.prash.xapodemo.threading.IBusinessExecutor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory viewModelFactory = new ViewModelFactory();
    private IEventbus eventBus;
    private IBusinessExecutor businessExecutor;

    private ViewModelFactory() {
        this.eventBus = EventbusImpl.getInstance();
        this.businessExecutor = BusinessExecutor.getInstance();
    }

    public static ViewModelFactory getInstance(){return viewModelFactory;}

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(eventBus, businessExecutor);
        }
        throw new IllegalArgumentException("Unknown model class " + modelClass);
    }

}