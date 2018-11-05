package top.rechinx.rikka.mvp

import androidx.appcompat.app.AppCompatActivity
import top.rechinx.rikka.mvp.presenter.Presenter
import top.rechinx.rikka.mvp.view.ViewWithPresenter
import top.rechinx.rikka.mvp.factory.ReflectionPresenterFactory
import top.rechinx.rikka.mvp.view.PresenterLifecycleDelegate
import top.rechinx.rikka.mvp.factory.PresenterFactory
import android.os.Bundle

abstract class MvpAppCompatActivity<P : Presenter<*>> : AppCompatActivity(), ViewWithPresenter<P> {

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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState())
    }

    override fun onResume() {
        super.onResume()
        presenterDelegate.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        presenterDelegate.onDropView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterDelegate.onDestroy(!isChangingConfigurations)
    }
}