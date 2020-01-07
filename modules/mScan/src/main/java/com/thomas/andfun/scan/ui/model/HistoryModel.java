package com.thomas.andfun.scan.ui.model;

import com.thomas.andfun.scan.entity.ScanResult;
import com.thomas.andfun.scan.ui.contract.HistoryContract;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/31
 * @updatelog
 * @since
 */
public class HistoryModel implements HistoryContract.Model {

    @Override
    public void getData(FindMultiCallback<ScanResult> callback) {
        LitePal.findAllAsync(ScanResult.class).listen(callback);
    }
}
