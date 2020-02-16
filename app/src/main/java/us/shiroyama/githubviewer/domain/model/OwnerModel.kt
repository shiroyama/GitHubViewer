package us.shiroyama.githubviewer.domain.model

/**
 * A model representing GitHub's repository owner
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
data class OwnerModel(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val url: String
)