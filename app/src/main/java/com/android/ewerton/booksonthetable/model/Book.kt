package com.android.ewerton.booksonthetable.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "book", indices = [Index(value = ["name"], unique = true), Index(value = ["UUID"], unique = true)])
data class Book(
    @ColumnInfo(name = "name")
    var name: String? = "",
    @ColumnInfo(name = "author")
    var author: String? = "",
    @ColumnInfo(name = "gender")
    var gender: String? = "",
    @ColumnInfo(name = "status")
    var status: BookStatus? = null,
    @ColumnInfo(name = "UUID")
    var UUID: String? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
) : Parcelable {

    var statusByName: String?
        get() = status?.statusName
        set(value) {
            status = value?.let { BookStatus.getByStatusName(it) }
        }

    fun getNextStatusName(): String {
        if (status == BookStatus.READ || status == null) {
            return ""
        }
        return BookStatus.values()[status!!.ordinal.plus(1)].statusName
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()?.let { BookStatus.valueOf(it) },
        parcel.readString(),
        parcel.readLong(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(author)
        parcel.writeString(gender)
        parcel.writeString(this.status?.name)
        parcel.writeString(UUID)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
