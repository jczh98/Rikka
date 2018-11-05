package top.rechinx.rikka.mvp.factory;

import top.rechinx.rikka.mvp.presenter.Presenter;

public interface PresenterFactory<P extends Presenter> {
    P createPresenter();
}
