package com.example.ply.wdplycollect.activity.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.ply.wdplycollect.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 扭曲图像
 * Created by ply on 2017/3/15.
 */

public class DrawBitmapMeshView extends View {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
    private final float[] verts = new float[COUNT * 2];
    private final float[] orig = new float[COUNT * 2];
    private final Matrix matrix = new Matrix();
    private final Matrix m = new Matrix();
    private static Bitmap bitmap;
    private int angle = 0;//圆形轨迹当前的角度

    public DrawBitmapMeshView(Context context) {
        super(context);
        init();
    }

    public DrawBitmapMeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawBitmapMeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setFocusable(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.abc);
        float w = bitmap.getWidth();
        float h = bitmap.getHeight();
        int index = 0;
        //生成verts和orign数组的初始值，这两个数组的值是一样的，只是在扭曲的过程中需要修改verts的值，而修改verts的值需要将原始的值保修在orign数组中
        for (int y = 0; y <= HEIGHT; y++) {
            float fy = h * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = w * x / WIDTH;
                setXY(verts, index, fx, fy);
                setXY(orig, index, fx, fy);
                index += 1;
            }
        }
        matrix.setTranslate(10, 10);
        setBackgroundColor(Color.WHITE);
    }

    //设置verts数组的值
    private static void setXY(float[] array, int index, float x, float y) {
        array[index * 2 + 0] = x;
        array[index * 2 + 1] = y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.concat(matrix);
        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);//原始头像，扭曲宽度，扭曲高度，扭曲区域像素坐标
    }

    //用于扭曲图像的方法，在该方法中分局当前的扭曲点（扭曲区域的中心点），也就是cx和cy参数，来不断变化verts数组中的坐标值
    private void warp(float cx, float cy) {
        final float K = 10000;//该值越大，扭曲的越严重(扭曲的范围越大)
        float[] src = orig;
        float[] dst = verts;
        //按一定的数学规则生成verts数组中的元素值
        for (int i = 0; i < COUNT * 2; i += 2) {
            float x = src[i + 0];
            float y = src[i + 1];
            float dx = cx - x;
            float dy = cy - y;
            float dd = dx * dx + dy * dy;
            double d = Math.sqrt(new Double(dd));
            float pull = K / ((float) (dd * d));
            if (pull >= 1) {
                dst[i + 0] = cx;
                dst[i + 1] = cy;
            } else {
                dst[i + 0] = x + dx * pull;
                dst[i + 1] = y + dy * pull;
            }
        }
    }

    //用于Myview外部控制图像扭曲的方法，该方法在HandlerMessage方法中被调用
    public void mess(int x, int y) {
        float[] pt = {x, y};
        m.mapPoints(pt);
        //重新生成verts数组的值
        warp(pt[0], pt[1]);
        invalidate();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //计算圆形中心点坐标
                    int centerX = bitmap.getWidth() / 2;
                    int centerY = bitmap.getHeight() / 2;
                    double radian = Math.toRadians((double) angle);
                    //通过圆心坐标，半径和当前角度计算当前圆周的某点横坐标
                    int currentX = (int) (centerX + 100 * Math.cos(radian));
                    //通过圆心坐标，半径和当前角度计算当前圆周的某点纵坐标
                    int currentY = (int) (centerY + 100 * Math.cos(radian));
                    //重绘View,并在圆周的某一点扭曲图像
                    mess(currentX, currentY);
                    angle += 2;
                    if (angle > 360) {
                        angle = 0;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    public void start() {
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 100);
    }
}
