package com.willkernel.app.transitionapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.willkernel.app.transitionapp.R;

public class MainActivity extends Activity {
    //transition to move between scenes
    private Transition transition;
    //flag to swap between scenes
    private boolean start;

    private Scene scene1, scene2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the layout ID
        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.base);

        //first scene
        ViewGroup startViews = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.activity_main, baseLayout, false);
        ViewGroup endViews = (ViewGroup) getLayoutInflater()
                .inflate(R.layout.activity_main_end, baseLayout, false);

        //create the scenes
        scene1 = new Scene(baseLayout, startViews);
        scene2 = new Scene(baseLayout, endViews);

        //create transition, set properties
        transition = new AutoTransition();
        transition.setDuration(500);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

        //initialize flag
        start = true;
    }

    public void changeScene(View v) {
        //check flag
        if (start) {
            TransitionManager.go(scene1, transition);
        } else {
            TransitionManager.go(scene2, transition);
        }
        start = !start;
    }

    public void transitionBlue(View v) {
        startActivity(new Intent(this, TransitionActivity.class));
    }

    public void transitionRed(View v) {
        startActivity(new Intent(this, TransitionRedActivity.class));
    }

    public void transitionGreen(View v) {
        startActivity(new Intent(this, TransitionRedActivity.class));
    }

}
