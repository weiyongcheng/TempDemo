package org.chengying.com.list.company.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.chengying.com.list.company.utils.DensityUtil;

/**
 * Created by yuanyuan on 17-12-15.
 */

public class LetterListView extends View{

    private OnTouchingLetterChangedListener mOnTouchingLetterChangedListener;
    private String[] mLetters;

    private int choose = -1;
    private Paint mPaint = new Paint();
    private int mTextSize;
    private final int TEXT_DEFAULT_COLOR = Color.parseColor("#8c8c8c");
    private final int TEXT_FOCUS_COLOR = Color.parseColor("#40c60a");

    public LetterListView(Context context) {
        super(context);
        setup(context);
    }

    public LetterListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public LetterListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    private void setup(Context context) {
        mTextSize = DensityUtil.dp2px(context, 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int height = getHeight();
        final int width = getWidth();
        final int length = mLetters.length;
        final int singleHeight = height / length;
        for (int i = 0; i < length; ++i) {
            mPaint.setColor(TEXT_DEFAULT_COLOR);
            mPaint.setTextSize(mTextSize);
            mPaint.setAntiAlias(true);
            if (i == choose) {
                mPaint.setColor(TEXT_FOCUS_COLOR);
                mPaint.setFakeBoldText(true);
            }
            float xPos = width / 2 - mPaint.measureText(mLetters[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(mLetters[i], xPos, yPos, mPaint);
            mPaint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = mOnTouchingLetterChangedListener;
        final int length = mLetters.length;
        final int index = (int) (y / getHeight() * length);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (oldChoose != index && listener != null) {
                    if (index >= 0 && index < length) {
                        listener.onTouchingLetterChanged(mLetters[index]);
                        choose = index;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != index && listener != null) {
                    if (index >= 0 && index < length) {
                        listener.onTouchingLetterChanged(mLetters[index]);
                        choose = index;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                choose = -1;
                invalidate();
                break;
        }
        return true;
    }

    public void setLetters(String[] letters) {
        mLetters = letters;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.mOnTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }
}
