package com.thomas.andfun.home.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.text.HtmlCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.bean.ProjectBean;
import com.thomas.sdk.helper.ImageHelper;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/8
 * @updatelog
 * @since
 */
public class ProjectAdapter extends BaseQuickAdapter<ProjectBean.DatasBean, BaseViewHolder> {
    public ProjectAdapter(@Nullable List<ProjectBean.DatasBean> data) {
        super(R.layout.item_project, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ProjectBean.DatasBean item) {
        helper.setText(R.id.tv_title, HtmlCompat.fromHtml(item.getTitle(), HtmlCompat.FROM_HTML_MODE_COMPACT));
        AppCompatImageView imageView = helper.itemView.findViewById(R.id.iv_image);
        ImageHelper.showSimple(imageView, item.getEnvelopePic());
        helper.setText(R.id.tv_desc, HtmlCompat.fromHtml(item.getDesc(), HtmlCompat.FROM_HTML_MODE_COMPACT));
    }
}
