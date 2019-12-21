package com.thomas.sdk.ui;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.core.mvp.IBaseMvpView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public abstract class ThomasMvpFragment<P extends BaseMvpPresenter> extends ThomasFragment implements IBaseMvpView {
    protected P presenter;


    @Override
    public void setContentView() {
        super.setContentView();
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定，避免内存泄露
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    protected abstract P createPresenter();
}
