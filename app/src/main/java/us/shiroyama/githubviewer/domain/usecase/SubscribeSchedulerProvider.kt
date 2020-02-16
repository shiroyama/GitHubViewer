package us.shiroyama.githubviewer.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * A [SchedulerProvider] meant for `subscribeOn`
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class SubscribeSchedulerProvider @Inject constructor() : SchedulerProvider {
    override fun provide(): Scheduler {
        return Schedulers.io()
    }
}