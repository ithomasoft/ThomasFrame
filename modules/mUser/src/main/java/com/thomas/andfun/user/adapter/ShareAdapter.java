package com.thomas.andfun.user.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.bean.ShareListBean;
import com.thomas.sdk.helper.ImageHelper;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class ShareAdapter extends BaseQuickAdapter<ShareListBean.ShareArticlesBean.DatasBean, BaseViewHolder> {
    public ShareAdapter(@Nullable List<ShareListBean.ShareArticlesBean.DatasBean> data) {
        super(R.layout.item_share, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShareListBean.ShareArticlesBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_time, item.getNiceDate());
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
