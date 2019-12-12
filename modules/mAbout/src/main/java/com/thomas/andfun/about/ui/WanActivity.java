package com.thomas.andfun.about.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.andfun.about.R;
import com.thomas.andfun.about.R2;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ResourceUtils;
import com.thomas.core.utils.SpanUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_WEB)
public class WanActivity extends ThomasActivity {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.tv_content)
    AppCompatTextView tvContent;

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_wan;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
    }

    @Override
    public void doBusiness() {
        SpanUtils.with(tvContent)
                .append("本网站每天新增20~30篇优质文章，并加入到现有分类中，力求整理出一份优质而又详尽的知识体系，闲暇时间不妨上来学习下知识； 除此以外，并为大家提供平时开发过程中常用的工具以及常用的网址导航。")
                .setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)

                .appendLine("当然这只是我们目前的功能，未来我们将提供更多更加便捷的功能...")
                .setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine()
                .appendLine("如果您有任何好的建议:")
                .appendLine("♥关于网站排版").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥关于新增常用网址以及工具").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥未来你希望增加的功能等").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)

                .appendLine()
                .append("可以在").append("这里").setUrl("https://github.com/hongyangAndroid/xueandroid")
                .append("以issue的形式提出，我将及时跟进。")
                .create();
    }

}
