package com.willkernel.app.transitionapp.transitions;

import android.animation.ArgbEvaluator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;


public class ChangeBackground extends AbsChangeValue {
    private static final String FIELD_NAME = "backgroundColor";

    public ChangeBackground() {
        super(new ArgbEvaluator(), FIELD_NAME);
    }

    @Override
    protected Object getPropertyValue(View view, String propertyName) {
        if(FIELD_NAME.equals(propertyName)){
            Drawable drawable = view.getBackground();
            if(drawable instanceof ColorDrawable){
                return ((ColorDrawable) drawable).getColor();
            }
        }
        return super.getPropertyValue(view, propertyName);
    }
}
