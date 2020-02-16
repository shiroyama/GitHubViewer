package us.shiroyama.githubviewer.infrastructure.entity.mapper

import us.shiroyama.githubviewer.domain.model.OwnerModel
import us.shiroyama.githubviewer.domain.model.RepositoryModel
import us.shiroyama.githubviewer.infrastructure.entity.RepositoryEntity
import javax.inject.Singleton

/**
 * A mapper from [RepositoryEntity] to [RepositoryModel]
 */
@Singleton
class RepositoryMapper {
    fun convert(repos: List<RepositoryEntity>): List<RepositoryModel> {
        return repos.map { convert(it) }
    }

    private fun convert(repositoryEntity: RepositoryEntity): RepositoryModel {
        val owner = repositoryEntity.owner
        val ownerModel = OwnerModel(
            owner.id,
            owner.login,
            owner.avatarUrl,
            owner.url
        )
        return RepositoryModel(
            repositoryEntity.id,
            repositoryEntity.name,
            repositoryEntity.fullName,
            repositoryEntity.htmlUrl,
            repositoryEntity.isPrivate,
            repositoryEntity.starCount,
            ownerModel
        )
    }
}