package com.example.ply.wdplycollect.activity.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.ply.wdplycollect.R;
import com.example.ply.wdplycollect.util.DisplayUtil;


public class DrawProgressView extends View {
    private Paint paint;
    private int strokeWidth;
    private float mPercent = 25;

    public DrawProgressView(Context context) {
        super(context);

        init(context);
    }

    public DrawProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public DrawProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        if (isInEditMode()) {
            strokeWidth = 35;
        } else {
            strokeWidth = (int) (DisplayUtil.getScreenWidth(context) * 0.0528);
        }

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        int radius = center - strokeWidth / 2;

        paint.setColor(getResources().getColor(R.color.bg_green007));
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        canvas.drawArc(oval, 225 - 22.5f, 135, false, paint);

        paint.setColor(getResources().getColor(R.color.bg_green003));
        canvas.drawArc(oval, 225 - 22.5f, mPercent * 135 / 100, false, paint);

        Bitmap pointer = BitmapFactory.decodeResource(getResources(), R.drawable.icon_pointer);
        float widthOriginal = pointer.getWidth();
        float heightOriginal = pointer.getHeight();
        float percent = center / widthOriginal;
        float heightScaleAfter = heightOriginal * percent;

        Matrix matrix1 = new Matrix();
        matrix1.postTranslate(center - widthOriginal * 0.18f, center - heightScaleAfter / 2);

        float selfX = widthOriginal * 0.18f;
        float selfY = heightScaleAfter;
        matrix1.preRotate(225 - 22.5f + mPercent * 135 / 100);
        matrix1.preTranslate(-selfX, -selfY / 2);
        matrix1.postTranslate(selfX, selfY / 2);

        matrix1.preScale(percent, percent);

        canvas.drawBitmap(pointer, matrix1, null);
    }

    public void setPercent(float percent) {
        mPercent = percent;
    }
}
