package us.shiroyama.githubviewer.domain.usecase

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Base UseCase class
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
abstract class UseCase<OBSERVABLE_TYPE, RESPONSE_TYPE : Any, PARAMS>(
    val subscribeSchedulerProvider: SubscribeSchedulerProvider,
    val observeSchedulerProvider: ObserveSchedulerProvider,
    private val compositeDisposable: CompositeDisposable
) {
    abstract fun buildObservable(params: PARAMS): OBSERVABLE_TYPE

    abstract fun execute(
        onNext: (RESPONSE_TYPE) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
        params: PARAMS
    )

    abstract fun execute(params: PARAMS): RESPONSE_TYPE

    protected fun addDisposable(disposable: Disposable): Boolean {
        return compositeDisposable.add(disposable)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

}