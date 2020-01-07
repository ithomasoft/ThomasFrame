package com.thomas.andfun.scan.helper;


import com.thomas.andfun.scan.entity.ScanResult;
import com.thomas.core.utils.TimeUtils;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2020/1/7
 * @updatelog
 * @since
 */
public class ResultHelper {
    public static void add(String result) {
        ScanResult scanResult = new ScanResult();
        scanResult.setResult(result);
        scanResult.setType(getType(result));
        scanResult.setCreateDate(TimeUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        scanResult.saveOrUpdate();
    }

    public static void delete(String result) {
        LitePal.deleteAll(ScanResult.class, "result like ?", result);
    }


    private static String getType(String result) {
        return "默认";
    }


}
