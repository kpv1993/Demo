package com.example.prash.xapodemo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainPojo {
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("incomplete_results")
    @Expose
    private boolean incompleteResults;

    @SerializedName("items")
    @Expose
    private ArrayList<SearchRepo> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public ArrayList<SearchRepo> getItems() {
        return items;
    }

    public void setItems(ArrayList<SearchRepo> items) {
        this.items = items;
    }
}
