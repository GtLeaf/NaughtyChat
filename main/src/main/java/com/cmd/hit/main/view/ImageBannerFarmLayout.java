package com.cmd.hit.main.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.cmd.hit.main.R;

import java.util.ArrayList;
import java.util.List;

public class ImageBannerFarmLayout extends FrameLayout implements ImageBannerViewGroup.ImageBarnnerViewGroupListener, ImageBannerViewGroup.ImageBarnnerListener {

    private ImageBannerViewGroup imageBannerViewGroup;
    private LinearLayout textLinearLayout;
    private LinearLayout linearLayout;
    private TextView tv;

    //上次点击的位置
    float lastX = 0f;
    float lastY = 0f;

    public static int WIDTH = 0;


    private List<String> arrayText = new ArrayList<String>();

    private FarmLayoutListener listener;

    public FarmLayoutListener getListener() {
        return listener;
    }

    public void setListener(FarmLayoutListener listener) {
        this.listener = listener;
    }

    public ImageBannerFarmLayout(@NonNull Context context) {
        this(context, null);
    }

    public ImageBannerFarmLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageBannerFarmLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBannerViewGroup();
        initTextLinearLayout();
        addTextShow("");
        initDotLinearLayout();
    }

    public void addBitmaps(List<Bitmap> list) {
        for (int i = 0; i < list.size(); i++) {
            Bitmap bitmap = list.get(i);
            addBitmapToImageBannerViewGroup(bitmap);
            addTextShow("第一条文本");
            addDotToLinearLayout();
        }
    }

    public void addBitmap(Bitmap bitmap, String text) {
        addBitmapToImageBannerViewGroup(bitmap);
        arrayText.add(text);
        addDotToLinearLayout();
    }


    //添加显示文本
    private void addTextShow(String str) {
        tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(15, 5, 5, 5);
        tv.setLayoutParams(lp);
        tv.setText(str);
        tv.setTextSize(36);
        tv.setTextColor(Color.WHITE);
        tv.setMaxLines(2);
        textLinearLayout.addView(tv);
    }

    private void addDotToLinearLayout() {
        ImageView iv = new ImageView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 5, 5, 5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        linearLayout.addView(iv);

    }

    private void addBitmapToImageBannerViewGroup(Bitmap bitmap) {
        ImageView iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setLayoutParams(new ViewGroup.LayoutParams(WIDTH, WIDTH));
        iv.setImageBitmap(bitmap);
        imageBannerViewGroup.addView(iv);
    }

    //初始化自定义的图片轮播功能核心类
    private void initImageBannerViewGroup() {
        imageBannerViewGroup = new ImageBannerViewGroup(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageBannerViewGroup.setLayoutParams(lp);
        imageBannerViewGroup.setBarnnerViewGroupListener(this);
        imageBannerViewGroup.setListener(this);
        addView(imageBannerViewGroup);
    }

    //初始化文本
    private void initTextLinearLayout() {
        textLinearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                240);
        textLinearLayout.setLayoutParams(lp);
        textLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        textLinearLayout.setGravity(Gravity.LEFT);
        addView(textLinearLayout);

        //重新设置布局
        FrameLayout.LayoutParams layoutParams = (LayoutParams) textLinearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        textLinearLayout.setLayoutParams(layoutParams);
    }

    //初始化底部原点
    private void initDotLinearLayout() {
        linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                40);
        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
        //linearLayout.setBackgroundColor(Color.RED);
        addView(linearLayout);

        //重新设置布局
        FrameLayout.LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(layoutParams);

        //设置透明度
        linearLayout.setAlpha(0.5f);
    }

    //选择文本
    @Override
    public void selectText(int index) {
        tv.setText(arrayText.get(index));
    }

    @Override
    public void selectImage(int index) {
        int count = linearLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            ImageView iv = (ImageView) linearLayout.getChildAt(i);
            if (i == index) {
                iv.setImageResource(R.drawable.dot_select);
            } else {
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void clickImageIndex(int pos) {
        listener.clickImageIndex(pos);
    }

    public interface FarmLayoutListener {
        void clickImageIndex(int pos);
    }

    /*
     * 为解决scroll滑动冲突
     * */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        float xDistance = 0f;
        float yDistance = 0f;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                xDistance += Math.abs(x - lastX);
                yDistance += Math.abs(y - lastY);
                lastX = x;
                lastY = y;
                if (xDistance >= yDistance) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }
}
