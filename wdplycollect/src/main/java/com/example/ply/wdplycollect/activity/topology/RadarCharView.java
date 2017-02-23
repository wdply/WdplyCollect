package com.example.ply.wdplycollect.activity.topology;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.ply.wdplycollect.R;
import com.example.ply.wdplycollect.util.DisplayUtil;


public class RadarCharView extends View {
    private float[] mPercent = {0.2f, 0.5f, 0f, 0.3f, 0.6f};
    private float textLength = 80;//字占空间
    private float axisLegnth = 230; //轴长度
    private float intervalMore = 20;  //轴超出部分长度
    private int strokeWitdh = 4;//线条宽度
    private float textsize = 24;
    private float textInterval = 20;//字距离轴的长度
    private float textIntervalBetween = 10;//字之间的间距

    private int intervalNum = 5;    //轴分段个数
    private Paint paint;
    private Path path;
    private String[] strArr = {"常识", "言语", "资料", "数量", "判断"};
    private String str = "30";

    public RadarCharView(Context context) {
        super(context);

        init(context);
    }

    public RadarCharView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public RadarCharView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setStrokeWidth(strokeWitdh);

        path = new Path();

        float screentWidth;
        if (isInEditMode()) {
            screentWidth = 720;
        } else {
            screentWidth = DisplayUtil.getScreenWidth(context);
        }

        axisLegnth = screentWidth * 0.3194f;
        textLength = screentWidth * 0.1111f;
        intervalMore = screentWidth * 0.0139f;
        textsize = screentWidth * 0.0333f;
        textInterval = screentWidth * 0.0278f;
        textIntervalBetween = screentWidth * 0.0139f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        float axis1Final = axisLegnth - intervalMore;
        float intervalLength = axis1Final / intervalNum;//每段间隔长度

        paint.setStyle(Paint.Style.STROKE);
        for (int index = 0; index < intervalNum; index++) {
            float lengthCurrent = intervalLength * (index + 1);
            //axis1
            path.moveTo(centerX, centerY - lengthCurrent);
            //axis2
            path.lineTo((float) (centerX + lengthCurrent * Math.cos(Math.toRadians(18))), (float) (centerY - lengthCurrent * Math.sin(Math.toRadians(18))));
            //axis3
            path.lineTo((float) (centerX + lengthCurrent * Math.sin(Math.toRadians(36))), (float) (centerY + lengthCurrent * Math.cos(Math.toRadians(36))));
            //axis4
            path.lineTo((float) (centerX - lengthCurrent * Math.sin(Math.toRadians(36))), (float) (centerY + lengthCurrent * Math.cos(Math.toRadians(36))));
            //axis5
            path.lineTo((float) (centerX - lengthCurrent * Math.cos(Math.toRadians(18))), (float) (centerY - lengthCurrent * Math.sin(Math.toRadians(18))));
            //axis1
            path.lineTo(centerX, centerY - lengthCurrent);
        }
        paint.setStrokeWidth(1);
        paint.setColor(getResources().getColor(R.color.bg_gray012));
        canvas.drawPath(path, paint);

        //axis1
        path.reset();
        paint.setStrokeWidth(strokeWitdh);
        path.moveTo(centerX, centerY);
        path.lineTo(centerX, centerY - axisLegnth);
        paint.setColor(getResources().getColor(R.color.red_002));
        canvas.drawPath(path, paint);
        //axis2
        path.reset();
        path.moveTo(centerX, centerY);
        path.lineTo((float) (centerX + axisLegnth * Math.cos(Math.toRadians(18))), (float) (centerY - axisLegnth * Math.sin(Math.toRadians(18))));
        paint.setColor(getResources().getColor(R.color.orange_002));
        canvas.drawPath(path, paint);
        //axis3
        path.reset();
        path.moveTo(centerX, centerY);
        path.lineTo((float) (centerX + axisLegnth * Math.sin(Math.toRadians(36))), (float) (centerY + axisLegnth * Math.cos(Math.toRadians(36))));
        paint.setColor(getResources().getColor(R.color.bg_green006));
        canvas.drawPath(path, paint);
        //axis4
        path.reset();
        path.moveTo(centerX, centerY);
        path.lineTo((float) (centerX - axisLegnth * Math.sin(Math.toRadians(36))), (float) (centerY + axisLegnth * Math.cos(Math.toRadians(36))));
        paint.setColor(getResources().getColor(R.color.bg_purple004));
        canvas.drawPath(path, paint);
        //axis5
        path.reset();
        path.moveTo(centerX, centerY);
        path.lineTo((float) (centerX - axisLegnth * Math.cos(Math.toRadians(18))), (float) (centerY - axisLegnth * Math.sin(Math.toRadians(18))));
        paint.setColor(getResources().getColor(R.color.blue009));
        canvas.drawPath(path, paint);

        //绘制字
        paint.setTextSize(textsize);
        paint.setStyle(Paint.Style.FILL);
        float textWidth = paint.measureText(strArr[0]);
        float textWidth2 = paint.measureText(str);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //axis1绘制字
        paint.setColor(getResources().getColor(R.color.red_002));
        float textPoint1Y1 = centerY - (axisLegnth + textInterval + textsize / 2);
        canvas.drawText(strArr[0], centerX - textWidth / 2, textPoint1Y1 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        float textPoint1Y2 = centerY - (axisLegnth + textInterval + textsize + textIntervalBetween + textsize / 2);
        canvas.drawText(String.format("%02d", (int) (mPercent[0] * 100)), centerX - textWidth2 / 2, textPoint1Y2 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        //axis2绘制字
        paint.setColor(getResources().getColor(R.color.orange_002));
        float textPoint2X1 = (float) (centerX + axisLegnth * Math.cos(Math.toRadians(18))) + textInterval + textWidth / 2 - textWidth2 / 2;
        float textPoint2Y1 = (float) (centerY - axisLegnth * Math.sin(Math.toRadians(18))) - (textIntervalBetween / 2 + textsize / 2);
        canvas.drawText(String.format("%02d", (int) (mPercent[1] * 100)), textPoint2X1, textPoint2Y1 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        float textPoint2X2 = (float) (centerX + axisLegnth * Math.cos(Math.toRadians(18))) + textInterval;
        float textPoint2Y2 = (float) (centerY - axisLegnth * Math.sin(Math.toRadians(18))) + (textIntervalBetween / 2 + textsize / 2);
        canvas.drawText(strArr[1], textPoint2X2, textPoint2Y2 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        //axis3绘制字
        paint.setColor(getResources().getColor(R.color.bg_green006));
        float textPoint3X1 = (float) (centerX + axisLegnth * Math.sin(Math.toRadians(36))) - textWidth / 2;
        float textPoint3Y1 = (float) (centerY + axisLegnth * Math.cos(Math.toRadians(36))) + textInterval + textsize / 2;
        canvas.drawText(strArr[2], textPoint3X1, textPoint3Y1 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        float textPoint3X2 = (float) (centerX + axisLegnth * Math.sin(Math.toRadians(36))) - textWidth2 / 2;
        float textPoint3Y2 = (float) (centerY + axisLegnth * Math.cos(Math.toRadians(36))) + textInterval + textsize + textIntervalBetween + textsize / 2;
        canvas.drawText(String.format("%02d", (int) (mPercent[2] * 100)), textPoint3X2, textPoint3Y2 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        //axis4绘制字
        paint.setColor(getResources().getColor(R.color.bg_purple004));
        float textPoint4X1 = (float) (centerX - axisLegnth * Math.sin(Math.toRadians(36))) - textWidth / 2;
        float textPoint4Y1 = (float) (centerY + axisLegnth * Math.cos(Math.toRadians(36))) + textInterval + textsize / 2;
        canvas.drawText(strArr[3], textPoint4X1, textPoint4Y1 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        float textPoint4X2 = (float) (centerX - axisLegnth * Math.sin(Math.toRadians(36))) - textWidth2 / 2;
        float textPoint4Y2 = (float) (centerY + axisLegnth * Math.cos(Math.toRadians(36))) + textInterval + textsize + textIntervalBetween + textsize / 2;
        canvas.drawText(String.format("%02d", (int) (mPercent[3] * 100)), textPoint4X2, textPoint4Y2 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        //axis5绘制字
        paint.setColor(getResources().getColor(R.color.blue009));
        float textPoint5X1 = (float) (centerX - axisLegnth * Math.cos(Math.toRadians(18))) - textInterval - textWidth2 / 2 - textWidth / 2;
        float textPoint5Y1 = (float) (centerY - axisLegnth * Math.sin(Math.toRadians(18))) - (textIntervalBetween / 2 + textsize / 2);
        canvas.drawText(String.format("%02d", (int) (mPercent[4] * 100)), textPoint5X1, textPoint5Y1 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);
        float textPoint5X2 = (float) (centerX - axisLegnth * Math.cos(Math.toRadians(18))) - textInterval - textWidth;
        float textPoint5Y2 = (float) (centerY - axisLegnth * Math.sin(Math.toRadians(18))) + (textIntervalBetween / 2 + textsize / 2);
        canvas.drawText(strArr[4], textPoint5X2, textPoint5Y2 - (fontMetrics.bottom + fontMetrics.top) / 2, paint);

        path.reset();
        //绘制实际填充的多边形
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.red_003));
        //axis1
        path.moveTo(centerX, centerY - mPercent[0] * axis1Final);
        //axis2
        path.lineTo((float) (centerX + mPercent[1] * axis1Final * Math.cos(Math.toRadians(18))), (float) (centerY - mPercent[1] * axis1Final * Math.sin(Math.toRadians(18))));
        //axis3
        path.lineTo((float) (centerX + mPercent[2] * axis1Final * Math.sin(Math.toRadians(36))), (float) (centerY + mPercent[2] * axis1Final * Math.cos(Math.toRadians(36))));
        //axis4
        path.lineTo((float) (centerX - mPercent[3] * axis1Final * Math.sin(Math.toRadians(36))), (float) (centerY + mPercent[3] * axis1Final * Math.cos(Math.toRadians(36))));
        //axis5
        path.lineTo((float) (centerX - mPercent[4] * axis1Final * Math.cos(Math.toRadians(18))), (float) (centerY - mPercent[4] * axis1Final * Math.sin(Math.toRadians(18))));
        //axis1
        path.lineTo(centerX, centerY - mPercent[0] * axis1Final);
        canvas.drawPath(path, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.red_004));
        //axis1
        path.moveTo(centerX, centerY - mPercent[0] * axis1Final);
        //axis2
        path.lineTo((float) (centerX + mPercent[1] * axis1Final * Math.cos(Math.toRadians(18))), (float) (centerY - mPercent[1] * axis1Final * Math.sin(Math.toRadians(18))));
        //axis3
        path.lineTo((float) (centerX + mPercent[2] * axis1Final * Math.sin(Math.toRadians(36))), (float) (centerY + mPercent[2] * axis1Final * Math.cos(Math.toRadians(36))));
        //axis4
        path.lineTo((float) (centerX - mPercent[3] * axis1Final * Math.sin(Math.toRadians(36))), (float) (centerY + mPercent[3] * axis1Final * Math.cos(Math.toRadians(36))));
        //axis5
        path.lineTo((float) (centerX - mPercent[4] * axis1Final * Math.cos(Math.toRadians(18))), (float) (centerY - mPercent[4] * axis1Final * Math.sin(Math.toRadians(18))));
        //axis1
        path.lineTo(centerX, centerY - mPercent[0] * axis1Final);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (axisLegnth + textLength) * 2, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (axisLegnth + textLength) * 2, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setPercent(float[] percent) {
        mPercent = percent;
    }
}
