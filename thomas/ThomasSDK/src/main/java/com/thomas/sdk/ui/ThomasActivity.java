package com.thomas.sdk.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;

import com.alibaba.android.arouter.launcher.ARouter;
import com.thomas.core.ui.BaseActivity;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.sdk.helper.EventHelper;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.helper.StatusHelper;

import butterknife.ButterKnife;

/**
 * @author Thomas
 * @describe 公共的Activity
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public abstract class ThomasActivity extends BaseActivity {
    protected StatusHelper.Holder holder;

    @Override
    protected void onStart() {
        super.onStart();
        ARouter.getInstance().inject(this);
        if (isNeedRegister()) {
            EventHelper.register(this);
        }
    }

    @Override
    public void setContentView() {
        super.setContentView();
        ButterKnife.bind(this);
    }

    @Override
    protected boolean isNeedAdapt() {
        return false;
    }

    @Override
    protected int setAdaptScreen() {
        return 0;
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public void onThomasClick(@NonNull View view) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS
                );
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        if (isNeedRegister()) {
            EventHelper.unregister(this);
        }
        if (ActivityUtils.getTopActivity() != null) {
            //取消当前页面的所有网络请求。
            HttpHelper.cancelRequest(this);
        }
        super.onDestroy();

    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

}
