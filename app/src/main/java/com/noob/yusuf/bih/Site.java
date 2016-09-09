package com.noob.yusuf.bih;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yusuf on 02.08.2016.
 */
public class Site implements Parcelable{
    double lat, lng;
    String title, name;
    public Site (String lat, String lng, String title, String name){
        this.lat = Double.parseDouble(lat);
        this.lng = Double.parseDouble(lng);
        this.title = title;
        this.name = name;
    }

    public  Site(Parcel in){
        String[] info = new String[4];
        in.readStringArray( info);
        this.lat = Double.parseDouble(info[0]);
        this.lng = Double.parseDouble(info[1]);
        this.title = info[2];
        this.name = info[3];
    }

    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {lat +"", lng+"",title, name });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Site createFromParcel(Parcel in) {
            return new Site(in);
        }

        public Site[] newArray(int size) {
            return new Site[size];
        }
    };
}
