package com.example.mariohany.tourguide;

/**
 * Created by Mario on 6/13/2017.
 */

public class Place {
    private String mPlaceName;
    private String mPlaceDes;
    private int mImageResourceId;
    private String mLocation;

    public Place(String mPlaceName, String mPlaceDes, int mImageResourceId, String mLocation) {
        this.mPlaceName = mPlaceName;
        this.mPlaceDes = mPlaceDes;
        this.mImageResourceId = mImageResourceId;
        this.mLocation = mLocation;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public String getPlaceDes() {
        return mPlaceDes;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public String getLocation() {
        return mLocation;
    }
}
