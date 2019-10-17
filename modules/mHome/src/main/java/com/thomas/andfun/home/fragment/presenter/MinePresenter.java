package com.thomas.andfun.home.fragment.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.MineContract;
import com.thomas.andfun.home.fragment.model.MineModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class MinePresenter extends BaseMvpPresenter<MineContract.Model, MineContract.View> implements MineContract.Presenter {

    @Override
    protected MineContract.Model createModel() {
        return new MineModel();
    }


}
