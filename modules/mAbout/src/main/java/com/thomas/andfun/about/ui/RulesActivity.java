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
import com.thomas.core.utils.SpanUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_RULES)
public class RulesActivity extends ThomasActivity {

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
        return R.layout.activity_rules;
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
        SpanUtils.with(tvContent).appendLine("为了感谢在本站比较活跃的用户，本站开发了签到积分功能。")
                .setLineHeight(2 * tvContent.getLineHeight(), SpanUtils.ALIGN_BOTTOM)
                .appendLine()
                .append("后续将根据积分，暂定每月赠送一些小礼品给大家，")
                .append("目前礼品还没想法，可能是图书、电子产品一类，排行榜待开发").setBold()
                .append("，到时候会在首页宣布获奖者。")
                .appendLine()
                .appendLine("获取积分").setBold().setFontProportion(1.2f)
                .setLineHeight(2 * tvContent.getLineHeight(), SpanUtils.ALIGN_BOTTOM)
                .appendLine()
                .appendLine("1. 每日登陆积分 ： 基数 + 登陆次数").setBold()
                .appendLine("只看登陆天数，不管中间间断没间断。")
                .setQuoteColor(Color.GREEN, 10, 10)
                .setFontProportion(0.8f).setBackgroundColor(Color.LTGRAY)
                .appendLine("基数默认为 10，每个基数对应最大值为 10 + 29，然后基数增加为 11 ，从 11 + 0 ~ 11 + 29 ，周而复始。")
                .setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("由于本功能上线后，灰度期间有一些 bug，所以部分用户的签到记录会出现同一天同一秒签到两次的情况，这个数据不准备去除了，就当是学艺不精，留下的悔恨的泪水吧。")
                .setQuoteColor(Color.GREEN, 10, 10)
                .setFontProportion(0.8f).setBackgroundColor(Color.LTGRAY)

                .appendLine()
                .appendLine("2. 分享文章").setBold()
                .appendLine("每天仅第一篇会增加积分，避免不必要分享。")
                .setQuoteColor(Color.GREEN, 10, 10)
                .setFontProportion(0.8f).setBackgroundColor(Color.LTGRAY)
                .appendLine("基数默认为 10，每个基数对应最大值为 10 + 29，然后基数增加为 11 ，从 11 + 0 ~ 11 + 29 ，周而复始。")
                .setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine()
                .appendLine("积分用途").setBold().setFontProportion(1.2f)
                .setLineHeight(2 * tvContent.getLineHeight(), SpanUtils.ALIGN_BOTTOM)
                .appendLine()
                .appendLine("♥领取本站合作礼包").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥投递文章免审核（一定积分开启）").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥投递文章直接进入首页（一定积分开启）").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥开放上传文件入口（1500积分开启）").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥开发文章功能（一定积分开启）").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)

                .appendLine()
                .appendLine("每天积分打满").setBold().setFontProportion(1.2f)
                .setLineHeight(2 * tvContent.getLineHeight(), SpanUtils.ALIGN_BOTTOM)
                .appendLine()
                .appendLine("♥登录一次").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥在广场 tab 分享一次文章（必须要登录，可以无视 1）").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)

                .appendLine()
                .appendLine("特殊奖励").setBold().setFontProportion(1.2f)
                .setLineHeight(2 * tvContent.getLineHeight(), SpanUtils.ALIGN_BOTTOM)
                .appendLine()
                .appendLine("♥报备严重 bug，例如 首页崩溃，严重影响用户体验 +66").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .appendLine("♥报备其他 bug，或者优化 +10").setLeadingMargin(((int) tvContent.getTextSize() * 2), 10)
                .append("注意 bug 一定要在")
                .append("这里").setUrl("https://github.com/hongyangAndroid/wanandroid/issues").append("上提出，确定是 bug 会添加 bug 标签，当然要留下用户名。")
                .appendLine("会在积分描述说明清楚为何加分。")

                .create();
    }

}
