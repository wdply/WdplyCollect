package com.example.ply.wdplycollect.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制路径文字
 * Created by ply on 2017/3/15.
 */

public class DrawPathTextView extends View {
    private Paint paint;
    private Paint pathPaint;
    private Path[] paths = new Path[3];

    public DrawPathTextView(Context context) {
        super(context);
        init();
    }

    public DrawPathTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawPathTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setTextSize(20);
        paint.setTypeface(Typeface.SERIF);//字体风格
        paths[0] = new Path();
        paths[1] = new Path();
        paths[2] = new Path();
        makePath(paths[0], 1);
        makePath(paths[1], 2);
        makePath(paths[2], 3);
        pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setColor(0x800000FF);
        pathPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.translate(0, 50);
        //曲线
        canvas.drawPath(paths[0], pathPaint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawTextOnPath("Android开发讲义", paths[0], 0, 0, paint);
        canvas.translate(-20, 80);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawTextOnPath("Android/ipone 开发讲义", paths[0], 0, 0, paint);
        //圆形文字
        canvas.translate(50, 50);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawTextOnPath("Android/ipone 开发讲义", paths[1], -30, 0, paint);
//        //椭圆形文字和椭圆形路径
        canvas.translate(0, 500);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawPath(paths[2], pathPaint);
        canvas.drawTextOnPath("Android/ipone 开发讲义", paths[2], 0, 0, paint);
    }

    public void makePath(Path path, int style) {
        path.moveTo(10, 0);
        switch (style) {
            case 1:
                //曲线路径
                path.cubicTo(100, -50, 200, 50, 300, 0);
                break;
            case 2:
                //圆形路径
                path.addCircle(100, 100, 100, Path.Direction.CCW);
                break;
            case 3:
                //椭圆路径
                RectF rectF = new RectF(0, 0, 200, 100);
                path.addArc(rectF, 0, 360);
                break;
        }
    }
}
