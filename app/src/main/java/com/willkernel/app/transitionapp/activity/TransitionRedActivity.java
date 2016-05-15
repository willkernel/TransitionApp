package com.willkernel.app.transitionapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.willkernel.app.transitionapp.R;
import com.willkernel.app.transitionapp.utils.TransitionHelper;

/**
 * Created by willkernel on 2016/5/16.
 * Email:willkerneljc@gmail.com
 */
public class TransitionRedActivity extends Activity {
    private ImageView purpleIv;
    private TextView purpleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red);
        setViews();
        setupWindowAnimations();
    }

    private void setViews() {
        purpleIv = (ImageView) findViewById(R.id.purpleIv);
        purpleTv = (TextView) findViewById(R.id.purpleTv);
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(2000);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    public void onClick(View v) {
        if (v.getId() != R.id.purpleIv) {
            startActivity(ContentTransitionActivity.class, TransitionHelper.createSafeTransitionParticipants(this, true));
        } else {
            final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, false,
                    new Pair<>(purpleIv, getString(R.string.square_purple)),
                    new Pair<>(purpleTv, getString(R.string.sharedElement)));
            startActivity(TransitionSharedActivity.class, pairs);
        }
    }

    private void startActivity(Class target, Pair<View, String>[] pairs) {
        Intent i = new Intent(this, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }
}
