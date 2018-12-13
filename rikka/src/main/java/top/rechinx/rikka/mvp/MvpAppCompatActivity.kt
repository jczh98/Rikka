package top.rechinx.rikka.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import top.rechinx.rikka.mvp.factory.PresenterFactory
import top.rechinx.rikka.mvp.factory.ReflectionPresenterFactory
import top.rechinx.rikka.mvp.presenter.Presenter
import top.rechinx.rikka.mvp.view.PresenterLifecycleDelegate
import top.rechinx.rikka.mvp.view.ViewWithPresenter

abstract class MvpAppCompatActivity<P : Presenter<*>> : BaseAppCompatActivity(), ViewWithPresenter<P> {

    private val PRESENTER_STATE_KEY = "presenter_state"

    private val presenterDelegate = PresenterLifecycleDelegate(ReflectionPresenterFactory.fromViewClass<P>(javaClass))

    override fun getPresenterFactory(): PresenterFactory<P> {
        return presenterDelegate.presenterFactory!!
    }

    override fun setPresenterFactory(presenterFactory: PresenterFactory<P>) {
        presenterDelegate.presenterFactory = presenterFactory
    }

    override fun getPresenter(): P {
        return presenterDelegate.presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null)
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_STATE_KEY))
        presenterDelegate.onResume(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterDelegate.onDropView()
        presenterDelegate.onDestroy(!isChangingConfigurations)
    }
}