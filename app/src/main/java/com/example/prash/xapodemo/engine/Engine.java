package com.example.prash.xapodemo.engine;

import android.util.Log;

import com.example.prash.xapodemo.eventbus.EventbusImpl;
import com.example.prash.xapodemo.eventbus.IEventbus;
import com.example.prash.xapodemo.events.IGetServiceFailureEngineEvent;
import com.example.prash.xapodemo.events.IGetServiceSuccessEngineEvent;
import com.example.prash.xapodemo.pojo.MainPojo;
import com.example.prash.xapodemo.pojo.SearchRepo;
import com.example.prash.xapodemo.rest.RetrofitClient;
import com.example.prash.xapodemo.threading.BusinessExecutor;
import com.example.prash.xapodemo.threading.IBusinessExecutor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Engine implements IEngine {

    private static final String TAG = "Engine";
    private IBusinessExecutor mBusinessExecutor;
    private IEventbus mEventBus;
    private static Engine sInstance = new Engine();

    public static Engine getInstance(){
        return sInstance;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        mBusinessExecutor = BusinessExecutor.getInstance();
        mEventBus = EventbusImpl.getInstance();
    }

    @Override
    public void getGithubTrendingList(int page) {
        Log.e(TAG, "getGithubTrendingList");
        RestInterface service = RetrofitClient.getRetrofitInstance().create(RestInterface.class);
        Call<MainPojo> call = service.getGithubTrendingList(page);

        call.enqueue(new Callback<MainPojo>() {
            @Override
            public void onResponse(Call<MainPojo> call, Response<MainPojo> response) {
                if(response != null && response.body() != null) {
                    final ArrayList<SearchRepo> searchRepos = response.body().getItems();

                    //pushing the POJO to the viewModel Layer
                    mEventBus.post(new IGetServiceSuccessEngineEvent() {
                        @Override
                        public ArrayList<SearchRepo> getSearchList() {
                            return searchRepos;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MainPojo> call, Throwable t) {
                mEventBus.post(new IGetServiceFailureEngineEvent() {
                    @Override
                    public String getErrorMessage() {
                        return "Something went Wrong, Please Try Again";
                    }
                });
            }
        });
    }
}
