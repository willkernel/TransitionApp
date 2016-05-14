package com.willkernel.app.transitionapp.transitions;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.willkernel.app.transitionapp.utils.AnimatorUtils;
import com.willkernel.app.transitionapp.utils.PlayControl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by willkernel on 2016/5/15.
 * Email:willkerneljc@gmail.com
 */
public class TransitionPlayer extends TransitionSet{
    private LinkedHashMap<Transition, ArrayList<ValueAnimator>> animMap = new LinkedHashMap<>();
    private PlayControl playControl;

    public final ChangeBounds changeBounds = new ChangeBounds();
    public final ChangeAlpha changeAlpha = new ChangeAlpha();
    public final ChangeRotate changeRotate = new ChangeRotate();
    public final ChangeScale changeScale = new ChangeScale();
    public final ChangeTransition changeTransition = new ChangeTransition();
    public final ChangeBackground changeBackground = new ChangeBackground();
    public final ChangeTextColor changeTextColor = new ChangeTextColor();
    public final Fade fadeOut = new Fade(Fade.OUT);
    public final Fade fadeIn = new Fade(Fade.IN);

    public TransitionPlayer() {
        //设置动画效果执行顺序,同步或者有序执行
        setOrdering(TransitionSet.ORDERING_TOGETHER);
        setDuration(300);

        addTransition(changeBounds);
        addTransition(changeAlpha);
        addTransition(changeRotate);
        addTransition(changeScale);
        addTransition(changeTransition);
        addTransition(changeBackground);
        addTransition(changeTextColor);

        addTransition(fadeOut);
        addTransition(fadeIn);

        setInterpolator(new LinearInterpolator());
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
                                   TransitionValues endValues) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void runAnimators() {
        reflectSetupStartEndListeners();
        animMap.clear();
        for(int i=0, count = getTransitionCount(); i<count; i++){
            Transition transition = getTransitionAt(i);
            ArrayList<ValueAnimator> valueAnimators = new ArrayList<>();
            for(Animator animator : AnimatorUtils.collectAnimators(transition)){
                valueAnimators.addAll(AnimatorUtils.collectValueAnimators(animator));
            }

            //insure animator's duration, startDelay, Interpolator same as this transition
            long duration = (long) (getDuration()/ AnimatorUtils.getAnimatorDurationScale());
            long startDelay = getStartDelay();
            TimeInterpolator interpolator = getInterpolator();
            for (ValueAnimator animator : valueAnimators) {
                if (duration >= 0) animator.setDuration(duration);
                if (startDelay >= 0) animator.setStartDelay(startDelay + animator.getStartDelay());
                if (interpolator != null) animator.setInterpolator(interpolator);
            }

            animMap.put(transition, valueAnimators);
            //you can change transition's duration, startDelay and Interpolator at here
            if(playControl!=null) playControl.onPreRunAnimator(transition, valueAnimators);
        }

    }

    private void reflectSetupStartEndListeners() {
        try {
            Method method = TransitionSet.class.getDeclaredMethod("setupStartEndListeners");
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception ignore) {
        }
    }

    public ArrayList<ValueAnimator> getAllAnimator(){
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        for(ArrayList<ValueAnimator> value : animMap.values()){
            animators.addAll(value);
        }
        return animators;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public void setCurrentFraction(float fraction){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            for (ValueAnimator valueAnimator : getAllAnimator()) {
                valueAnimator.setCurrentFraction(fraction);
            }
        }else{
            setCurrentPlayTime((long) (fraction * getDuration()));
        }
    }
    public void setCurrentPlayTime(long playTime){
        for (ValueAnimator valueAnimator : getAllAnimator()) {
            playTime -= valueAnimator.getStartDelay();
            if(playTime<0) playTime=0;
            valueAnimator.setCurrentPlayTime(playTime);
        }
    }

    public PlayControl getPlayControl() {
        return playControl;
    }

    public void setPlayControl(PlayControl playControl) {
        this.playControl = playControl;
    }

}
