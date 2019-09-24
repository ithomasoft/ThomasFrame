package com.thomas.base.mvp;

import java.lang.ref.WeakReference;

/**
 * @author Thomas
 * @describe 基础mvp架构的presenter
 * @date 2019/9/24
 * @updatelog
 * @since 1.0.0
 */
public abstract class BaseMvpPresenter<M extends IBaseMvpModel, V extends IBaseMvpView> {
    private V view;
    private M model;
    private WeakReference<V> weakReference;

    /**
     * 绑定View
     */
    public void attachView(V view) {
        if (view != null) {
            weakReference = new WeakReference<>(view);
            this.view = weakReference.get();
        }
        if (model == null) {
            model = createModel();
        }
    }

    /**
     * 解绑View
     */
    public void detachView() {
        this.model = null;
        if (isViewAttached()) {
            weakReference.clear();
            weakReference = null;
            view = null;
        }
    }

    /**
     * 是否与View建立连接
     */
    protected boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    protected V getView() {
        if (view == null) {
            throw new IllegalStateException("view not attached");
        } else {
            return view;
        }
    }

    protected M getModel() {
        return model;
    }

    /**
     * 通过该方法创建Module
     */
    protected abstract M createModel();

}
