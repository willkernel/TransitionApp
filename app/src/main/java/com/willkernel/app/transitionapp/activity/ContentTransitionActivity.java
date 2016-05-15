package com.willkernel.app.transitionapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Visibility;

import com.willkernel.app.transitionapp.R;

public class ContentTransitionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_transition);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(2000);
        // This view will not be affected by enter transition animation
//        enterTransition.excludeTarget(R.id.sample1_button3, true);
        return enterTransition;
    }
}
