package com.willkernel.app.transitionapp.utils;

import android.animation.ValueAnimator;
import android.transition.Transition;

import java.util.ArrayList;


public interface PlayControl {
    void onPreRunAnimator(Transition transition, ArrayList<ValueAnimator> animators);
}
