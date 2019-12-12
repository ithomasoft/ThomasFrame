package com.thomas.andfun.article.adapter;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.bean.HotKeyBean;

import java.util.List;
import java.util.Random;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/11
 * @updatelog
 * @since
 */
public class HotKeyAdapter extends BaseQuickAdapter<HotKeyBean, BaseViewHolder> {
    public HotKeyAdapter(@Nullable List<HotKeyBean> data) {
        super(R.layout.item_hot_key, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotKeyBean item) {
        AppCompatTextView tvKey = helper.getView(R.id.tv_key);
        tvKey.setText(item.getName());
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        tvKey.setTextColor(Color.rgb(r, g, b));
    }
}
