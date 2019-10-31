package com.thomas.andfun.home.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.bean.NewsListBean;
import com.thomas.sdk.helper.ImageHelper;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/25
 * @updatelog
 * @since
 */
public class NewsAdapter extends BaseQuickAdapter<NewsListBean.DatasBean, BaseViewHolder> {
    public NewsAdapter(@Nullable List<NewsListBean.DatasBean> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsListBean.DatasBean item) {
        helper.setText(R.id.tv_title, HtmlCompat.fromHtml(item.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT));

        if (TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_author, "分享人：" + item.getShareUser());
            helper.setText(R.id.tv_date, item.getNiceShareDate());
        } else {
            helper.setText(R.id.tv_author, "作者：" + item.getAuthor());
            helper.setText(R.id.tv_date, item.getNiceDate());
        }
        if (item.getLink().contains("jianshu")) {
            ImageHelper.showSimpleSquare(helper.itemView.findViewById(R.id.iv_from), R.mipmap.ic_jianshu);
        } else if (item.getLink().contains("juejin")) {
            ImageHelper.showSimpleSquare(helper.itemView.findViewById(R.id.iv_from), R.mipmap.ic_juejin);
        } else if (item.getLink().contains("csdn")) {
            ImageHelper.showSimpleSquare(helper.itemView.findViewById(R.id.iv_from), R.mipmap.ic_csdn);
        } else if (item.getLink().contains("weixin")) {
            ImageHelper.showSimpleSquare(helper.itemView.findViewById(R.id.iv_from), R.mipmap.ic_weixin);
        } else {
            ImageHelper.showSimpleSquare(helper.itemView.findViewById(R.id.iv_from), R.mipmap.ic_launcher_round);
        }

    }
}
