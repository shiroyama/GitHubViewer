package us.shiroyama.githubviewer.domain.usecase

import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

/**
 * A [UseCase] for [Maybe]
 *
 * @author Fumihiko Shiroyama (fu.shiroyama@gmail.com)
 */
abstract class MaybeUseCase<RESPONSE_TYPE : Any, PARAMS>(
    subscribeSchedulerProvider: SubscribeSchedulerProvider,
    observeSchedulerProvider: ObserveSchedulerProvider,
    compositeDisposable: CompositeDisposable
) : UseCase<Maybe<RESPONSE_TYPE>, RESPONSE_TYPE, PARAMS>(
    subscribeSchedulerProvider,
    observeSchedulerProvider,
    compositeDisposable
) {
    abstract override fun buildObservable(params: PARAMS): Maybe<RESPONSE_TYPE>

    override fun execute(
        onNext: (RESPONSE_TYPE) -> Unit,
        onError: (Throwable) -> Unit,
        onComplete: () -> Unit,
        params: PARAMS
    ) {
        val maybe = buildObservable(params)
        addDisposable(
            maybe
                .subscribeOn(subscribeSchedulerProvider.provide())
                .observeOn(observeSchedulerProvider.provide())
                .subscribeBy(
                    onSuccess = onNext,
                    onError = onError,
                    onComplete = onComplete
                )
        )
    }

    override fun execute(params: PARAMS): RESPONSE_TYPE {
        val maybe = buildObservable(params)
        return maybe.blockingGet()
    }
}