package com.thomas.andfun.user.fragment;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import androidx.appcompat.widget.AppCompatTextView;

import com.thomas.andfun.user.R;
import com.thomas.andfun.user.bean.IntegralBean;

import razerdp.basepopup.BasePopupWindow;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class MineIntegralCenterFragment extends BasePopupWindow {
    private AppCompatTextView tvTips, tvRank, tvPoint;

    public MineIntegralCenterFragment(Context context) {
        super(context);
        setPopupGravity(Gravity.CENTER);
        setClipChildren(false);
        setOutSideDismiss(false);
        setOutSideTouchable(false);

        tvTips = findViewById(R.id.tv_tips);
        tvRank = findViewById(R.id.tv_rank);
        tvPoint = findViewById(R.id.tv_point);

        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.fragment_mine_integral);
    }

    public void setData(IntegralBean data) {
        tvRank.setText(data.getRank() + "");
        tvPoint.setText(data.getCoinCount() + "");
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }

}
