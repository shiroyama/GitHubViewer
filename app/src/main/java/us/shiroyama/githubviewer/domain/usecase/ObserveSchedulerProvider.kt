package us.shiroyama.githubviewer.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * A [SchedulerProvider] meant for `observeOn`
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
class ObserveSchedulerProvider @Inject constructor() : SchedulerProvider {
    override fun provide(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}