package com.thomas.sdk.helper;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.thomas.core.utils.ImageUtils;
import com.thomas.core.utils.Utils;
import com.thomas.sdk.R;
import com.thomas.sdk.transform.BlurTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/21
 * @updatelog
 * @since
 */
public class ImageHelper {

    /**
     * 展示带模糊效果的图片
     *
     * @param iv
     * @param url
     */
    public static void showSimpleWithBlur(ImageView iv, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .priority(Priority.IMMEDIATE);

        Glide.with(Utils.getApp())
                .load(url)
                .apply(requestOptions)
                .transition(withCrossFade())
                .transform(new BlurTransformation())
                .into(iv);
    }

    /**
     * 展示带模糊效果的图片
     *
     * @param iv
     */
    public static void showSimpleWithBlur(ImageView iv, @DrawableRes final int resId) {
        iv.setImageBitmap(ImageUtils.fastBlur(ImageUtils.getBitmap(resId), 0.2f, 10));
    }


    /**
     * 加载显示一般的圆形小头像
     *
     * @param iv
     * @param url
     */
    public static void showSimpleCircleHead(ImageView iv, String url) {

        RequestOptions requestOptions = new RequestOptions()
                .circleCrop()
                .error(R.mipmap.ic_launcher_round)
                .priority(Priority.HIGH)
                .placeholder(R.mipmap.ic_launcher_round);
        Glide.with(Utils.getApp())
                .load(url)
                .apply(requestOptions)
                .transition(withCrossFade())
                .into(iv);

    }

    /**
     * 加载显示一般的圆形小头像
     *
     * @param iv
     */
    public static void showSimpleCircleHead(ImageView iv, @DrawableRes final int resId) {
        RequestOptions requestOptions = new RequestOptions()
                .circleCrop()
                .error(R.mipmap.ic_launcher_round)
                .priority(Priority.HIGH)
                .placeholder(R.mipmap.ic_launcher_round);
        Glide.with(Utils.getApp())
                .load(resId)
                .apply(requestOptions)
                .transition(withCrossFade())
                .into(iv);

    }


    /**
     * 加载显示一般的圆形小头像
     *
     * @param iv
     * @param url
     */
    public static void showSimpleSquare(ImageView iv, String url) {

        RequestOptions requestOptions = new RequestOptions()
                .error(R.mipmap.ic_launcher_round)
                .priority(Priority.HIGH)
                .placeholder(R.mipmap.ic_launcher_round);
        Glide.with(Utils.getApp())
                .load(url)
                .apply(requestOptions)
                .transform(new GranularRoundedCorners(8, 8, 8, 8))
                .transition(withCrossFade())
                .into(iv);

    }

    /**
     * 加载显示一般的圆形小头像
     *
     * @param iv
     */
    public static void showSimpleSquare(ImageView iv, @DrawableRes final int resId) {
        RequestOptions requestOptions = new RequestOptions()
                .error(R.mipmap.ic_launcher_round)
                .priority(Priority.HIGH)
                .placeholder(R.mipmap.ic_launcher_round);
        Glide.with(Utils.getApp())
                .load(resId)
                .apply(requestOptions)
                .transform(new GranularRoundedCorners(8, 8, 8, 8))
                .transition(withCrossFade())
                .into(iv);

    }

}
