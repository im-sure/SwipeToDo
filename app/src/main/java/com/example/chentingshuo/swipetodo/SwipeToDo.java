package com.example.chentingshuo.swipetodo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by CloudChen on 16/5/27.
 */
public class SwipeToDo extends View {

    private int mX;
    private int mY;
    private int mWidth;
    private int mHeight;
    private boolean mIsOnTouch;
    private Paint mPaint;

    private int mControlCircleRadius;
    private int mCenterCircleRadius;
    private int mLeftAndRightCirclesRadius;

    private OnSwipeToDo mOnSwipeToDo;

    public SwipeToDo(Context context) {
        super(context);
        init();
    }

    public SwipeToDo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeToDo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusable(true);
        mIsOnTouch = false;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mX = 0;
        mY = 0;
        mControlCircleRadius = 0;
        mCenterCircleRadius = 0;
        mLeftAndRightCirclesRadius = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        mX = (int) event.getX();
        mY = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (isTouchCenterCircle()) {
                    mIsOnTouch = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (mIsOnTouch) {
                    if (isTouchLeftCircle()) {
                        mOnSwipeToDo.onSwipeToLeft(this, event);
                    } else if (isTouchRightCircle()) {
                        mOnSwipeToDo.onSwipeToRight(this, event);
                    }
                }
                mIsOnTouch = false;
                mX = mWidth / 2;
                mY = mHeight / 2;
                break;

        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getWidth();
        mHeight = getHeight();
        mControlCircleRadius = Math.min(mWidth, mHeight) / 16;
        mCenterCircleRadius = Math.min(mWidth, mHeight) / 8;
        mLeftAndRightCirclesRadius = Math.min(mWidth, mHeight) / 4;
        if (!mIsOnTouch) {
            mPaint.setColor(getResources().getColor(R.color.white_50alpha));
            canvas.drawCircle(mWidth / 2, mHeight / 2, mCenterCircleRadius, mPaint);
            mPaint.setColor(Color.alpha(0));
            canvas.drawCircle(0, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
            canvas.drawCircle(mWidth, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
        } else {
            mPaint.setColor(getResources().getColor(R.color.white_50alpha));
            canvas.drawCircle(mX, mY, mControlCircleRadius, mPaint);
            if (isTouchLeftCircle()) {
                mPaint.setColor(getResources().getColor(R.color.red_light));
                canvas.drawCircle(0, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
                mPaint.setColor(Color.alpha(0));
                canvas.drawCircle(mWidth, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
            } else if (isTouchRightCircle()) {
                mPaint.setColor(Color.alpha(0));
                canvas.drawCircle(0, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
                mPaint.setColor(Color.WHITE);
                canvas.drawCircle(mWidth, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
            } else {
                mPaint.setColor(Color.alpha(0));
                canvas.drawCircle(0, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
                canvas.drawCircle(mWidth, mHeight / 2, mLeftAndRightCirclesRadius, mPaint);
            }
        }
        super.onDraw(canvas);

    }

    private boolean isTouchCenterCircle() {
        int hypotenuse = (int) Math.sqrt((mX - mWidth / 2) * (mX - mWidth / 2) + (mY - mHeight / 2) * (mY - mHeight / 2));
        if (hypotenuse <= mCenterCircleRadius)
            return true;
        return false;
    }

    private boolean isTouchLeftCircle() {
        int hypotenuse = (int) Math.sqrt(mX * mX + (mY - mHeight / 2) * (mY - mHeight / 2));
        if (hypotenuse <= mLeftAndRightCirclesRadius)
            return true;
        return false;
    }

    private boolean isTouchRightCircle() {
        int hypotenuse = (int) Math.sqrt((mX - mWidth) * (mX - mWidth) + (mY - mHeight / 2) * (mY - mHeight / 2));
        if (hypotenuse <= mLeftAndRightCirclesRadius)
            return true;
        return false;
    }

    public void setOnSwipeToDo(OnSwipeToDo onSwipeToDo) {
        mOnSwipeToDo = onSwipeToDo;
    }

    public interface OnSwipeToDo {
        boolean onSwipeToLeft(View v, MotionEvent event);
        boolean onSwipeToRight(View v, MotionEvent event);
    }
}
