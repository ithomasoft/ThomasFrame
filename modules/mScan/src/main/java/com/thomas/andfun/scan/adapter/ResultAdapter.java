package com.thomas.andfun.scan.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.scan.R;
import com.thomas.andfun.scan.entity.ScanResult;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2020/1/7
 * @updatelog
 * @since
 */
public class ResultAdapter extends BaseQuickAdapter<ScanResult, BaseViewHolder> {
    public ResultAdapter(@Nullable List<ScanResult> data) {
        super(R.layout.item_reslut, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ScanResult item) {
        helper.setText(R.id.tv_title, item.getResult());
        helper.setText(R.id.tv_date, item.getCreateDate());
        helper.setText(R.id.tv_author, item.getType());
    }
}
