package com.thomas.sdk.ui;

import android.view.View;

import androidx.annotation.NonNull;

import com.thomas.core.ui.BaseFragment;
import com.thomas.sdk.helper.EventHelper;
import com.thomas.sdk.helper.StatusHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Thomas
 * @describe 公共的Fragment
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public abstract class ThomasFragment extends BaseFragment {

    private Unbinder unbinder;
    protected StatusHelper.Holder holder;

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public void setContentView() {
        super.setContentView();
        unbinder = ButterKnife.bind(this, mContentView);
    }

    @Override
    public void onThomasClick(@NonNull View view) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (isNeedRegister()) {
            EventHelper.register(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (isNeedRegister()) {
            EventHelper.unregister(this);
        }
        super.onDestroyView();
        unbinder.unbind();
    }
}
