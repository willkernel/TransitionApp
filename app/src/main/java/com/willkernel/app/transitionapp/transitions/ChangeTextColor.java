package com.willkernel.app.transitionapp.transitions;

import android.animation.ArgbEvaluator;
import android.view.View;
import android.widget.TextView;


public class ChangeTextColor extends AbsChangeValue{
    public ChangeTextColor() {
        super(new ArgbEvaluator(), "textColor", "hintTextColor");
    }

    @Override
    protected Object getPropertyValue(View view, String propertyName) {
        if(view instanceof TextView){
            if("textColor".equals(propertyName)){
                return ((TextView) view).getCurrentTextColor();
            }
            if("hintTextColor".equals(propertyName)){
                return ((TextView) view).getCurrentHintTextColor();
            }
        }
        return super.getPropertyValue(view, propertyName);
    }
}
