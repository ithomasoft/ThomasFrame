package com.thomas.andfun.user.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.bean.CollectionListBean;

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
        helper.setText(R.id.tv_title, item.getTitle());
    }
}
