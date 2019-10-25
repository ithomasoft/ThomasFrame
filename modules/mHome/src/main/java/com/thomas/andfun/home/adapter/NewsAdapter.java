package com.thomas.andfun.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_type, "分类：" + item.getChapterName());
        helper.setText(R.id.tv_time, item.getNiceDate());
        ImageHelper.showSimpleSquare(helper.itemView.findViewById(R.id.iv_from), R.mipmap.ic_launcher_round);
    }
}
