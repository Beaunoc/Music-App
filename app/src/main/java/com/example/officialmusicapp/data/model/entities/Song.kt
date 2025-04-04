package com.example.officialmusicapp.data.model.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey val id: String,
    val title: String,
    val album: String,
    val artist: String,
    val source: String,
    val image: String,
    val duration: Int,
    val favorite: String,
    val counter: Int,
    val replay: Int,
): Parcelable {
    // Constructor để đọc từ Parcel
    constructor(parcel: Parcel) : this(
        id = parcel.readString() ?: "",
        title = parcel.readString() ?: "",
        album = parcel.readString() ?: "",
        artist = parcel.readString() ?: "",
        source = parcel.readString() ?: "",
        image = parcel.readString() ?: "",
        duration = parcel.readInt(),
        favorite = parcel.readString() ?: "",
        counter = parcel.readInt(),
        replay = parcel.readInt()
    )

    // Ghi đối tượng vào Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(album)
        parcel.writeString(artist)
        parcel.writeString(source)
        parcel.writeString(image)
        parcel.writeInt(duration)
        parcel.writeString(favorite)
        parcel.writeInt(counter)
        parcel.writeInt(replay)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}