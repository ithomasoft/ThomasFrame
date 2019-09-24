package com.thomas.base.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ClickUtils;

/**
 * @author Thomas
 * @describe 懒加载的基础Fragment
 * @date 2019/9/24
 * @updatelog
 * @since 1.0.0
 */
public abstract class LazyFragment extends Fragment implements IBaseView {

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;


    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected View mContentView;
    protected LayoutInflater mInflater;
    protected Activity mActivity;


    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onThomasClick(v);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commitAllowingStateLoss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        setRootLayout(bindLayout());
        return mContentView;
    }

    @SuppressLint("ResourceType")
    @Override
    public void setRootLayout(@LayoutRes int layoutId) {
        if (layoutId <= 0) {
            return;
        }
        mContentView = mInflater.inflate(layoutId, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        initData(bundle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(savedInstanceState, mContentView);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstVisible) {
            isFirstVisible = false;
            onFirstUserVisible();
        } else {
            onUserVisible();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (isFirstInvisible) {
            isFirstInvisible = false;
            onFirstUserInvisible();
        } else {
            onUserInvisible();
        }
    }

    @Override
    public void onDestroyView() {
        if (mContentView != null) {
            ((ViewGroup) mContentView.getParent()).removeView(mContentView);
        }
        super.onDestroyView();
        destroyViewAndThing();
    }

    public void applyThomasClickListener(View... views) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener);
        ClickUtils.applyScale(views);
    }

    public <T extends View> T findViewById(@IdRes int id) {
        if (mContentView == null) {
            throw new NullPointerException("ContentView is null.");
        }
        return mContentView.findViewById(id);
    }

    @Override
    public void doBusiness() {

    }

    protected abstract void onFirstUserVisible();//加载数据，开启动画/广播..

    protected abstract void onUserVisible();///开启动画/广播..

    private void onFirstUserInvisible() {
    }

    protected abstract void onUserInvisible();//暂停动画，暂停广播

    protected abstract void destroyViewAndThing();//销毁动作
}
