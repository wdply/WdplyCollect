package com.example.ply.wdplycollect.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 手指滑动绘制直线(画板)
 * Created by ply on 2017/3/15.
 */

public class DrawPathLineView extends View {
    private Paint paint;
    private Path path;

    public DrawPathLineView(Context context) {
        super(context);
        init();
    }

    public DrawPathLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawPathLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawText("画板区域",0,0,paint);
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                invalidate();
                break;
        }
        return true;
    }

}
