package com.thomas.andfun.article.ui.presenter;

import android.text.TextUtils;

import com.thomas.andfun.article.ui.contract.IntegralRulesContract;
import com.thomas.andfun.article.ui.model.IntegralRulesModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
public class IntegralRulesPresenter extends BaseMvpPresenter<IntegralRulesContract.Model, IntegralRulesContract.View> implements IntegralRulesContract.Presenter {

    private String BLOG_CONTENT = "show";

    @Override
    protected IntegralRulesContract.Model createModel() {
        return new IntegralRulesModel();
    }


    @Override
    public void getContent() {
        getModel().getContent(new BaseThomasCallback<String>() {
            @Override
            protected void onSuccess(String succeed) {
                if (isViewAttached()) {
                    Document document = Jsoup.parse(succeed);
                    String content = document.getElementById(BLOG_CONTENT).html();
                    if (!TextUtils.isEmpty(content)) {
                        getView().getContentSuccess(content);
                    } else {
                        getView().onFailed("出现未知异常，请稍后重试");
                    }

                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onFailed(failed);
                }
            }
        });
    }
}
