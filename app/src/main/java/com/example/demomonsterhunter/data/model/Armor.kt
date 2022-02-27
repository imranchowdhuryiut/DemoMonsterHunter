package com.example.demomonsterhunter.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Imran Chowdhury on 2/26/2022.
 */


data class Armor(
    @SerializedName("assets")
    var assets: Assets? = null,
    @SerializedName("defense")
    var defense: Defense? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("slots")
    var slots: List<Slot>? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("rank")
    var rank: String? = null,
    @SerializedName("rarity")
    var rarity: Int? = null,
    @SerializedName("type")
    var type: String? = null
)

data class Assets(
    @SerializedName("imageFemale")
    var imageFemale: String? = null,
    @SerializedName("imageMale")
    var imageMale: String? = null
)

data class Defense(
    @SerializedName("augmented")
    var augmented: Int? = null,
    @SerializedName("base")
    var base: Int? = null,
    @SerializedName("max")
    var max: Int? = null
)

data class Slot(
    @SerializedName("rank")
    var rank: Int? = null
)
