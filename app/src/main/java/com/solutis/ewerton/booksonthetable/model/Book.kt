package com.solutis.ewerton.booksonthetable.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*

@Entity(tableName = "book", indices = [Index(value = ["name"], unique = true)])
data class Book(
    @ColumnInfo(name = "name")
    var name: String?  = "",
    @ColumnInfo(name = "author")
    var author: String?  = "",
    @ColumnInfo(name = "gender")
    var gender: String? = "",
    @ColumnInfo(name = "status")
    var status: BookStatus?  = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
):Parcelable{

    var statusByName: String?
        get() = status?.statusName
        set(value) {
            status = value?.let { BookStatus.getByStatusName(it) }
        }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()?.let { BookStatus.valueOf(it) },
        parcel.readLong()
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (name != other.name) return false
        if (author != other.author) return false
        if (gender != other.gender) return false
        if (status != other.status) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }

    fun getNextStatusName():String{
        if(status == BookStatus.READ || status == null){
            return ""
        }
        return BookStatus.values()[status!!.ordinal.plus(1)].statusName

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(author)
        parcel.writeString(gender)
        parcel.writeString(this.status?.name)
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
