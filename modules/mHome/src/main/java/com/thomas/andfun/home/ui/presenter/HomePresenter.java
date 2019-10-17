package com.thomas.andfun.home.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.ui.contract.HomeContract;
import com.thomas.andfun.home.ui.model.HomeModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class HomePresenter extends BaseMvpPresenter<HomeContract.Model, HomeContract.View> implements HomeContract.Presenter {

    @Override
    protected HomeContract.Model createModel() {
        return new HomeModel();
    }


}
