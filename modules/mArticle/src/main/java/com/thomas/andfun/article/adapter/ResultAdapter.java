package com.thomas.andfun.article.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.bean.ResultBean;
import com.thomas.sdk.helper.ImageHelper;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
public class ResultAdapter extends BaseQuickAdapter<ResultBean.DatasBean, BaseViewHolder> {
    public ResultAdapter(@Nullable List<ResultBean.DatasBean> data) {
        super(R.layout.item_square, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ResultBean.DatasBean item) {
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
