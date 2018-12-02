package com.example.prash.xapodemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.prash.xapodemo.engine.Engine;
import com.example.prash.xapodemo.eventbus.EventbusImpl;
import com.example.prash.xapodemo.eventbus.IEventbus;
import com.example.prash.xapodemo.events.IGetServiceSuccessEngineEvent;
import com.example.prash.xapodemo.threading.BusinessExecutor;
import com.example.prash.xapodemo.threading.IBusinessExecutor;

import org.greenrobot.eventbus.Subscribe;
public class AppController extends Application {

    public static final String TAG = "AppController";
    private static AppController mInstance;
    private static Context sContext;
    private IBusinessExecutor businessExecutor;
    private IEventbus eventbus;

    public static synchronized AppController getInstance() {
        return mInstance;
    }
    public static Context getContext(){
        return sContext;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Oncreate");
        mInstance = this;
        sContext = this;
        super.onCreate();
        eventbus = EventbusImpl.getInstance();
        eventbus.register(this);
        businessExecutor = BusinessExecutor.getInstance();
        businessExecutor.executeInBusinessThread(new Runnable() {
            @Override
            public void run() {
                Engine.getInstance().onStart();

            }
        });
        Log.d(TAG, "Oncreate");
    }

    @Subscribe
    public void dummyEvent(IGetServiceSuccessEngineEvent event) {}

}
