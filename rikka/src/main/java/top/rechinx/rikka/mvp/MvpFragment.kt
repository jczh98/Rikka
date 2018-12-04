package top.rechinx.rikka.mvp

import androidx.fragment.app.Fragment
import top.rechinx.rikka.mvp.presenter.Presenter
import top.rechinx.rikka.mvp.view.ViewWithPresenter
import android.os.Bundle
import top.rechinx.rikka.mvp.factory.PresenterFactory
import top.rechinx.rikka.mvp.factory.ReflectionPresenterFactory
import top.rechinx.rikka.mvp.view.PresenterLifecycleDelegate

abstract class MvpFragment<P : Presenter<*>> : Fragment(), ViewWithPresenter<P> {

    private val PRESENTER_STATE_KEY = "presenter_state"
    private val presenterDelegate = PresenterLifecycleDelegate(ReflectionPresenterFactory.fromViewClass<P>(javaClass))

    override fun getPresenterFactory(): PresenterFactory<P>? {
        return presenterDelegate.presenterFactory
    }

    override fun setPresenterFactory(presenterFactory: PresenterFactory<P>) {
        presenterDelegate.presenterFactory = presenterFactory
    }

    override fun getPresenter(): P {
        return presenterDelegate.presenter
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        if (bundle != null)
            presenterDelegate.onRestoreInstanceState(bundle.getBundle(PRESENTER_STATE_KEY))
        presenterDelegate.onResume(this)
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        bundle.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterDelegate.onDropView()
        presenterDelegate.onDestroy(!activity!!.isChangingConfigurations)
    }

}