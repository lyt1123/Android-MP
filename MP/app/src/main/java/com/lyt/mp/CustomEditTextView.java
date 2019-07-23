package com.lyt.mp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CustomEditTextView extends LinearLayout {

    // 左侧图片
    Drawable leftimg;
    // title内容
    String title;

    String content;

    public CustomEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CustomEditTextView);
        leftimg = ta.getDrawable(R.styleable.CustomEditTextView_customleftimg);
        title = ta.getString(R.styleable.CustomEditTextView_customtext);

        initView();
        ta.recycle();
    }


    public void  initView(){
      View view = inflate(getContext(),R.layout.custom_edittext_view,this);
    }
}
