package com.thomas.andfun.user.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.user.ui.contract.ShareContract;
import com.thomas.andfun.user.ui.model.ShareModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class SharePresenter extends BaseMvpPresenter<ShareContract.Model, ShareContract.View> implements ShareContract.Presenter {

    @Override
    protected ShareContract.Model createModel() {
        return new ShareModel();
    }


}
