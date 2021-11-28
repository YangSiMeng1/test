package com.bawei.registermvvm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class SearchEditText extends AppCompatEditText {
    private Paint mPaint;
    private RectF rectF;

    public SearchEditText( Context context) {
        super(context);
        initPaint();
    }

    public SearchEditText( Context context,  AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public SearchEditText( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.WHITE);

        setBackground(null);
    }

    private float rx = 30.0f;
    private float ry = 30.0f;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF = new RectF(0+15,0+15,getMeasuredWidth()-15,getMeasuredHeight()-15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rectF,rx,ry,mPaint);

    }
}
