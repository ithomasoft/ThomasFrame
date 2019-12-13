package com.thomas.andfun.about.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.about.ui.contract.ProductsContract;
import com.thomas.andfun.about.ui.model.ProductsModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/13
 * @updatelog
 * @since
 */
public class ProductsPresenter extends BaseMvpPresenter<ProductsContract.Model, ProductsContract.View> implements ProductsContract.Presenter {

    @Override
    protected ProductsContract.Model createModel() {
        return new ProductsModel();
    }


}
