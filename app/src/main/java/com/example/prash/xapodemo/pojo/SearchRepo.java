package com.example.prash.xapodemo.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRepo implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("watchers_count")
    @Expose
    private int watchersCount;

    @SerializedName("html_url")
    @Expose
    private String url;

    protected SearchRepo(Parcel in) {
        name = in.readString();
        description = in.readString();
        language = in.readString();
        watchersCount = in.readInt();
        url = in.readString();
    }

    public static final Creator<SearchRepo> CREATOR = new Creator<SearchRepo>() {
        @Override
        public SearchRepo createFromParcel(Parcel in) {
            return new SearchRepo(in);
        }

        @Override
        public SearchRepo[] newArray(int size) {
            return new SearchRepo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SearchRepo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", watchersCount=" + watchersCount +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(language);
        dest.writeInt(watchersCount);
        dest.writeString(url);
    }
}
