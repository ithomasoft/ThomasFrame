package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.R2;
import com.thomas.andfun.home.bean.ProjectCateBean;
import com.thomas.andfun.home.fragment.contract.ProjectContract;
import com.thomas.andfun.home.fragment.presenter.ProjectPresenter;
import com.thomas.core.utils.BarUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.LazyThomasMvpFragment;
import com.thomas.sdk.ui.ThomasMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ProjectFragment extends ThomasMvpFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R2.id.vp_content)
    ViewPager vpContent;

    private List<LazyThomasMvpFragment> fragments = new ArrayList<>();
    private List<CharSequence> titles = new ArrayList<>();

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void onStart() {
        super.onStart();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        holder = StatusHelper.getDefault().wrap(vpContent);
    }

    @Override
    public void doBusiness() {
        holder.showLoading();
        presenter.getProject();
    }

    @Override
    public void onFailed(String failed) {
        holder.withData(failed).withRetry(() -> presenter.getProject()).showLoadFailed();
    }

    @Override
    public void getCateSuccess(List<ProjectCateBean> datas) {
        holder.showLoadSuccess();
        tabLayout.setVisibility(View.VISIBLE);
        for (int i = 0; i < datas.size(); i++) {
            ProjectCateBean cateBean = datas.get(i);
            titles.add(HtmlCompat.fromHtml(cateBean.getName(), HtmlCompat.FROM_HTML_MODE_COMPACT));
            fragments.add(ProjectCateFragment.newInstance(cateBean.getId()));
        }
        vpContent.setAdapter(new FragmentPagerAdapter(getFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        tabLayout.setupWithViewPager(vpContent);
    }

    @Override
    public void getCateEmpty() {
        holder.showEmpty();
    }
}
