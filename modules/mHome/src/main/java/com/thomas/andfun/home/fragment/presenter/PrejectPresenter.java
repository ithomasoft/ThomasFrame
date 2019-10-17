package com.thomas.andfun.home.fragment.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.PrejectContract;
import com.thomas.andfun.home.fragment.model.PrejectModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class PrejectPresenter extends BaseMvpPresenter<PrejectContract.Model, PrejectContract.View> implements PrejectContract.Presenter {

    @Override
    protected PrejectContract.Model createModel() {
        return new PrejectModel();
    }


}
