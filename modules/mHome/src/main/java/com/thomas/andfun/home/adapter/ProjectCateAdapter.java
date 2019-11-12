package com.thomas.andfun.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.bean.ProjectCateBean;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/12
 * @updatelog
 * @since
 */
public class ProjectCateAdapter extends BaseQuickAdapter<ProjectCateBean, BaseViewHolder> {
    public ProjectCateAdapter(@Nullable List<ProjectCateBean> data) {
        super(R.layout.item_project_cate, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProjectCateBean item) {
        SuperTextView tvCate = helper.itemView.findViewById(R.id.tv_project_cate);
        tvCate.setCenterString(HtmlCompat.fromHtml(item.getName(), HtmlCompat.FROM_HTML_MODE_COMPACT));
        helper.addOnClickListener(R.id.tv_project_cate);
    }
}
