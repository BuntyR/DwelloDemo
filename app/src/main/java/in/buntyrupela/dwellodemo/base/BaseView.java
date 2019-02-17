package in.buntyrupela.dwellodemo.base;

public interface BaseView<T> {

    void setPresenter(T mPresenter);

    void showLoading();

    void hideLoading();
}
