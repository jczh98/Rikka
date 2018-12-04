package top.rechinx.rikka.mvp.presenter.delivery

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import top.rechinx.rikka.mvp.view.OptionalView

/**
 * A deliverable that only emits to the view if attached, otherwise the event is ignored.
 */
class DeliverWithView<View, T>(private val view: Observable<OptionalView<View>>) : ObservableTransformer<T, Delivery<View, T>> {

    override fun apply(observable: Observable<T>): ObservableSource<Delivery<View, T>> {
        return observable
                .materialize()
                .filter { notification -> !notification.isOnComplete }
                .flatMap { notification ->
                    view.take(1)
                            .filter { it != null }
                            .map {
                                return@map if(Delivery.isValid(it, notification))
                                    Delivery<View, T>(it.view, notification)
                                else null
                            }
                }
    }

}