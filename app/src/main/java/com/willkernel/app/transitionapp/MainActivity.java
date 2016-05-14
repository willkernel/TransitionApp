package com.willkernel.app.transitionapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import com.willkernel.app.transitionapp.transitions.TransitionPlayer;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager extraPager;
    private View mStartBtn;
    private View mPathBtn;

    private Scene pageScene1_2;
    private Scene pageScene2_3;
    private Scene pageScene3_4;
    private Scene textScene1, textScene2, textScene3, textScene4;
    private AlphaAnimation mShowAnimation, mHideAnimation, mTextHideAnimation;
    private TransitionPlayer customTransition1_2, customTransition2_3, customTransition3_4;
    private ViewGroup mTextSceneRoot;
    private ArrayList<View> viewArray;

    private int[] resIdArray3 = new int[] {
            R.id.low_price_item, R.id.real_source_item,
            R.id.keep_privacy_item, R.id.has_deposit_item,
            R.id.is_profession_item, R.id.best_server_item
    };

    private int[] resIdArray4 = new int[] {
            R.id.fast_ask_item, R.id.fase_price_item,
            R.id.fast_sign_item, R.id.fast_take_item
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        setAnimation();
        setTitleAndDescription();
        setTransition();
    }

    private void bindView() {
        extraPager = (ViewPager) findViewById(R.id.extra_pager);
        initSmoothScrollToViewPager(extraPager);


        mStartBtn = findViewById(R.id.start);
        View.OnClickListener finishClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        mStartBtn.setOnClickListener(finishClick);
        mStartBtn.setVisibility(View.INVISIBLE);
        mPathBtn = findViewById(R.id.through);
        mPathBtn.setOnClickListener(finishClick);
    }

    private void setAnimation() {
        mShowAnimation = new AlphaAnimation(0.0f, 1.0f);
        mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
        mTextHideAnimation = new AlphaAnimation(1.0f, 0.0f);
        mShowAnimation.setDuration(400);
        mHideAnimation.setDuration(400);
        mTextHideAnimation.setDuration(400);
        mShowAnimation.setFillAfter(true);
        mHideAnimation.setFillAfter(true);
        mTextHideAnimation.setFillAfter(true);
    }


    private void setTitleAndDescription() {
        viewArray = new ArrayList<View>();
        View view1 = View.inflate(this, R.layout.xiaoma_intro_page_base, null);
        ((ImageView) view1.findViewById(R.id.title)).setImageResource(R.drawable.xiaoma_intro_top_text1);
        view1.findViewById(R.id.description).setVisibility(View.VISIBLE);
        viewArray.add(view1);

        View view2 = View.inflate(this, R.layout.xiaoma_intro_page_base, null);
        ((ImageView) view2.findViewById(R.id.title)).setImageResource(R.drawable.xiaoma_intro_top_text2);
        view2.findViewById(R.id.description).setVisibility(View.GONE);
        viewArray.add(view2);

        View view3 = View.inflate(this, R.layout.xiaoma_intro_page_base, null);
        ((ImageView) view3.findViewById(R.id.title)).setImageResource(R.drawable.xiaoma_intro_top_text3);
        view3.findViewById(R.id.description).setVisibility(View.GONE);
        viewArray.add(view3);

        View view4 = View.inflate(this, R.layout.xiaoma_intro_page_base, null);
        ((ImageView) view4.findViewById(R.id.title)).setImageResource(R.drawable.xiaoma_intro_top_text4);
        view4.findViewById(R.id.description).setVisibility(View.GONE);
        viewArray.add(view4);
    }

    /**
     * 设置页面切换时的场景，需要设置开始的 ViewGroup，以及结束的布局资源 Id
     * (ViewGroup) findViewById(R.id.root_view_1_to_2):viewpager切换时需要产生过渡效果的ViewGroup
     * R.layout.xiaoma_intro_scene2:切换效果结束时的布局文件layoutId
     * <p/>
     * TransitionManager.go():给设置好的场景设置过渡的动画效果
     */
    private void setTransition() {
        customTransition1_2 = new TransitionPlayer();
        customTransition2_3 = new TransitionPlayer();
        customTransition3_4 = new TransitionPlayer();

        pageScene1_2 = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.root_view_1_to_2), R.layout.xiaoma_intro_scene2, this);
        pageScene2_3 = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.root_view_2_to_3), R.layout.xiaoma_intro_scene3, this);
        pageScene3_4 = Scene.getSceneForLayout((ViewGroup) findViewById(R.id.root_view_3_to_4), R.layout.xiaoma_intro_scene4, this);
        extraPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                //TransitionManager.go(pageScene1_2); //the default transition
                TransitionManager.go(pageScene1_2, customTransition1_2);
                TransitionManager.go(pageScene2_3, customTransition2_3);
                TransitionManager.go(pageScene3_4, customTransition3_4);
            }
        }, 50);

        mTextSceneRoot = (ViewGroup) findViewById(R.id.text_root_view);
        textScene1 = Scene.getSceneForLayout(mTextSceneRoot, R.layout.xiaoma_intro_scene1_text, this);
        textScene2 = Scene.getSceneForLayout(mTextSceneRoot, R.layout.xiaoma_intro_scene2_text, this);
        textScene3 = Scene.getSceneForLayout(mTextSceneRoot, R.layout.xiaoma_intro_scene3_text, this);
        textScene4 = Scene.getSceneForLayout(mTextSceneRoot, R.layout.xiaoma_intro_scene4_text, this);

        extraPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewArray.get(position));
                return viewArray.get(position);
            }
        });
        extraPager.addOnPageChangeListener(this);
    }


    /**
     * 设置 viewpager的插值器,反射机制访问私有属性 mScroller
     *
     * @param viewPager 需要设置切换页面插值器的 ViewPager
     */
    private void initSmoothScrollToViewPager(ViewPager viewPager) {
        try {
            Interpolator sInterpolator = new Interpolator() {
                /**
                 * @param t 每次页面切换(0,1),插值器的效果是先快后慢
                 */
                public float getInterpolation(float t) {
                    t -= 1.0f;
                    return t * t * t * t * t + 1.0f;
                }
            };
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(viewPager, new Scroller(this, sInterpolator) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, duration * 2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset==0 && position>0){//避免一页结束时，细微的闪移
            return;
        }
        switch (position){
            case -1:
            case 0:
                pageScene1_2.getSceneRoot().setVisibility(View.VISIBLE);
                pageScene2_3.getSceneRoot().setVisibility(View.INVISIBLE);
                pageScene3_4.getSceneRoot().setVisibility(View.INVISIBLE);
                customTransition1_2.setCurrentFraction(positionOffset);
                break;
            case 1:
                pageScene2_3.getSceneRoot().setVisibility(View.VISIBLE);
                pageScene1_2.getSceneRoot().setVisibility(View.INVISIBLE);
                pageScene3_4.getSceneRoot().setVisibility(View.INVISIBLE);
                customTransition2_3.setCurrentFraction(positionOffset);
                break;
            case 2:
                pageScene3_4.getSceneRoot().setVisibility(View.VISIBLE);
                pageScene1_2.getSceneRoot().setVisibility(View.INVISIBLE);
                pageScene2_3.getSceneRoot().setVisibility(View.INVISIBLE);
                customTransition3_4.setCurrentFraction(positionOffset);
                break;
            case 3:
                pageScene3_4.getSceneRoot().setVisibility(View.VISIBLE);
                pageScene1_2.getSceneRoot().setVisibility(View.INVISIBLE);
                pageScene2_3.getSceneRoot().setVisibility(View.INVISIBLE);
                customTransition3_4.setCurrentFraction(1);
                break;
        }
    }

    private int lastPosition = 0;
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                TransitionManager.go(textScene1);
                break;
            case 1:
                TransitionManager.go(textScene2);
                break;
            case 2:
                if (lastPosition != 3) {
                    TransitionSet set = new TransitionSet();
                    for (int i = 0; i < resIdArray3.length ;i++) {
                        Fade fade = new Fade(Fade.MODE_IN);
                        fade.addTarget(resIdArray3[i]);
                        set.addTransition(fade);
                    }
                    set.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
                    set.setDuration(400);
                    set.setStartDelay(400);
                    TransitionManager.go(textScene3, set);
                } else {
                    mStartBtn.startAnimation(mHideAnimation);
                    mShowAnimation.setDuration(400);
                    mPathBtn.startAnimation(mShowAnimation);
                    TransitionManager.go(textScene3);
                    mStartBtn.setClickable(false);
                }
                break;
            case 3:
                TransitionSet set4 = new TransitionSet();
                for (int i = 0; i < resIdArray4.length ;i++) {
                    Fade fade = new Fade(Fade.MODE_IN);
                    fade.addTarget(resIdArray4[i]);
                    set4.addTransition(fade);
                }
                set4.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
                set4.setDuration(400);
                set4.setStartDelay(400);
                TransitionManager.go(textScene4, set4);
                mPathBtn.startAnimation(mHideAnimation);
                mShowAnimation.setDuration(1000);
                mStartBtn.startAnimation(mShowAnimation);
                mStartBtn.setClickable(true);
                break;
            default:
                break;
        }
        lastPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==ViewPager.SCROLL_STATE_DRAGGING){
            mTextSceneRoot.startAnimation(mTextHideAnimation);
        }else{
            mTextSceneRoot.clearAnimation();
        }
    }
}
