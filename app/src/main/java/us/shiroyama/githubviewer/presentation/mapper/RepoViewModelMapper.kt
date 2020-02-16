package us.shiroyama.githubviewer.presentation.mapper

import us.shiroyama.githubviewer.domain.model.RepositoryModel
import us.shiroyama.githubviewer.presentation.viewmodel.RepositoryViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Mapper for [RepositoryModel] -> [RepositoryViewModel]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
@Singleton
class RepoViewModelMapper @Inject constructor() {
    fun convert(model: RepositoryModel): RepositoryViewModel {
        return RepositoryViewModel(
            model.name,
            model.htmlUrl,
            model.owner.login,
            model.owner.avatarUrl,
            model.starCount
        )
    }

    fun convert(models: List<RepositoryModel>): List<RepositoryViewModel> {
        return models.map { convert(it) }
    }
}