package com.example.prash.xapodemo.factory;

import android.util.Log;

import com.example.prash.xapodemo.engine.Engine;
import com.example.prash.xapodemo.engine.IEngine;
import com.example.prash.xapodemo.eventbus.EventbusImpl;
import com.example.prash.xapodemo.eventbus.IEventbus;

import java.util.ArrayList;
import java.util.List;

public class EngineLifeCycleController {

    private static final String TAG = "EngineLifeCycleControl";
    private List<IEngine> mEngines = new ArrayList<IEngine>();
    private final IEventbus mEventBus;
    private String accessToken;
    private boolean isAuthenticated;

    public EngineLifeCycleController() {
        mEventBus = EventbusImpl.getInstance();
    }

    public void init() {
        Log.d(TAG, "init");

        //initiate business modules
        //ADD ALL MODULES HERE
        mEngines = new ArrayList<IEngine>();
        mEngines.add(Engine.getInstance());
        for (IEngine manager : mEngines) {
            manager.onStart();
        }
    }
}

