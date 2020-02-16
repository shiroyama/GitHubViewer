package us.shiroyama.githubviewer.domain.usecase

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable

/**
 * A [UseCase] for [Completable]
 *
 * @author Fumihiko Shiroyama
 */
abstract class CompletableUseCase<PARAMS>(
    subscribeSchedulerProvider: SubscribeSchedulerProvider,
    observeSchedulerProvider: ObserveSchedulerProvider,
    compositeDisposable: CompositeDisposable
) : UseCase<Completable, Throwable, PARAMS>(
    subscribeSchedulerProvider,
    observeSchedulerProvider,
    compositeDisposable
) {
    abstract override fun buildObservable(params: PARAMS): Completable

    override fun execute(
        onNext: (Throwable) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
        params: PARAMS
    ) {
        val completable = buildObservable(params)
        addDisposable(
            completable
                .subscribeOn(subscribeSchedulerProvider.provide())
                .observeOn(observeSchedulerProvider.provide())
                .subscribe(onComplete, onError)
        )
    }

    fun execute(
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
        params: PARAMS
    ) {
        execute(
            {},
            onError,
            onComplete,
            params
        )
    }

    override fun execute(params: PARAMS): Throwable {
        val completable = buildObservable(params)
        return completable.blockingGet()!!
    }
}