package net.gentledot.helloandroid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sang on 2017-05-19.
 */

public class SimpleData implements Parcelable{

    private int number;
    private String message;


    public SimpleData(int num, String msg){
        this.number = num;
        this.message = msg;
    }

    public SimpleData(Parcel in) {
        number = in.readInt();
        message = in.readString();
    }

    public static final Creator<SimpleData> CREATOR = new Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
