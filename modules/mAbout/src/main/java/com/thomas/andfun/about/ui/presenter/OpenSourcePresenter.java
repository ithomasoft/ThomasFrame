package com.thomas.andfun.about.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.about.ui.contract.OpenSourceContract;
import com.thomas.andfun.about.ui.model.OpenSourceModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/13
 * @updatelog
 * @since
 */
public class OpenSourcePresenter extends BaseMvpPresenter<OpenSourceContract.Model, OpenSourceContract.View> implements OpenSourceContract.Presenter {

    @Override
    protected OpenSourceContract.Model createModel() {
        return new OpenSourceModel();
    }


}
