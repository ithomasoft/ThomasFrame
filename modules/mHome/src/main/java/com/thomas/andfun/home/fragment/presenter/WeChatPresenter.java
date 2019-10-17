package com.thomas.andfun.home.fragment.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.WeChatContract;
import com.thomas.andfun.home.fragment.model.WeChatModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class WeChatPresenter extends BaseMvpPresenter<WeChatContract.Model, WeChatContract.View> implements WeChatContract.Presenter {

    @Override
    protected WeChatContract.Model createModel() {
        return new WeChatModel();
    }


}
