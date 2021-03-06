package com.thomas.andfun.home.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.R2;
import com.thomas.andfun.home.fragment.KnowledgeFragment;
import com.thomas.andfun.home.fragment.MineFragment;
import com.thomas.andfun.home.fragment.NewsFragment;
import com.thomas.andfun.home.fragment.ProjectCateFragment;
import com.thomas.core.utils.BarUtils;
import com.thomas.sdk.ui.LazyThomasMvpFragment;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_HOME)
public class HomeActivity extends ThomasActivity {
    @BindView(R2.id.vp_content)
    ViewPager2 vpContent;
    @BindView(R2.id.nav_view)
    BottomNavigationView navView;

    private List<LazyThomasMvpFragment> fragments = new ArrayList<>();

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initStatusBar() {
        super.initStatusBar();
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(mActivity, android.R.color.black), false);
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        fragments.add(new NewsFragment());
        fragments.add(new KnowledgeFragment());
        fragments.add(new ProjectCateFragment());
        fragments.add(new MineFragment());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        vpContent.setAdapter(new FragmentStateAdapter(mActivity) {
            @Override
            public int getItemCount() {
                return fragments.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }
        });
        vpContent.setUserInputEnabled(false);
        vpContent.setOffscreenPageLimit(fragments.size());
        navView.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.navigation_news) {
                vpContent.setCurrentItem(0, false);
            }

            if (menuItem.getItemId() == R.id.navigation_knowledge) {
                vpContent.setCurrentItem(1, false);
            }

            if (menuItem.getItemId() == R.id.navigation_project) {
                vpContent.setCurrentItem(2, false);
            }

            if (menuItem.getItemId() == R.id.navigation_mine) {
                vpContent.setCurrentItem(3, false);
            }
            return true;
        });
    }

    @Override
    public void doBusiness() {

    }

}
