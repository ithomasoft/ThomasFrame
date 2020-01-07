package com.thomas.andfun.scan.ui.contract;

import com.thomas.andfun.scan.entity.ScanResult;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;

import org.litepal.crud.callback.FindMultiCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/31
 * @updatelog
 * @since
 */
public interface HistoryContract {
    interface Model extends IBaseMvpModel {
        void getData(FindMultiCallback<ScanResult> callback);
    }

    interface View extends IBaseMvpView {
        void getDataSuccess(List<ScanResult> succeed);

        void getDataEmpty();
    }

    interface Presenter {
        void getData();
    }
}