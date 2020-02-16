package us.shiroyama.githubviewer.infrastructure.entity

import com.google.gson.annotations.SerializedName

data class OwnerEntity(
    @SerializedName("id") val id: Long,

    @SerializedName("login") val login: String,

    @SerializedName("avatar_url") val avatarUrl: String,

    @SerializedName("url") val url: String
)
