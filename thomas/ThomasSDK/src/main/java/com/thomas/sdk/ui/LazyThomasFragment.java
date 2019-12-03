package com.thomas.sdk.ui;

import android.view.View;

import androidx.annotation.NonNull;

import com.thomas.core.ui.LazyFragment;
import com.thomas.sdk.helper.EventHelper;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.helper.StatusHelper;

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
    protected StatusHelper.Holder holder;

    @Override
    public boolean isTransparent() {
        return true;
    }


    @Override
    public void setContentView() {
        super.setContentView();
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

    @Override
    public void onThomasClick(@NonNull View view) {

    }
}
