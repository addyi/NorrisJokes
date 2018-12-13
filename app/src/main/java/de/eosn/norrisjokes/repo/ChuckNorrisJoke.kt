package de.eosn.norrisjokes.repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "chuck_norris_joke")
data class ChuckNorrisJoke(

    @SerializedName("icon_url")
    @Expose
    @ColumnInfo(name = "icon_url")
    var iconUrl: String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    @Expose
    var url: String,

    @ColumnInfo(name = "joke")
    @SerializedName("value")
    @Expose
    var joke: String

)