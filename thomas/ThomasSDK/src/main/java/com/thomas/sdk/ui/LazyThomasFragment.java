package com.thomas.sdk.ui;

import com.thomas.core.ui.LazyFragment;
import com.thomas.sdk.helper.EventHelper;
import com.thomas.sdk.helper.HttpHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public abstract class LazyThomasFragment extends LazyFragment {
    private Unbinder unbinder;

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public void setRootLayout(int layoutId) {
        super.setRootLayout(layoutId);
        unbinder = ButterKnife.bind(this, mContentView);
    }


    @Override
    protected void onFirstUserVisible() {
        if (isNeedRegister()) {
            EventHelper.register(this);
        }
    }

    @Override
    protected void onUserVisible() {
        if (isNeedRegister()) {
            EventHelper.register(this);
        }

    }

    @Override
    protected void onUserInvisible() {
        if (isNeedRegister()) {
            EventHelper.unregister(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void destroyViewAndThing() {
        if (isNeedRegister()) {
            EventHelper.unregister(this);
        }
        HttpHelper.cancelRequest(getActivity());
    }
}