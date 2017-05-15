package com.example.ply.wdplycollect.activity.animation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动画demo
 * Created by ply on 2017/3/16.
 */

public class AnimationDemoActivity extends Activity implements Animation.AnimationListener {
    @BindView(R.id.iv_img)
    ImageView iv_img;
    //    @BindView(R.id.myFragmentImageView)
//    MyFragmentImageView myFragmentImageView;
    @BindView(R.id.tv_translate)
    TextView tv_translate;
    @BindView(R.id.iv_scal)
    ImageView iv_scal;
    @BindView(R.id.iv_rotate)
    ImageView iv_rotate;
    @BindView(R.id.iv_alpha)
    ImageView iv_alpha;
    @BindView(R.id.iv_zonghe)
    ImageView iv_zonghe;
    @BindView(R.id.iv_top)
    ImageView iv_top;

    AnimationDrawable animationDrawable;
    AnimationDrawable mifeng_animation;
    Animation animation_translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
//*********************************帧动画*******************************************************************//
        //第一种方法
        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.luyin);
        iv_img.setBackgroundDrawable(animationDrawable);
        animationDrawable.stop();
        animationDrawable.start();
        //第二种方法
//        iv_img.setBackgroundResource(R.drawable.luyin);
//        animationDrawable = (AnimationDrawable) iv_img.getBackground();
//        mifeng_animation = (AnimationDrawable) getResources().getDrawable(R.drawable.mifeng);
//        animationDrawable.addFrame(mifeng_animation, 2000);
//        animationDrawable.stop();
//        animationDrawable.start();
        //监听没一帧的状态
//        myFragmentImageView.setBackgroundResource(R.drawable.luyin);
//        animationDrawable = (AnimationDrawable) myFragmentImageView.getBackground();
//        myFragmentImageView.animationDrawable = animationDrawable;
//        animationDrawable.stop();
//        animationDrawable.start();
//        CommonUtils.showToast(this, "共" + animationDrawable.getNumberOfFrames() + "帧");
        //******************************补间动画************************************************************//
        //平移
        animation_translate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        tv_translate.startAnimation(animation_translate);
//        animation_translate.setAnimationListener(this);
        //缩放
        Animation animation_scale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        iv_scal.startAnimation(animation_scale);
        //旋转
        Animation animation_rotate = AnimationUtils.loadAnimation(this, R.anim.anim_rote);
        iv_rotate.startAnimation(animation_rotate);
        //透明度
        Animation animation_alpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        iv_alpha.startAnimation(animation_alpha);
        //综合
        Animation animation_zonghe = AnimationUtils.loadAnimation(this, R.anim.anima_set);
        iv_zonghe.startAnimation(animation_zonghe);
        //自定义渲染器
        MyTwenInterpolator myTwenInterpolator = new MyTwenInterpolator();
        Animation animation_top = AnimationUtils.loadAnimation(this, R.anim.anim_translate_top);
        animation_top.setInterpolator(myTwenInterpolator);
        animation_top.setRepeatCount(Animation.INFINITE);
        iv_top.startAnimation(animation_top);
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, AnimationDemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation.hashCode() == animation_translate.hashCode()) {
            //执行下一个动画效果
//            iv_rotate.startAnimation(animation_rotate);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
