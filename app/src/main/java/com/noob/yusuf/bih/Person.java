package com.noob.yusuf.bih;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yusuf on 01.08.2016.
 */
class Person implements Parcelable {
    String name, contact, file;
    public Person( String inName, String inContact, String statment){
        name = inName;
        contact = inContact;
        file = statment;
    }

    public  Person(Parcel in){
        String[] info = new String[3];
        in.readStringArray( info);
        name = info[0];
        contact = info[1];
        file = info[2];
    }

    public int describeContents(){
        return 0;
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {name, contact, file});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

}
