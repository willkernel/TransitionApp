package com.willkernel.app.transitionapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.willkernel.app.transitionapp.R;

public class SharedElementFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sharedelement_fragment2, container, false);

        ImageView squareBlue = (ImageView) view.findViewById(R.id.square_purple);
        DrawableCompat.setTint(squareBlue.getDrawable(), Color.MAGENTA);

        return view;
    }

}
