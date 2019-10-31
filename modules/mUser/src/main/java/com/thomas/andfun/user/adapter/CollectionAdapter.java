package com.thomas.andfun.user.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.bean.CollectionListBean;
import com.thomas.sdk.helper.ImageHelper;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class CollectionAdapter extends BaseQuickAdapter<CollectionListBean.DatasBean, BaseViewHolder> {
    public CollectionAdapter(@Nullable List<CollectionListBean.DatasBean> data) {
        super(R.layout.item_collection, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CollectionListBean.DatasBean item) {
        helper.setText(R.id.tv_title, HtmlCompat.fromHtml(item.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT));
        helper.setText(R.id.tv_date, item.getNiceDate());
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
