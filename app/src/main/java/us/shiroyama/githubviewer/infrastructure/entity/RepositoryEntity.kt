package us.shiroyama.githubviewer.infrastructure.entity

import com.google.gson.annotations.SerializedName

/**
 * An entity representing GitHub's repository
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
data class RepositoryEntity(
    @SerializedName("id") val id: Long,

    @SerializedName("name") val name: String,

    @SerializedName("full_name") val fullName: String,

    @SerializedName("html_url") val htmlUrl: String,

    @SerializedName("private") val isPrivate: Boolean,

    @SerializedName("stargazers_count") val starCount: Long,

    @SerializedName("owner") val owner: OwnerEntity
)