package com.thomas.andfun.scan.ui.presenter;

import com.thomas.andfun.scan.entity.ScanResult;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.scan.ui.contract.HistoryContract;
import com.thomas.andfun.scan.ui.model.HistoryModel;

import org.litepal.crud.callback.FindMultiCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/31
 * @updatelog
 * @since
 */
public class HistoryPresenter extends BaseMvpPresenter<HistoryContract.Model, HistoryContract.View> implements HistoryContract.Presenter {

    @Override
    protected HistoryContract.Model createModel() {
        return new HistoryModel();
    }


    @Override
    public void getData() {
        getModel().getData(list -> {
            if (isViewAttached()) {
                if (list != null && list.size() > 0) {
                    getView().getDataSuccess(list);
                } else {
                    getView().getDataEmpty();
                }
            }
        });
    }
}
