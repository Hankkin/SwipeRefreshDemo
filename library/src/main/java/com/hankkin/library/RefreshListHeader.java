package com.hankkin.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;



public class RefreshListHeader extends LinearLayout {
    //根布局
    private LinearLayout mContainer;
    //下拉箭头图片
    private ImageView mArrowImageView;
    //下拉进度条
    private ProgressBar mProgressBar;
    //下拉文本
    private TextView mHintTextView;
    //状态值 0-正常 1-准备刷新  2-正在刷新
    private int mState = STATE_NORMAL;

    //抬起动画
    private Animation mRotateUpAnim;
    //下拉动画
    private Animation mRotateDownAnim;

    //下拉动画时间
    private final int ROTATE_ANIM_DURATION = 180;

    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    public RefreshListHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public RefreshListHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * 初始化组件
     * @param context
     */
    private void initView(Context context) {
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);

        mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
        mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
        mProgressBar = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
        //设置抬起动画
        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        //设置下拉动画
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }
    //设置状态
    public void setState(int state) {
        if (state == mState)
            return;
        //正在刷新时-显示进度条，隐藏下拉箭头
        if (state == STATE_REFRESHING) {
            mArrowImageView.clearAnimation();
            mArrowImageView.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            //显示下拉箭头，隐藏进度条
            mArrowImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        switch (state) {
        case STATE_NORMAL:
            if (mState == STATE_READY) {//准备刷新时开启动画
                mArrowImageView.startAnimation(mRotateDownAnim);
            }
            if (mState == STATE_REFRESHING) {//正在刷新时开启动画
                mArrowImageView.clearAnimation();
            }
            mHintTextView.setText(R.string.xlistview_header_hint_normal);
            break;
        case STATE_READY:
            if (mState != STATE_READY) {
                mArrowImageView.clearAnimation();
                mArrowImageView.startAnimation(mRotateUpAnim);
                mHintTextView.setText(R.string.xlistview_header_hint_ready);
            }
            break;
        case STATE_REFRESHING:
            mHintTextView.setText(R.string.xlistview_header_hint_loading);
            break;
        default:
        }

        mState = state;
    }

    /**
     * 设置头部高度
     * @param height
     */
    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    public int getVisiableHeight() {
        return mContainer.getHeight();
    }

}
