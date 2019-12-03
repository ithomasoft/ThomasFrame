package com.thomas.sdk.ui;

import android.os.Bundle;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.core.mvp.IBaseMvpView;

/**
 * @author Thomas
 * @describe mvp模式的公共Activity
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public abstract class ThomasMvpActivity<P extends BaseMvpPresenter> extends ThomasActivity implements IBaseMvpView {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定，避免内存泄露
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    protected abstract P createPresenter();
}
