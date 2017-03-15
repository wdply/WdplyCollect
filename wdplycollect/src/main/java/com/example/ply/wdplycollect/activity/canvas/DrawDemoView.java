package com.example.ply.wdplycollect.activity.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.ply.wdplycollect.R;

/**
 * 画布样例
 * Created by ply on 2017/3/3.
 */

public class DrawDemoView extends View {
    public DrawDemoView(Context context) {
        super(context);
    }

    public DrawDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //绘制像素点
        float[] arr = new float[]{100, 60, 130, 60, 160, 60, 190, 60, 210, 60};
        canvas.drawPoint(100, 30, paint);//绘制一个点
        canvas.drawPoints(arr, paint);//绘制多个点
        //绘制直线
        float[] line = new float[]{200, 200, 400, 200, 200, 300, 400, 300};
        canvas.drawLine(200, 100, 400, 100, paint);//绘制一条线
        canvas.drawLines(line, paint);//绘制多条线
        //绘制圆形
        canvas.drawCircle(200, 400, 100, paint);
        //绘制矩形
        Rect rect = new Rect(400, 400, 600, 600);
        canvas.drawRect(rect, paint);
        canvas.drawRect(700, 400, 800, 500, paint);
        //绘制弧形
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        RectF rect1 = new RectF(400, 400, 600, 600);
        canvas.drawArc(rect1, 0, 90, true, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rect1, 90, 90, true, paint);
        paint.setColor(Color.GRAY);
        canvas.drawArc(rect1, 180, 90, true, paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rect1, 270, 90, true, paint);
        //绘制文本
        canvas.drawText("文本绘制", 100, 400, paint);//文本 x坐标，y坐标
        Path path = new Path();
        path.moveTo(300, 300);
        path.lineTo(600, 600);
        paint.setTextSize(60);
        paint.setColor(Color.RED);
        canvas.drawTextOnPath("文本绘制", path, 0, 0, paint);
        //绘制位图
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 2;//按原图像的50%绘制
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.abc, option);
        canvas.drawBitmap(bitmap, 200, 700, paint);
    }
}

