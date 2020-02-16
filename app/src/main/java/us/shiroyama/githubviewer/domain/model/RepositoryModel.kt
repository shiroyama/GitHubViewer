package us.shiroyama.githubviewer.domain.model

/**
 * A model representing GitHub's repository
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
data class RepositoryModel(
    val id: Long,
    val name: String,
    val fullName: String,
    val htmlUrl: String,
    val isPrivate: Boolean,
    val starCount: Long,
    val owner: OwnerModel
)