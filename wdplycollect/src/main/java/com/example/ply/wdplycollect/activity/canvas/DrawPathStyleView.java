package com.example.ply.wdplycollect.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制不同风格路径
 * Created by ply on 2017/3/15.
 */

public class DrawPathStyleView extends View {
    private Paint paint;
    private Path path;
    private PathEffect pathEffect[];
    private int colors[];
    private float pharse = 1;

    public DrawPathStyleView(Context context) {
        super(context);
        init();
    }

    public DrawPathStyleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawPathStyleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path = makePath();
        pathEffect = new PathEffect[6];
        colors = new int[]{Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.GREEN};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        RectF bounds = new RectF();
        canvas.translate(10 - bounds.left, 10 - bounds.top);
        makePathEffect(pathEffect, pharse);
        pharse += 1;
        invalidate();
        for (int i = 0; i < pathEffect.length; i++) {
            paint.setPathEffect(pathEffect[i]);
            paint.setColor(colors[i]);
            canvas.drawPath(path, paint);
            canvas.translate(0, 70);
        }
    }

    public Path makePath() {
        Path path1 = new Path();
        path1.moveTo(0, 0);
        for (int i = 0; i <= 15; i++) {
            path1.lineTo(i * 20, (float) (Math.random() * 70));
        }
        return path1;
    }

    public void makePathEffect(PathEffect[] e, Float phase) {
        Path path = new Path();
        path.addCircle(0, 0, 5, Path.Direction.CCW);

        e[0] = null;
        e[1] = new CornerPathEffect(10);//将线段与线段之间的夹角转换成圆角，参数表示半径
        e[2] = new DashPathEffect(new float[]{20, 10, 5, 10}, phase);//用于绘制虚线路径，第一个参数表示虚线的长度和虚线间隔的距离，改参数是float数组，数组长度必须大于2，也就是指定一个虚线的长度，必须指定虚线的间隔，第二个参数是偏移量，不断改变呈现虚线前后移动效果
        e[3] = new PathDashPathEffect(path, 12, phase, PathDashPathEffect.Style.ROTATE);//绘制任意图形做成的虚线路径，意思每一个线段都可以是任意图形，第一个参数是绘制虚线图形的path队形，第二个参数两个虚线之间的距离，的三个参数偏移量，第四个表示绘制的类型
        e[4] = new ComposePathEffect(e[2], e[1]);//将两种特效组合一起，第一个参数必须是形状特效既DashPathEffect或PathDashPathEffect，第二个参数是外观特效既CornerPathEffect对象
        e[4] = new ComposePathEffect(e[3], e[1]);
    }
}
