package com.thomas.andfun.user.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.bean.IntegralListBean;
import com.thomas.core.utils.TimeUtils;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class IntegralAdapter extends BaseQuickAdapter<IntegralListBean.DatasBean, BaseViewHolder> {
    public IntegralAdapter(@Nullable List<IntegralListBean.DatasBean> data) {
        super(R.layout.item_integral, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, IntegralListBean.DatasBean item) {
        helper.setText(R.id.tv_reason, item.getReason());
        helper.setText(R.id.tv_date, TimeUtils.millis2String(item.getDate()));
        helper.setText(R.id.tv_point, "+" + item.getCoinCount());
    }
}
