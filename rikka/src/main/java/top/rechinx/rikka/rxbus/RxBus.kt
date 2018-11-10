package top.rechinx.rikka.rxbus

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class RxBus {

    private var bus: Relay<Any> = PublishRelay.create<Any>().toSerialized()

    fun post(obj: Any) {
        bus.accept(obj)
    }

    fun <T> toObservable(eventType: Class<T>): Observable<T> {
        return bus.ofType(eventType)
    }

    fun hasObservables() = bus.hasObservers()

    fun <T> register(eventType: Class<T>, scheduler: Scheduler, onNext: Consumer<T>, onError: Consumer<Throwable>
                     , onComplete: Action, onSubscribe: Consumer<Disposable>): Disposable {
        return toObservable(eventType).observeOn(scheduler).subscribe(onNext, onError, onComplete, onSubscribe)
    }

    fun <T> register(eventType: Class<T>, onNext: Consumer<T>, onError: Consumer<Throwable>
                     , onComplete: Action, onSubscribe: Consumer<Disposable>): Disposable {
        return toObservable(eventType).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete, onSubscribe)
    }

    companion object {

        var instance : RxBus? = null
            get() {
                if(field == null) {
                    synchronized(RxBus::class) {
                        if(field == null) {
                            field = RxBus()
                        }
                    }
                }
                return field
            }
    }
}