package com.thomas.andfun.home.adapter;

import androidx.annotation.NonNull;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.bean.KnowledgeBean;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/31
 * @updatelog
 * @since
 */
public class KnowledgeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;

    public KnowledgeAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_1, R.layout.item_knowledge_level_one);
        addItemType(TYPE_LEVEL_2, R.layout.item_knowledge_level_two);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_1:
                convertLevelOne(helper, item);
                break;
            case TYPE_LEVEL_2:
                convertLevelTwo(helper, item);
                break;
            default:
                break;
        }
    }


    private void convertLevelOne(BaseViewHolder helper, MultiItemEntity item) {
        KnowledgeBean knowledgeBean = (KnowledgeBean) item;
        ((SuperTextView) helper.itemView.findViewById(R.id.tv_name)).setLeftString(knowledgeBean.getName());

    }

    private void convertLevelTwo(BaseViewHolder helper, MultiItemEntity item) {
        KnowledgeBean.ChildrenBean childrenBean = (KnowledgeBean.ChildrenBean) item;
        ((SuperTextView) helper.itemView.findViewById(R.id.tv_name)).setCenterString(childrenBean.getName());

        helper.addOnClickListener(R.id.tv_name);
    }
}
