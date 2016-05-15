package com.willkernel.app.transitionapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.willkernel.app.transitionapp.R;

public class SharedElementFragment1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sharedelement_fragment1, container, false);

        final ImageView square_purple = (ImageView) view.findViewById(R.id.square_purple);
        DrawableCompat.setTint(square_purple.getDrawable(), Color.MAGENTA);

        view.findViewById(R.id.sample2_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(square_purple, false);
            }
        });

        view.findViewById(R.id.sample2_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(square_purple, true);
            }
        });

        return view;
    }

    private void addNextFragment(ImageView square_purple, boolean overlap) {
        SharedElementFragment2 sharedElementFragment2 = new SharedElementFragment2();

        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(2000);

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(2000);

        sharedElementFragment2.setEnterTransition(slideTransition);
        sharedElementFragment2.setAllowEnterTransitionOverlap(overlap);
        sharedElementFragment2.setAllowReturnTransitionOverlap(overlap);
        sharedElementFragment2.setSharedElementEnterTransition(changeBoundsTransition);

        getFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment2)
                .addToBackStack(null)
                .addSharedElement(square_purple, getString(R.string.square_purple))
                .commit();
    }

}
