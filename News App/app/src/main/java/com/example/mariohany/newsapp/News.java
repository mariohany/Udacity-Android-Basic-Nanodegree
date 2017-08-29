package com.example.mariohany.newsapp;

/**
 * Created by Mario Hany on 2017-07-30.
 */

public class News {
    private String mTitle;
    private String mType;
    private String mSectionName;
    private String mDate;
    private String mWebUrl;

    public News(String mTitle, String mType, String mSectionName, String mDate, String mWebUrl) {
        this.mTitle = mTitle;
        this.mType = mType;
        this.mSectionName = mSectionName;
        this.mDate = mDate;
        this.mWebUrl = mWebUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getDate() {
        return mDate;
    }

    public String getWebUrl() {
        return mWebUrl;
    }
}
