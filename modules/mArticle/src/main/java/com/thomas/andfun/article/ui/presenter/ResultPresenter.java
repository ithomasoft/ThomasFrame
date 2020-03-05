package com.thomas.andfun.article.ui.presenter;

import com.thomas.andfun.article.bean.ResultBean;
import com.thomas.andfun.article.ui.contract.ResultContract;
import com.thomas.andfun.article.ui.model.ResultModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
public class ResultPresenter extends BaseMvpPresenter<ResultContract.Model, ResultContract.View> implements ResultContract.Presenter {

    @Override
    protected ResultContract.Model createModel() {
        return new ResultModel();
    }


    @Override
    public void getData(int pageNo, String key) {
        getModel().getData(pageNo, key, new BaseThomasCallback<ResultBean>() {
            @Override
            protected void onSuccess(ResultBean succeed) {
                if (isViewAttached()) {
                    getView().onMoreData(succeed.getCurPage() < succeed.getPageCount());

                    if (succeed.getDatas() != null && succeed.getDatas().size() > 0) {
                        getView().getDataSuccess(succeed.getDatas());
                    } else {
                        getView().getDataEmpty();
                    }


                }

            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onFailed(0, failed);
                }
            }
        });
    }
}
