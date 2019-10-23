package com.thomas.res.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import com.thomas.res.R;

import java.util.UUID;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class ThomasTitleBar extends RelativeLayout implements View.OnClickListener {
    private RelativeLayout rlMain;                      // 主视图

    private AppCompatTextView tvLeft;                            // 左边TextView
    private AppCompatImageButton btnLeft;                        // 左边ImageButton
    private View viewCustomLeft;
    private AppCompatTextView tvRight;                           // 右边TextView
    private AppCompatImageButton btnRight;                       // 右边ImageButton
    private View viewCustomRight;
    private LinearLayoutCompat llMainCenter;
    private AppCompatTextView tvCenter;                          // 标题栏文字
    private AppCompatTextView tvCenterSub;                       // 副标题栏文字
    private RelativeLayout rlMainCenterSearch;          // 中间搜索框布局
    private AppCompatEditText etSearchHint;
    private AppCompatImageView ivSearch;
    private AppCompatImageView ivSearchAction;

    private View centerCustomView;                      // 中间自定义视图

    private int titleBarBackgroundColor;                          // 标题栏背景颜色
    private int titleBarHeight;                         // 标题栏高度

    private int leftType;                               // 左边视图类型
    private String leftText;                            // 左边TextView文字
    private int leftTextColor;                          // 左边TextView颜色
    private float leftTextSize;                         // 左边TextView文字大小
    private int leftDrawable;                           // 左边TextView drawableLeft资源
    private float leftDrawablePadding;                  // 左边TextView drawablePadding
    private int leftImageResource;                      // 左边图片资源
    private int leftCustomViewRes;                      // 左边自定义视图布局资源

    private int rightType;                              // 右边视图类型
    private String rightText;                           // 右边TextView文字
    private int rightTextColor;                         // 右边TextView颜色
    private float rightTextSize;                        // 右边TextView文字大小
    private int rightImageResource;                     // 右边图片资源
    private int rightCustomViewRes;                     // 右边自定义视图布局资源

    private int centerType;                             // 中间视图类型
    private String centerText;                          // 中间TextView文字
    private int centerTextColor;                        // 中间TextView字体颜色
    private float centerTextSize;                       // 中间TextView字体大小
    private boolean centerTextMarquee;                  // 中间TextView字体是否显示跑马灯效果
    private String centerSubText;                       // 中间subTextView文字
    private int centerSubTextColor;                     // 中间subTextView字体颜色
    private float centerSubTextSize;                    // 中间subTextView字体大小
    private boolean centerSearchEditable;                // 搜索框是否可输入
    private int centerSearchBgResource;                 // 搜索框背景图片
    private int centerSearchRightType;                  // 搜索框右边按钮类型  0: action 1: delete
    private int centerSearchRightRes;                  // 搜索框右边按钮图片
    private int centerCustomViewRes;                    // 中间自定义布局资源


    private OnTitleBarListener listener;
    private OnTitleBarDoubleClickListener doubleClickListener;

    private static final int TYPE_LEFT_NONE = 0;
    private static final int TYPE_LEFT_TEXTVIEW = 1;
    private static final int TYPE_LEFT_IMAGEBUTTON = 2;
    private static final int TYPE_LEFT_CUSTOM_VIEW = 3;
    private static final int TYPE_RIGHT_NONE = 0;
    private static final int TYPE_RIGHT_TEXTVIEW = 1;
    private static final int TYPE_RIGHT_IMAGEBUTTON = 2;
    private static final int TYPE_RIGHT_CUSTOM_VIEW = 3;
    private static final int TYPE_CENTER_NONE = 0;
    private static final int TYPE_CENTER_TEXTVIEW = 1;
    private static final int TYPE_CENTER_SEARCHVIEW = 2;
    private static final int TYPE_CENTER_CUSTOM_VIEW = 3;

    private static final int TYPE_CENTER_SEARCH_RIGHT_RES = 0;
    private static final int TYPE_CENTER_SEARCH_RIGHT_DELETE = 1;

    private final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    private int PADDING_5;
    private int PADDING_12;

    public ThomasTitleBar(Context context) {
        this(context, null);
    }

    public ThomasTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThomasTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttributes(context, attrs);
        initGlobalViews(context);
        initMainViews(context);
    }

    private void loadAttributes(Context context, AttributeSet attrs) {
        PADDING_5 = 10;
        PADDING_12 = 24;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ThomasTitleBar);
            titleBarBackgroundColor = array.getColor(R.styleable.ThomasTitleBar_titleBarBackgroundColor, ContextCompat.getColor(context, R.color.thomas_color_app_title_background));

            titleBarHeight = (int) array.getDimension(R.styleable.ThomasTitleBar_titleBarHeight, 56);

            leftType = array.getInt(R.styleable.ThomasTitleBar_leftType, TYPE_LEFT_NONE);
            if (leftType == TYPE_LEFT_TEXTVIEW) {
                leftText = array.getString(R.styleable.ThomasTitleBar_leftText);
                leftTextColor = array.getColor(R.styleable.ThomasTitleBar_leftTextColor, ContextCompat.getColor(context, R.color.thomas_color_app_title_text));
                leftTextSize = array.getDimension(R.styleable.ThomasTitleBar_leftTextSize, 16);
                leftDrawable = array.getResourceId(R.styleable.ThomasTitleBar_leftDrawable, 0);
                leftDrawablePadding = array.getDimension(R.styleable.ThomasTitleBar_leftDrawablePadding, 5);
            } else if (leftType == TYPE_LEFT_IMAGEBUTTON) {
                leftImageResource = array.getResourceId(R.styleable.ThomasTitleBar_leftImageResource, R.drawable.ic_arrow_back);
            } else if (leftType == TYPE_LEFT_CUSTOM_VIEW) {
                leftCustomViewRes = array.getResourceId(R.styleable.ThomasTitleBar_leftCustomView, 0);
            }

            rightType = array.getInt(R.styleable.ThomasTitleBar_rightType, TYPE_RIGHT_NONE);
            if (rightType == TYPE_RIGHT_TEXTVIEW) {
                rightText = array.getString(R.styleable.ThomasTitleBar_rightText);
                rightTextColor = array.getColor(R.styleable.ThomasTitleBar_rightTextColor, ContextCompat.getColor(context, R.color.thomas_color_app_title_text));
                rightTextSize = array.getDimension(R.styleable.ThomasTitleBar_rightTextSize, 16);
            } else if (rightType == TYPE_RIGHT_IMAGEBUTTON) {
                rightImageResource = array.getResourceId(R.styleable.ThomasTitleBar_rightImageResource, 0);
            } else if (rightType == TYPE_RIGHT_CUSTOM_VIEW) {
                rightCustomViewRes = array.getResourceId(R.styleable.ThomasTitleBar_rightCustomView, 0);
            }

            centerType = array.getInt(R.styleable.ThomasTitleBar_centerType, TYPE_CENTER_NONE);
            if (centerType == TYPE_CENTER_TEXTVIEW) {
                centerText = array.getString(R.styleable.ThomasTitleBar_centerText);
                centerTextColor = array.getColor(R.styleable.ThomasTitleBar_centerTextColor, ContextCompat.getColor(context, R.color.thomas_color_app_title_text));
                centerTextSize = array.getDimension(R.styleable.ThomasTitleBar_centerTextSize, 18);
                centerTextMarquee = array.getBoolean(R.styleable.ThomasTitleBar_centerTextMarquee, true);
                centerSubText = array.getString(R.styleable.ThomasTitleBar_centerSubText);
                centerSubTextColor = array.getColor(R.styleable.ThomasTitleBar_centerSubTextColor, ContextCompat.getColor(context, R.color.thomas_color_app_title_text));
                centerSubTextSize = array.getDimension(R.styleable.ThomasTitleBar_centerSubTextSize, 12);
            } else if (centerType == TYPE_CENTER_SEARCHVIEW) {
                centerSearchEditable = array.getBoolean(R.styleable.ThomasTitleBar_centerSearchEditable, true);
                centerSearchBgResource = array.getResourceId(R.styleable.ThomasTitleBar_centerSearchBackground, R.drawable.thomas_title_bar_search_background_shape);
                centerSearchRightType = array.getInt(R.styleable.ThomasTitleBar_centerSearchRightType, TYPE_CENTER_SEARCH_RIGHT_RES);
                centerSearchRightRes = array.getResourceId(R.styleable.ThomasTitleBar_centerSearchBackground, 0);
            } else if (centerType == TYPE_CENTER_CUSTOM_VIEW) {
                centerCustomViewRes = array.getResourceId(R.styleable.ThomasTitleBar_centerCustomView, 0);
            }

            array.recycle();
        }
    }


    private void initGlobalViews(Context context) {
        ViewGroup.LayoutParams globalParams = new ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        setLayoutParams(globalParams);

        // 构建主视图
        rlMain = new RelativeLayout(context);
        rlMain.setBackgroundColor(titleBarBackgroundColor);
        LayoutParams mainParams = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);

        mainParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        addView(rlMain, mainParams);
    }

    private void initMainViews(Context context) {
        if (leftType != TYPE_LEFT_NONE) {
            initMainLeftViews(context);
        }
        if (rightType != TYPE_RIGHT_NONE) {
            initMainRightViews(context);
        }
        if (centerType != TYPE_CENTER_NONE) {
            initMainCenterViews(context);
        }
    }

    /**
     * 初始化主视图左边部分
     * -- add: adaptive RTL
     *
     * @param context 上下文
     */
    private void initMainLeftViews(Context context) {
        LayoutParams leftInnerParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        leftInnerParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        leftInnerParams.addRule(RelativeLayout.CENTER_VERTICAL);

        if (leftType == TYPE_LEFT_TEXTVIEW) {
            // 初始化左边TextView
            tvLeft = new AppCompatTextView(context);
            tvLeft.setId(buildViewId());
            tvLeft.setText(leftText);
            tvLeft.setTextColor(leftTextColor);
            tvLeft.setTextSize(leftTextSize);
            tvLeft.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            tvLeft.setSingleLine(true);
            tvLeft.setOnClickListener(this);
            // 设置DrawableLeft及DrawablePadding
            if (leftDrawable != 0) {
                tvLeft.setCompoundDrawablePadding((int) leftDrawablePadding);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    tvLeft.setCompoundDrawablesRelativeWithIntrinsicBounds(leftDrawable, 0, 0, 0);
                } else {
                    tvLeft.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, 0, 0, 0);
                }
            }
            tvLeft.setPadding(16, 0, 16, 0);

            rlMain.addView(tvLeft, leftInnerParams);

        } else if (leftType == TYPE_LEFT_IMAGEBUTTON) {
            // 初始化左边ImageButton
            btnLeft = new AppCompatImageButton(context);
            btnLeft.setId(buildViewId());
            btnLeft.setBackgroundColor(Color.TRANSPARENT);
            btnLeft.setImageResource(leftImageResource);
            btnLeft.setPadding(PADDING_12, 0, PADDING_12, 0);
            btnLeft.setOnClickListener(this);

            rlMain.addView(btnLeft, leftInnerParams);

        } else if (leftType == TYPE_LEFT_CUSTOM_VIEW) {
            // 初始化自定义View
            viewCustomLeft = LayoutInflater.from(context).inflate(leftCustomViewRes, rlMain, false);
            if (viewCustomLeft.getId() == View.NO_ID) {
                viewCustomLeft.setId(buildViewId());
            }
            rlMain.addView(viewCustomLeft, leftInnerParams);
        }
    }

    /**
     * 初始化主视图右边部分
     * -- add: adaptive RTL
     *
     * @param context 上下文
     */
    private void initMainRightViews(Context context) {
        LayoutParams rightInnerParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        rightInnerParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        rightInnerParams.addRule(RelativeLayout.CENTER_VERTICAL);

        if (rightType == TYPE_RIGHT_TEXTVIEW) {
            // 初始化右边TextView
            tvRight = new AppCompatTextView(context);
            tvRight.setId(buildViewId());
            tvRight.setText(rightText);
            tvRight.setTextColor(rightTextColor);
            tvRight.setTextSize(rightTextSize);
            tvRight.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            tvRight.setSingleLine(true);
            tvRight.setPadding(PADDING_12, 0, PADDING_12, 0);
            tvRight.setOnClickListener(this);
            rlMain.addView(tvRight, rightInnerParams);

        } else if (rightType == TYPE_RIGHT_IMAGEBUTTON) {
            // 初始化右边ImageBtn
            btnRight = new AppCompatImageButton(context);
            btnRight.setId(buildViewId());
            btnRight.setImageResource(rightImageResource);
            btnRight.setBackgroundColor(Color.TRANSPARENT);
            btnRight.setScaleType(AppCompatImageView.ScaleType.CENTER_INSIDE);
            btnRight.setPadding(PADDING_12, 0, PADDING_12, 0);
            btnRight.setOnClickListener(this);
            rlMain.addView(btnRight, rightInnerParams);

        } else if (rightType == TYPE_RIGHT_CUSTOM_VIEW) {
            // 初始化自定义view
            viewCustomRight = LayoutInflater.from(context).inflate(rightCustomViewRes, rlMain, false);
            if (viewCustomRight.getId() == View.NO_ID) {
                viewCustomRight.setId(buildViewId());
            }
            rlMain.addView(viewCustomRight, rightInnerParams);
        }
    }

    /**
     * 初始化主视图中间部分
     *
     * @param context 上下文
     */
    private void initMainCenterViews(Context context) {
        if (centerType == TYPE_CENTER_TEXTVIEW) {
            // 初始化中间子布局
            llMainCenter = new LinearLayoutCompat(context);
            llMainCenter.setId(buildViewId());
            llMainCenter.setGravity(Gravity.CENTER);
            llMainCenter.setOrientation(LinearLayoutCompat.VERTICAL);
            llMainCenter.setOnClickListener(this);

            LayoutParams centerParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
            centerParams.setMargins(PADDING_12, 0, PADDING_12, 0);
            centerParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            rlMain.addView(llMainCenter, centerParams);

            // 初始化标题栏TextView
            tvCenter = new AppCompatTextView(context);
            tvCenter.setText(centerText);
            tvCenter.setTextColor(centerTextColor);
            tvCenter.setTextSize(centerTextSize);
            tvCenter.setGravity(Gravity.CENTER);
            tvCenter.setSingleLine(true);
            // 设置跑马灯效果
            tvCenter.setMaxWidth((int) (getScreenPixelSize(context)[0] * 3 / 5.0));
            if (centerTextMarquee) {
                tvCenter.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                tvCenter.setMarqueeRepeatLimit(-1);
                tvCenter.requestFocus();
                tvCenter.setSelected(true);
            }

            LinearLayoutCompat.LayoutParams centerTextParams = new LinearLayoutCompat.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            llMainCenter.addView(tvCenter, centerTextParams);

            // 初始化副标题栏
            tvCenterSub = new AppCompatTextView(context);
            tvCenterSub.setText(centerSubText);
            tvCenterSub.setTextColor(centerSubTextColor);
            tvCenterSub.setTextSize(centerSubTextSize);
            tvCenterSub.setGravity(Gravity.CENTER);
            tvCenterSub.setSingleLine(true);
            if (TextUtils.isEmpty(centerSubText)) {
                tvCenterSub.setVisibility(View.GONE);
            }

            LinearLayoutCompat.LayoutParams centerSubTextParams = new LinearLayoutCompat.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            llMainCenter.addView(tvCenterSub, centerSubTextParams);
        } else if (centerType == TYPE_CENTER_SEARCHVIEW) {
            // 初始化通用搜索框
            rlMainCenterSearch = new RelativeLayout(context);
            rlMainCenterSearch.setBackgroundResource(centerSearchBgResource);
            LayoutParams centerParams = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
            // 设置边距
            centerParams.topMargin = 7;
            centerParams.bottomMargin = 7;
            // 根据左边的布局类型来设置边距,布局依赖规则
            if (leftType == TYPE_LEFT_TEXTVIEW) {
                centerParams.addRule(RelativeLayout.END_OF, tvLeft.getId());
                centerParams.setMargins(PADDING_5, 0, 0, 0);
            } else if (leftType == TYPE_LEFT_IMAGEBUTTON) {
                centerParams.addRule(RelativeLayout.END_OF, btnLeft.getId());
                centerParams.setMargins(PADDING_5, 0, 0, 0);
            } else if (leftType == TYPE_LEFT_CUSTOM_VIEW) {
                centerParams.addRule(RelativeLayout.END_OF, viewCustomLeft.getId());
                centerParams.setMargins(PADDING_5, 0, 0, 0);
            } else {
                centerParams.setMargins(PADDING_12, 0, 0, 0);
            }
            // 根据右边的布局类型来设置边距,布局依赖规则
            if (rightType == TYPE_RIGHT_TEXTVIEW) {
                centerParams.addRule(RelativeLayout.START_OF, tvRight.getId());
                centerParams.setMargins(0, 0, PADDING_5, 0);
            } else if (rightType == TYPE_RIGHT_IMAGEBUTTON) {
                centerParams.addRule(RelativeLayout.START_OF, btnRight.getId());
                centerParams.setMargins(0, 0, PADDING_5, 0);
            } else if (rightType == TYPE_RIGHT_CUSTOM_VIEW) {
                centerParams.addRule(RelativeLayout.START_OF, viewCustomRight.getId());
                centerParams.setMargins(0, 0, PADDING_5, 0);
            } else {
                centerParams.setMargins(0, 0, PADDING_12, 0);
            }
            rlMain.addView(rlMainCenterSearch, centerParams);

            // 初始化搜索框搜索ImageView
            ivSearch = new AppCompatImageView(context);
            ivSearch.setId(buildViewId());
            ivSearch.setOnClickListener(this);
            int searchIconWidth = 15;
            LayoutParams searchParams = new LayoutParams(searchIconWidth, searchIconWidth);
            searchParams.addRule(RelativeLayout.CENTER_VERTICAL);
            searchParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            searchParams.setMargins(PADDING_12, 0, 0, 0);
            rlMainCenterSearch.addView(ivSearch, searchParams);
            ivSearch.setImageResource(R.drawable.ic_search);

            // 初始化搜索框语音ImageView
            ivSearchAction = new AppCompatImageView(context);
            ivSearchAction.setId(buildViewId());
            ivSearchAction.setOnClickListener(this);
            LayoutParams voiceParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            voiceParams.addRule(RelativeLayout.CENTER_VERTICAL);
            voiceParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            voiceParams.setMargins(0, 0, PADDING_12, 0);
            rlMainCenterSearch.addView(ivSearchAction, voiceParams);
            if (centerSearchRightType == TYPE_CENTER_SEARCH_RIGHT_RES) {
                ivSearchAction.setImageResource(centerSearchRightRes);
            } else {
                ivSearchAction.setVisibility(View.GONE);
            }

            // 初始化文字输入框
            etSearchHint = new AppCompatEditText(context);
            etSearchHint.setBackgroundColor(Color.TRANSPARENT);
            etSearchHint.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            etSearchHint.setTextColor(ContextCompat.getColor(context, R.color.thomas_color_normal_text));
            etSearchHint.setHintTextColor(ContextCompat.getColor(context, R.color.thomas_color_hint_text));
            etSearchHint.setTextSize(14);
            etSearchHint.setPadding(PADDING_5, 0, PADDING_5, 0);
            if (!centerSearchEditable) {
                etSearchHint.setCursorVisible(false);
                etSearchHint.clearFocus();
                etSearchHint.setFocusable(false);
                etSearchHint.setOnClickListener(this);
            }
            etSearchHint.setCursorVisible(false);
            etSearchHint.setSingleLine(true);
            etSearchHint.setEllipsize(TextUtils.TruncateAt.END);
            etSearchHint.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            etSearchHint.addTextChangedListener(centerSearchWatcher);
            etSearchHint.setOnFocusChangeListener(focusChangeListener);
            etSearchHint.setOnEditorActionListener(editorActionListener);
            etSearchHint.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    etSearchHint.setCursorVisible(true);
                }
            });
            LayoutParams searchHintParams = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
            searchHintParams.addRule(RelativeLayout.END_OF, ivSearch.getId());
            searchHintParams.addRule(RelativeLayout.START_OF, ivSearchAction.getId());
            searchHintParams.addRule(RelativeLayout.CENTER_VERTICAL);
            searchHintParams.setMargins(PADDING_5, 0, PADDING_5, 0);
            rlMainCenterSearch.addView(etSearchHint, searchHintParams);

        } else if (centerType == TYPE_CENTER_CUSTOM_VIEW) {
            // 初始化中间自定义布局
            centerCustomView = LayoutInflater.from(context).inflate(centerCustomViewRes, rlMain, false);
            if (centerCustomView.getId() == View.NO_ID) {
                centerCustomView.setId(buildViewId());
            }
            LayoutParams centerCustomParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
            centerCustomParams.setMargins(PADDING_12, 0, PADDING_12, 0);
            centerCustomParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            rlMain.addView(centerCustomView, centerCustomParams);
        }

    }


    /**
     * 计算View Id
     *
     * @return
     */
    private int buildViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        } else {
            return UUID.randomUUID().hashCode();
        }
    }


    private TextWatcher centerSearchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (centerSearchRightType == TYPE_CENTER_SEARCH_RIGHT_RES) {
                if (TextUtils.isEmpty(s)) {
                    ivSearchAction.setImageResource(centerSearchRightRes);
                } else {
                    ivSearchAction.setImageResource(R.drawable.ic_delete);
                }
            } else {
                if (TextUtils.isEmpty(s)) {
                    ivSearchAction.setVisibility(View.GONE);
                } else {
                    ivSearchAction.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    private OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            String input = etSearchHint.getText().toString();
            if (hasFocus && !TextUtils.isEmpty(input)) {
                ivSearchAction.setVisibility(View.VISIBLE);
            } else {
                ivSearchAction.setVisibility(View.GONE);
            }

        }
    };

    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (listener != null && actionId == EditorInfo.IME_ACTION_SEARCH) {
                listener.onClicked(v, ACTION_SEARCH_SUBMIT, etSearchHint.getText().toString());
            }
            return false;
        }
    };

    private long lastClickMillis = 0;     // 双击事件中，上次被点击时间

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }

        if (v.equals(llMainCenter) && doubleClickListener != null) {
            long currentClickMillis = System.currentTimeMillis();
            if (currentClickMillis - lastClickMillis < 500) {
                doubleClickListener.onClicked(v);
            }
            lastClickMillis = currentClickMillis;
        } else if (v.equals(tvLeft)) {
            listener.onClicked(v, ACTION_LEFT_TEXT, null);
        } else if (v.equals(btnLeft)) {
            listener.onClicked(v, ACTION_LEFT_BUTTON, null);
        } else if (v.equals(tvRight)) {
            listener.onClicked(v, ACTION_RIGHT_TEXT, null);
        } else if (v.equals(btnRight)) {
            listener.onClicked(v, ACTION_RIGHT_BUTTON, null);
        } else if (v.equals(etSearchHint) || v.equals(ivSearch)) {
            listener.onClicked(v, ACTION_SEARCH, null);
        } else if (v.equals(ivSearchAction)) {
            etSearchHint.setText("");
            if (centerSearchRightType == TYPE_CENTER_SEARCH_RIGHT_RES) {
                // 语音按钮被点击
                listener.onClicked(v, ACTION_SEARCH_RIGHT, null);
            } else {
                listener.onClicked(v, ACTION_SEARCH_DELETE, null);
            }
        } else if (v.equals(tvCenter)) {
            listener.onClicked(v, ACTION_CENTER_TEXT, null);
        }
    }

    /**
     * 设置点击事件监听
     *
     * @param listener
     */

    public void setListener(OnTitleBarListener listener) {
        this.listener = listener;
    }

    public void setDoubleClickListener(OnTitleBarDoubleClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }

    /**
     * 设置双击监听
     */


    public static final int ACTION_LEFT_TEXT = 1;        // 左边TextView被点击
    public static final int ACTION_LEFT_BUTTON = 2;      // 左边ImageBtn被点击
    public static final int ACTION_RIGHT_TEXT = 3;       // 右边TextView被点击
    public static final int ACTION_RIGHT_BUTTON = 4;     // 右边ImageBtn被点击
    public static final int ACTION_SEARCH = 5;           // 搜索框被点击,搜索框不可输入的状态下会被触发
    public static final int ACTION_SEARCH_SUBMIT = 6;    // 搜索框输入状态下,键盘提交触发
    public static final int ACTION_SEARCH_RIGHT = 7;     // 语音按钮被点击
    public static final int ACTION_SEARCH_DELETE = 8;    // 搜索删除按钮被点击
    public static final int ACTION_CENTER_TEXT = 9;     // 中间文字点击

    /**
     * 点击事件
     */
    public interface OnTitleBarListener {
        /**
         * @param view
         * @param action 对应ACTION_XXX, 如ACTION_LEFT_TEXT
         * @param extra  中间为搜索框时,如果可输入,点击键盘的搜索按钮,会返回输入关键词
         */
        void onClicked(View view, int action, String extra);
    }

    /**
     * 标题栏双击事件监听
     */
    public interface OnTitleBarDoubleClickListener {
        void onClicked(View view);
    }

    /**
     * 设置背景颜色
     *
     * @param color
     */
    @Override
    public void setBackgroundColor(int color) {
        rlMain.setBackgroundColor(color);
    }

    /**
     * 设置背景图片
     *
     * @param resource
     */
    @Override
    public void setBackgroundResource(int resource) {
        setBackgroundColor(Color.TRANSPARENT);
        super.setBackgroundResource(resource);
    }


    /**
     * 获取标题栏左边TextView，对应leftType = textView
     *
     * @return
     */
    public AppCompatTextView getLeftTextView() {
        return tvLeft;
    }

    /**
     * 获取标题栏左边ImageButton，对应leftType = imageButton
     *
     * @return
     */
    public AppCompatImageButton getLeftImageButton() {
        return btnLeft;
    }

    /**
     * 获取标题栏右边TextView，对应rightType = textView
     *
     * @return
     */
    public AppCompatTextView getRightTextView() {
        return tvRight;
    }

    /**
     * 获取标题栏右边ImageButton，对应rightType = imageButton
     *
     * @return
     */
    public AppCompatImageButton getRightImageButton() {
        return btnRight;
    }

    public LinearLayoutCompat getCenterLayout() {
        return llMainCenter;
    }

    /**
     * 获取标题栏中间TextView，对应centerType = textView
     *
     * @return
     */
    public AppCompatTextView getCenterTextView() {
        return tvCenter;
    }

    public AppCompatTextView getCenterSubTextView() {
        return tvCenterSub;
    }

    /**
     * 获取搜索框布局，对应centerType = searchView
     *
     * @return
     */
    public RelativeLayout getCenterSearchView() {
        return rlMainCenterSearch;
    }

    /**
     * 获取搜索框内部输入框，对应centerType = searchView
     *
     * @return
     */
    public AppCompatEditText getCenterSearchEditText() {
        return etSearchHint;
    }

    /**
     * 获取搜索框右边图标ImageView，对应centerType = searchView
     *
     * @return
     */
    public AppCompatImageView getCenterSearchRightImageView() {
        return ivSearchAction;
    }

    public AppCompatImageView getCenterSearchLeftImageView() {
        return ivSearch;
    }

    /**
     * 获取左边自定义布局
     *
     * @return
     */
    public View getLeftCustomView() {
        return viewCustomLeft;
    }

    /**
     * 获取右边自定义布局
     *
     * @return
     */
    public View getRightCustomView() {
        return viewCustomRight;
    }

    /**
     * 获取中间自定义布局视图
     *
     * @return
     */
    public View getCenterCustomView() {
        return centerCustomView;
    }

    /**
     * @param leftView
     */
    public void setLeftView(View leftView) {
        if (leftView.getId() == View.NO_ID) {
            leftView.setId(buildViewId());
        }
        LayoutParams leftInnerParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        leftInnerParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        leftInnerParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlMain.addView(leftView, leftInnerParams);
    }

    /**
     * @param centerView
     */
    public void setCenterView(View centerView) {
        if (centerView.getId() == View.NO_ID) {
            centerView.setId(buildViewId());
        }
        LayoutParams centerInnerParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        centerInnerParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        centerInnerParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlMain.addView(centerView, centerInnerParams);
    }

    /**
     * @param rightView
     */
    public void setRightView(View rightView) {
        if (rightView.getId() == View.NO_ID) {
            rightView.setId(buildViewId());
        }
        LayoutParams rightInnerParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
        rightInnerParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        rightInnerParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rlMain.addView(rightView, rightInnerParams);
    }

    /**
     * 设置搜索框右边图标
     *
     * @param res
     */
    public void setSearchRightImageResource(int res) {
        if (ivSearchAction != null) {
            ivSearchAction.setImageResource(res);
        }
    }

    /**
     * 获取SearchView输入结果
     */
    public String getSearchKey() {
        if (etSearchHint != null) {
            return etSearchHint.getText().toString();
        }
        return "";
    }


    /**
     * 获取屏幕大小
     *
     * @param context
     * @return
     */
    private int[] getScreenPixelSize(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }


    private DisplayMetrics getDisplayMetrics(Context context) {
        Activity activity;
        if (!(context instanceof Activity) && context instanceof ContextWrapper) {
            activity = (Activity) ((ContextWrapper) context).getBaseContext();
        } else {
            activity = (Activity) context;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

}