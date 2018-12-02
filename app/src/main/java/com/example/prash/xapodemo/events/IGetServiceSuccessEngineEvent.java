package com.example.prash.xapodemo.events;

import com.example.prash.xapodemo.pojo.SearchRepo;
import java.util.ArrayList;

public interface IGetServiceSuccessEngineEvent {
    ArrayList<SearchRepo> getSearchList();
}
