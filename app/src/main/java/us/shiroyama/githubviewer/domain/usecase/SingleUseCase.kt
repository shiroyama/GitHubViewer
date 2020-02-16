package us.shiroyama.githubviewer.domain.usecase

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * A [UseCase] for [Single]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
abstract class SingleUseCase<RESPONSE_TYPE : Any, PARAMS>(
    subscribeSchedulerProvider: SubscribeSchedulerProvider,
    observeSchedulerProvider: ObserveSchedulerProvider,
    compositeDisposable: CompositeDisposable
) : UseCase<Single<RESPONSE_TYPE>, RESPONSE_TYPE, PARAMS>(
    subscribeSchedulerProvider,
    observeSchedulerProvider,
    compositeDisposable
) {
    abstract override fun buildObservable(params: PARAMS): Single<RESPONSE_TYPE>

    override fun execute(
        onNext: (RESPONSE_TYPE) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
        params: PARAMS
    ) {
        val single = buildObservable(params)
        addDisposable(
            single
                .subscribeOn(subscribeSchedulerProvider.provide())
                .observeOn(observeSchedulerProvider.provide())
                .subscribeBy(
                    onSuccess = onNext,
                    onError = onError
                )
        )
    }

    fun execute(
        onNext: (RESPONSE_TYPE) -> Unit,
        onError: (Throwable) -> Unit,
        params: PARAMS
    ) {
        execute(onNext, onError, {}, params)
    }

    override fun execute(params: PARAMS): RESPONSE_TYPE {
        val single = buildObservable(params)
        return single.blockingGet()
    }
}