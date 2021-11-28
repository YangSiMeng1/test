package com.bawei.registermvvm.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.bawei.registermvvm.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimeView extends View {
    private Paint mPaint = null;
    private Paint textPaint = null;

    //圆形颜色
    private int mycolor = Color.BLUE;
    private int storkWidth = 5;

    //字体颜色
    private int textColor = Color.BLUE;
    private int textSize = 20;
    private String num = "6";
    private int count = 6;

    Timer timer;


    private int defaultwidth = 100;
    private int defaultheight = 100;


    private float rectAngle = 360;

    private int textX = 0;
    private int textY = 0;

    private int padding = 5;

    private TimeText timeText = null;

    public void setTimeView(TimeText timeText){
        this.timeText = timeText;
    }



    public TimeView(Context context) {
        super(context);
        initPaint();
    }


    public TimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        doTypeArray(context,attrs);

        initPaint();
    }

    /**
     * 处理自定义属性
     * @param context
     * @param attrs
     */
    private void doTypeArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
        mycolor = typedArray.getColor(R.styleable.TimeView_strokColor,mycolor);
        textSize = typedArray.getInteger(R.styleable.TimeView_textSize,textSize);
        storkWidth = typedArray.getInteger(R.styleable.TimeView_strokWidth,storkWidth);
        textColor = typedArray.getColor(R.styleable.TimeView_textColor,textColor);

        typedArray.recycle();
    }

    public TimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 前两位是mode 后30位是size
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);

        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthmode == MeasureSpec.AT_MOST&&heightmode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultwidth,defaultheight);
        }else if (widthmode == MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultwidth,heightsize);
        }else if (heightmode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthsize,defaultheight);
        }



    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        textX = getMeasuredWidth()/2;
        textY = getMeasuredHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(0+padding,0+padding,getMeasuredWidth()-padding,getMeasuredHeight()-padding);
        canvas.drawArc(rectF,0,rectAngle,false,mPaint);


        canvas.drawText(num,textX-padding/2,textY+padding,textPaint);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startDoAnimator();
    }

    /**
     * 倒计时动画
     */
    private void startDoAnimator(){


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count=count-1;
                num = String.valueOf(count);
                if (count==0){
                    timer.cancel();
                }
            }
        },0,1000);

        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(5*1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                rectAngle=360-animatedValue*360;
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    invalidate();
                }else{
                    postInvalidate();
                }
            }
        });
        valueAnimator.start();

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                timeText.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }



    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mycolor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(storkWidth);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

    }


    public  interface TimeText{
        void finish();
    }




}
