package us.shiroyama.githubviewer.domain.usecase

import io.reactivex.Scheduler

/**
 * A provider interface for [Scheduler]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
internal interface SchedulerProvider {
    fun provide(): Scheduler
}