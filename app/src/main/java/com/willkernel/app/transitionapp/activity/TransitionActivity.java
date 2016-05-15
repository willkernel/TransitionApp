package com.willkernel.app.transitionapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.willkernel.app.transitionapp.R;

/**
 * Created by willkernel on 2016/5/15.
 * Email:willkerneljc@gmail.com
 */
public class TransitionActivity extends Activity implements View.OnClickListener {
    private ViewGroup mRootView;
    private View mRedBox, mGreenBox, mBlueBox, mBlackBox;
    private TransitionSet transitionSet;
    private Transition transition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        mRootView = (ViewGroup) findViewById(R.id.layout_root_view);
        mRootView.setOnClickListener(this);

        mRedBox = findViewById(R.id.red_box);
        mGreenBox = findViewById(R.id.green_box);
        mBlueBox = findViewById(R.id.blue_box);
        mBlackBox = findViewById(R.id.black_box);

        transitionSet = new TransitionSet();
        transitionSet.addTransition(new Fade());
        transitionSet.addTransition(new Slide());
        transitionSet.addTransition(new Explode());
        transitionSet.addTransition(new ChangeBounds());
        transitionSet.addTransition(new ChangeClipBounds());
        transitionSet.addTransition(new ChangeImageTransform());
        transitionSet.addTransition(new ChangeTransform());
        transitionSet.addTransition(new AutoTransition());

        transition = transitionSet.getTransitionAt(0);
        transition.setDuration(2000);
    }

    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(mRootView, transition);

        toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);
    }

    private static void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 100, "Fade");
        menu.add(0, 1, 101, "Slide");
        menu.add(0, 2, 102, "Explode");
        menu.add(0, 3, 103, "ChangeBounds");
        menu.add(0, 4, 104, "ChangeClipBounds");
        menu.add(0, 5, 105, "ChangeImageTransform");
        menu.add(0, 6, 106, "ChangeTransform");
        menu.add(0, 7, 107, "AutoTransition");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        transition = transitionSet.getTransitionAt(item.getItemId());
        transition.setDuration(2000);
        return super.onOptionsItemSelected(item);
    }
}
