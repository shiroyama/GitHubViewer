package us.shiroyama.githubviewer.presentation.viewmodel

/**
 * A view model for GitHub repository
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
data class RepositoryViewModel(
    val repositoryName: String,
    val htmlUrl: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val starCount: Long
)