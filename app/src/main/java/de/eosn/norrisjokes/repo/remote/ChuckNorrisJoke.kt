package de.eosn.norrisjokes.repo.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ChuckNorrisJoke(

    @SerializedName("icon_url")
    @Expose
    var iconUrl: String,

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("value")
    @Expose
    var value: String

)