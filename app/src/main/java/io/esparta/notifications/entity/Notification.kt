package io.esparta.notifications.entity

import android.os.Parcel
import android.os.Parcelable
import io.esparta.notifications.utils.createParcel

/**
 * Created by deividi on 26/12/15.
 */
public class Notification() : Parcelable {

    companion object {
        val CREATOR = createParcel { Notification(it) }
    }

    lateinit var title: String
    lateinit var message:String

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    public constructor(message:String?, url : String?) : this() {
        this.title = message ?: ""
        this.message = url ?: ""
    }

    protected constructor(parcelIn: Parcel) : this() {
        this.title = parcelIn.readString()
        this.message = parcelIn.readString()
    }



}


