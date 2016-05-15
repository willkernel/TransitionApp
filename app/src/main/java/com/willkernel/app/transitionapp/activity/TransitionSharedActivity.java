package com.willkernel.app.transitionapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

import com.willkernel.app.transitionapp.R;
import com.willkernel.app.transitionapp.fragment.SharedElementFragment1;

public class TransitionSharedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_shared);
        setupToolbar();
        setupLayout();
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().setEnterTransition(new Fade().setDuration(2000));
    }

    private void setupLayout() {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(2000);
        // Create fragment and define some of it transitions
        SharedElementFragment1 sharedElementFragment1 = new SharedElementFragment1();
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment1)
                .commit();
    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}
